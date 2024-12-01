package com.luxury.wear.service.service.reservation;

import com.luxury.wear.service.dto.reservation.ReservationRequestDto;
import com.luxury.wear.service.dto.reservation.ReservationResponseDto;
import com.luxury.wear.service.entity.Address;
import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.entity.Reservation;
import com.luxury.wear.service.entity.User;
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
import java.time.temporal.ChronoUnit;
import java.util.List;
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

    @Transactional
    @Override
    public ReservationResponseDto createReservation(String userEmail, ReservationRequestDto reservationRequestDto) {

        Product product = productRepository.findByName(reservationRequestDto.getProductName())
                .orElseThrow(() -> new IllegalArgumentException("Product with name " + reservationRequestDto.getProductName() + " not found"));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + userEmail + " not found"));

        if (!isAvailable(product.getProductId(), reservationRequestDto.getStartDate(), reservationRequestDto.getEndDate())) {
            throw new IllegalStateException("Product is not available for the selected dates");
        }

        BigDecimal totalCost = product.getPrice()
                .multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(reservationRequestDto.getStartDate(), reservationRequestDto.getEndDate())));

        Address address = addressService.createAddress(reservationRequestDto);

        if (reservationRequestDto.getSaveData()) {
            user.setDni(reservationRequestDto.getDni());
            user.setPhoneNumber(reservationRequestDto.getPhoneNumber());
            userRepository.save(user);

            Long addressId = reservationRequestDto.getAddressId();

            if (addressId != null && addressId > 0) {
                address = addressRepository.findById(addressId)
                        .orElseThrow(() -> new IllegalArgumentException("Address with ID " + addressId + " not found"));

                if (!address.getUser().getUserId().equals(user.getUserId())) {
                    throw new IllegalStateException("Address does not belong to the user");
                }

                addressService.updateAddress(addressId, reservationRequestDto);
            } else {
                user.getAddresses().add(address);
                address.setUser(user);
                addressRepository.save(address);
            }
        }

        Reservation reservation = reservationMapper.toEntity(reservationRequestDto, user, product, address);
        reservation.setTotalCost(totalCost);

        Reservation savedReservation = reservationRepository.save(reservation);

        return reservationMapper.toResponseDto(savedReservation);
    }

    @Override
    public boolean isAvailable(Long productId, LocalDate startDate, LocalDate endDate) {
        return reservationRepository.findConflictingReservations(productId, startDate, endDate).isEmpty();
    }

    @Override
    public List<ReservationResponseDto> getUserReservationsById(Long userId) {
        return reservationRepository.findByUserUserId(userId)
                .stream()
                .map(reservationMapper::toResponseDto)
                .toList();
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
}
