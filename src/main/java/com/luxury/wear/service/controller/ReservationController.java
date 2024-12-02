package com.luxury.wear.service.controller;

import com.luxury.wear.service.dto.reservation.ReservationRequestDto;
import com.luxury.wear.service.dto.reservation.ReservationResponseDto;
import com.luxury.wear.service.dto.user.UserResponseDto;
import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.service.reservation.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/{id}")
    @Operation(summary = "Get a reservation by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservationResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Reservation not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Reservation not found."))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string",
                                    example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<ReservationResponseDto> getReservationById(@PathVariable("id") Long id) {
        ReservationResponseDto existingReservation = reservationService.getReservationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(existingReservation);
    }

    @GetMapping("/user-reservations")
    @Operation(
            summary = "Get list of reservations by user",
            description = "Retrieves a paginated list of all the reservations that belong to an user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user's reservations.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized. User is not authenticated.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Unauthorized access."))),
            @ApiResponse(responseCode = "404", description = "User not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "User not found."))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<Page<ReservationResponseDto>> getFavoriteProducts(
            @Parameter(description = "The page number to retrieve", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "The page size to retrieve", example = "6")
            @RequestParam(defaultValue = "6") int size,
            Authentication authentication) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReservationResponseDto> userReservations = reservationService.getUserReservationsByUserEmail(authentication.getName(), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(userReservations);
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
