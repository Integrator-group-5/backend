package com.luxury.wear.service.benchmark;

import com.luxury.wear.service.service.product.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@SpringBootTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceBenchmarkTest {

    private final ProductServiceImpl productService;

    @Test
    public void benchmarkGetAvailableProducts() {
        // Constant date range
        LocalDate startDate = LocalDate.of(2025, 11, 1);
        LocalDate endDate = LocalDate.of(2025, 11, 30);

        // Test search strings
        String[] searchStrings = {
                "Amanecer Coral",
                "Amanecer Coral",
                "Rosa Encantada",
                "Primavera Rosa",
                "Encanto Real",
                "Esplendor Dorado",
                "c-",
                "q-",
                "d-",
                "g-",
                "n-",
                "vestido",
                "elegante",
                "evento",
                "corto",
                "vestido corto",
                "largo",
                "juvenil",
                "encaje",
                "Corte juvenil y elegante",
                "seda",
                "gasa",
                "poliester",
                "Chiffon",
                "azul",
                "Verde",
                "Blanco",
                "Rosa",
                "Marfil",
                "Dorado",
                "Coral",
                "Perla",
                "Plateado",
                "Inbal Dror",
                "Jenny Yoo",
                "Mac Duggal",
                "Adrianna Papell",
                "Coctel",
                "Novias",
                "Quinces",
                "Dama Honor",
                "Grado",
                "XS",
                "S",
                "M",
                "L",
                "XL",
                "XXL"
        };

        // Pagination configuration
        Pageable pageable = PageRequest.of(0, 10);

        // Header row for Excel
        System.out.println("Search Query\tTotal Results\tExecution Time (ms)");

        for (String search : searchStrings) {
            long startTime = System.nanoTime();

            // Execute the method
            Page<?> products = productService.getAvailableProducts(startDate, endDate, search, pageable);

            long endTime = System.nanoTime();
            long executionTime = (endTime - startTime) / 1_000_000; // Convert to milliseconds

            // Print the results in tab-delimited format
            System.out.printf("%s\t%d\t%d%n", search, products.getTotalElements(), executionTime);
        }
    }
}
