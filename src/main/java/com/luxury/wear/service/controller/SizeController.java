package com.luxury.wear.service.controller;

import com.luxury.wear.service.entity.Size;
import com.luxury.wear.service.service.size.SizeService;
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
public class SizeController {

    private final SizeService sizeService;

    @GetMapping
    public ResponseEntity<List<Size>> getAllCategories() {
        List<Size> products = sizeService.getAllSizes();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
