package com.luxury.wear.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*@RestController
@RequestMapping("/api/v1/categories/upload")*/
@Slf4j
@Tag(name = "Image Upload", description = "Endpoints for uploading category images")
public class ImageUploadController {

    private static final String UPLOAD_DIR = "public/img/categories";
    private static final long MAX_FILE_SIZE_BYTES = 5 * 1024 * 1024; // 5 MB
    private static final String[] ALLOWED_EXTENSIONS = {"jpg", "jpeg", "png", "webp", "svg", "gif"};

    @PostMapping
    @Operation(summary = "Upload an image")
    public ResponseEntity<Map<String, String>> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name) {

        Map<String, String> response = new HashMap<>();

        // Check if the file is empty
        if (file.isEmpty()) {
            response.put("response", "File cannot be empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Check if the file size exceeds the limit
        if (file.getSize() > MAX_FILE_SIZE_BYTES) {
            response.put("response", "File size exceeds the 5 MB limit.");
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(response);
        }

        // Validate file extension
        String fileExtension = Objects.requireNonNull(StringUtils.getFilenameExtension(file.getOriginalFilename())).toLowerCase();
        if (!isValidImageFormat(fileExtension)) {
            response.put("response", "Only .jpg, .jpeg, .png, .webp, .svg, and .gif formats are allowed.");
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(response);
        }

        try {
            // Ensure upload directory exists
            Path uploadPath = Paths.get(UPLOAD_DIR);
            File uploadDir = uploadPath.toFile();
            if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                response.put("response", "Could not create upload directory.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

            Path filePath = uploadPath.resolve(name);
            log.info("Saving file to: {}", filePath.toAbsolutePath());
            Files.copy(file.getInputStream(), filePath);

            // Return response with file path
            response.put("response", "/img/categories/" + name);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IOException e) {
            String errorMessage = "Error saving file.";
            response.put("response", errorMessage);
            log.error(errorMessage, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private boolean isValidImageFormat(String fileExtension) {
        for (String allowedExtension : ALLOWED_EXTENSIONS) {
            if (allowedExtension.equalsIgnoreCase(fileExtension)) {
                return true;
            }
        }
        return false;
    }

    private String toUrlFriendlyString(String str) {
        return str.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-+|-+$", "");
    }
}
