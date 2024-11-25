package com.luxury.wear.service.service.reservation;

import com.luxury.wear.service.dto.reservation.ReservationRequestDto;
import com.luxury.wear.service.dto.reservation.ReservationResponseDto;
import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.entity.Reservation;
import com.luxury.wear.service.entity.User;
import com.luxury.wear.service.mapper.ReservationMapper;
import com.luxury.wear.service.repository.ProductRepository;
import com.luxury.wear.service.repository.ReservationRepository;
import com.luxury.wear.service.repository.UserRepository;
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

    @Override
    public ReservationResponseDto createReservation(String userEmail, ReservationRequestDto reservationRequestDto) {

        Product product = productRepository.findByName(reservationRequestDto.getProductName())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!isAvailable(product.getProductId(), reservationRequestDto.getStartDate(), reservationRequestDto.getEndDate())) {
            throw new IllegalStateException("Product is not available for the selected dates");
        }

        BigDecimal totalCost = product.getPrice()
                .multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(reservationRequestDto.getStartDate(), reservationRequestDto.getEndDate())));

        Reservation reservation = reservationMapper.toEntity(reservationRequestDto);
        reservation.setUser(user);
        reservation.setProduct(product);
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
