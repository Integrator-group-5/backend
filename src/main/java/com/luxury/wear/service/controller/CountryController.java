package com.luxury.wear.service.controller;

import com.luxury.wear.service.service.country.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
@AllArgsConstructor
@Tag(name = "Country", description = "Endpoints for managing countries and their states/departments")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    @Operation(summary = "Get all available countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all countries",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(type = "string")))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string",
                                    example = "An error occurred while processing your request.")))
    })
    public List<String> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{countryName}/states")
    @Operation(summary = "Get all states/departments for a given country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all states/departments for the specified country",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(type = "string")))),
            @ApiResponse(responseCode = "404", description = "Country not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Country not found."))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string",
                                    example = "An error occurred while processing your request.")))
    })
    public List<String> getStatesByCountryName(@PathVariable String countryName) {
        return countryService.getStatesByCountryName(countryName);
    }
}
