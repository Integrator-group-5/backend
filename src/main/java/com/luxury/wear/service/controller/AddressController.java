package com.luxury.wear.service.controller;

import com.luxury.wear.service.dto.address.AddressResponseDto;
import com.luxury.wear.service.entity.Address;
import com.luxury.wear.service.entity.Category;
import com.luxury.wear.service.service.address.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@AllArgsConstructor
@Tag(name = "Address", description = "Endpoints for managing addresses")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/by-user")
    @Operation(summary = "Get all addresses that belong to an User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all addresses",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Address.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string",
                                    example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<List<AddressResponseDto>> getUserAddressesByEmail(Authentication authentication) {
        List<AddressResponseDto> addresses = addressService.getUserAddressesByEmail(authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @GetMapping("/{countryName}/pickup-sites")
    @Operation(summary = "Get all the pick-up point addresses available by Country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all pickup points",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Address.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string",
                                    example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<List<AddressResponseDto>> getPickupPointByCountryName(@PathVariable String countryName) {
        List<AddressResponseDto> addresses = addressService.getPickupPointByCountryName(countryName);
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }
}
