package com.luxury.wear.service.service.reservation;

import com.luxury.wear.service.dto.reservation.ReservationRequestDto;
import com.luxury.wear.service.dto.reservation.ReservationResponseDto;
import com.luxury.wear.service.entity.Address;
import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.entity.Reservation;
import com.luxury.wear.service.entity.User;
import com.luxury.wear.service.exception.BadRequestException;
import com.luxury.wear.service.exception.ResourceNotFoundException;
import com.luxury.wear.service.mapper.ReservationMapper;
import com.luxury.wear.service.repository.AddressRepository;
import com.luxury.wear.service.repository.ProductRepository;
import com.luxury.wear.service.repository.ReservationRepository;
import com.luxury.wear.service.repository.UserRepository;
import com.luxury.wear.service.service.address.AddressService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ReservationMapper reservationMapper;
    private final AddressService addressService;
    private final AddressRepository addressRepository;
    private final EmailService emailService;

    @Transactional
    @Override
    public ReservationResponseDto createReservation(String userEmail, ReservationRequestDto reservationRequestDto) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + userEmail + " not found"));

        Product product = productRepository.findByName(reservationRequestDto.getProductName())
                .orElseThrow(() -> new ResourceNotFoundException("Product with name " + reservationRequestDto.getProductName() + " not found"));

        validateReservationDates(reservationRequestDto.getStartDate(), reservationRequestDto.getEndDate());

        if (!isAvailable(product.getProductId(), reservationRequestDto.getStartDate(), reservationRequestDto.getEndDate())) {
            throw new IllegalStateException("Product is not available for the selected dates");
        }

        Address address = reservationRequestDto.getShipping()
            ? processAddress(reservationRequestDto, user)
            : addressRepository.findById(reservationRequestDto.getStoreId())
                    .orElseThrow(() -> new ResourceNotFoundException("Store address not found"));

        Reservation reservation = reservationMapper.toEntity(reservationRequestDto, user, product, address);
        reservation.setTotalCost(calculateTotalCost(product, reservationRequestDto));
        reservation.setReservationCode(generateReservationCode());

        ReservationResponseDto responseDto = reservationMapper.toResponseDto(reservationRepository.save(reservation));

        // Enviar correo electrónico con el DTO de respuesta
        sendReservationEmail(user, product, reservation, responseDto);

        return responseDto;
    }

    private String generateReservationCode() {
        String timestamp = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomString = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "RES-" + timestamp + "-" + randomString;
    }


    private Address processAddress(ReservationRequestDto reservationRequestDto, User user) {
        if (reservationRequestDto.getSaveData()) {
            user.setDni(reservationRequestDto.getDni());
            user.setPhoneNumber(reservationRequestDto.getPhoneNumber());
            userRepository.save(user);

            Long addressId = reservationRequestDto.getAddressId();
            if (addressId != null && addressId > 0) {
                Address address = addressRepository.findById(addressId)
                        .orElseThrow(() -> new ResourceNotFoundException("Address with ID " + addressId + " not found"));
                if (!address.getUser().getUserId().equals(user.getUserId())) {
                    throw new BadRequestException("Address does not belong to the user");
                }
                addressService.updateAddress(addressId, reservationRequestDto);
                return address;
            } else {
                Address newAddress = addressService.createAddress(reservationRequestDto);
                newAddress.setUser(user);
                user.getAddresses().add(newAddress);
                return addressRepository.save(newAddress);
            }
        }
        return addressService.createAddress(reservationRequestDto);
    }

    private void validateReservationDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            throw new BadRequestException("Invalid reservation dates: startDate must be before endDate.");
        }
    }

    private BigDecimal calculateTotalCost(Product product, ReservationRequestDto reservationRequestDto) {
        return product.getPrice().multiply(BigDecimal.valueOf(
                ChronoUnit.DAYS.between(reservationRequestDto.getStartDate(), reservationRequestDto.getEndDate())));
    }

    @Override
    public ReservationResponseDto getReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + id));
    }

    @Override
    public boolean isAvailable(Long productId, LocalDate startDate, LocalDate endDate) {
        return reservationRepository.findConflictingReservations(productId, startDate, endDate).isEmpty();
    }

    @Override
    public Page<ReservationResponseDto> getUserReservationsByUserEmail(String email, Pageable pageable) {
        return reservationRepository.findByUserEmail(email, pageable)
                .map(reservationMapper::toResponseDto);
    }

    @Override
    public Page<ReservationResponseDto> getAllReservationsPaginated(Pageable pageable) {
        return reservationRepository.findAll(pageable)
                .map(reservationMapper::toResponseDto);
    }

    @Override
    public List<LocalDate> getUnavailableDates(Long productId) {
        return reservationRepository.findByProductId(productId).stream()
                .flatMap(reservation -> reservation.getStartDate().datesUntil(reservation.getEndDate().plusDays(1)))
                .distinct()
                .collect(Collectors.toList());
    }

    private void sendReservationEmail(User user, Product product, Reservation reservation,
                                      ReservationResponseDto responseDto) {
        String to = "paty.fer1970@gmail.com"; // Correo del cliente
        String subject = "Reservation Confirmation " + product.getName();
        String template = emailService.loadEmailTemplate("Correo_Reserva.html");
        System.out.println("carolv sendReservationEmail1");

        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = now.format(formatter);

        BigDecimal shippingCost = responseDto.getTotalCost().remainder(product.getPrice());

        String body = template.replace("{{firstName}}", user.getFirstName())
                .replace("{{lastName}}", user.getLastName())
                .replace("{{productName}}", product.getName())
                .replace("{{startDate}}", reservation.getStartDate().toString())
                .replace("{{hora}}", formattedTime)
                .replace("{{endDate}}", reservation.getEndDate().toString())
                .replace("{{reservationCode}}", responseDto.getReservationCode())
                .replace("{{phoneNumber}}", responseDto.getPhoneNumber())
                .replace("{{shippingMethod}}", responseDto.getShippingMethod())
                .replace("{{country}}", responseDto.getCountry())
                .replace("{{province}}", responseDto.getProvince())
                .replace("{{address}}", responseDto.getAddress())
                .replace("{{detail}}", responseDto.getDetail())
                .replace("{{company}}", "Luxury Wear")
                .replace("{{phoneNumberCompany}}", "3172568457")
                .replace("{{addressCompany}}", "Calle 54 # 94-35")
                .replace("{{countryCompany}}", "Colombia")
                .replace("{{shippingCost}}", shippingCost.toString())
                .replace("{{totalCost}}", responseDto.getTotalCost().toString());

        emailService.sendEmail(to, subject, body);
    }

}
