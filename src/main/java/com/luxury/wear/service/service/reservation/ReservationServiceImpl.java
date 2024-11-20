package com.luxury.wear.service.service.reservation;

import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.entity.Reservation;
import com.luxury.wear.service.entity.User;
import com.luxury.wear.service.repository.ProductRepository;
import com.luxury.wear.service.repository.ReservationRepository;
import com.luxury.wear.service.repository.UserRepository;
import lombok.AllArgsConstructor;
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

    @Override
    public Reservation createReservation(String productName, String userEmail, LocalDate startDate, LocalDate endDate) {

        Product product = productRepository.findByName(productName)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!isAvailable(product.getProductId(), startDate, endDate)) {
            throw new IllegalStateException("Product is not available for the selected dates");
        }

        BigDecimal totalCost = product.getPrice()
                .multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(startDate, endDate)));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setProduct(product);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setTotalCost(totalCost);

        return reservationRepository.save(reservation);
    }

    @Override
    public boolean isAvailable(Long productId, LocalDate startDate, LocalDate endDate) {
        return reservationRepository.findConflictingReservations(productId, startDate, endDate).isEmpty();
    }

    @Override
    public List<LocalDate> getUnavailableDates(Long productId) {
        return reservationRepository.findByProductId(productId).stream()
                .flatMap(reservation -> reservation.getStartDate().datesUntil(reservation.getEndDate().plusDays(1)))
                .distinct()
                .collect(Collectors.toList());
    }
}
