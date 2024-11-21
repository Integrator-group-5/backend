package com.luxury.wear.service.controller;

import com.luxury.wear.service.entity.Size;
import com.luxury.wear.service.service.size.SizeService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sizes")
@AllArgsConstructor
@Tag(name = "Size", description = "Endpoints for managing sizes")
public class SizeController {

    private final SizeService sizeService;

    @GetMapping
    @Operation(summary = "Get all sizes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of sizes",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Size.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string",
                                    example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<List<Size>> getAllCategories() {
        List<Size> products = sizeService.getAllSizes();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
