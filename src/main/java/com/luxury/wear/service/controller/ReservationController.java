package com.luxury.wear.service.controller;

import com.luxury.wear.service.dto.product.ProductRequestDto;
import com.luxury.wear.service.dto.product.ProductResponseDto;
import com.luxury.wear.service.dto.reservation.ReservationRequestDto;
import com.luxury.wear.service.dto.reservation.ReservationResponseDto;
import com.luxury.wear.service.dto.user.UserResponseDto;
import com.luxury.wear.service.service.reservation.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reservations")
@AllArgsConstructor
@Tag(name = "Reservation", description = "Endpoints for managing reservations")
public class ReservationController {

    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(
            Authentication authentication,
            @RequestBody ReservationRequestDto reservationRequestDto) {
        ReservationResponseDto createdReservation = reservationService.createReservation(authentication.getName(), reservationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }

    @GetMapping("/paginated")
    @Operation(summary = "Get all reservations paginated with default page size of 6")
    public ResponseEntity<Page<ReservationResponseDto>> getAllUsersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReservationResponseDto> reservations = reservationService.getAllReservationsPaginated(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(reservations);
    }
}
