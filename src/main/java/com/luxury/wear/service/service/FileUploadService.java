package com.luxury.wear.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@Slf4j
public class FileUploadService {

    @Value("${file.upload.max-size:5242880}") // Default to 5 MB if not configured
    private long maxFileSize;

    @Value("${file.upload.allowed-extensions:jpg,jpeg,png,webp,svg,gif}")
    private String[] allowedExtensions;

    /**
     * Uploads a file to the specified directory with the given name.
     *
     * @param file        the file to upload
     * @param name        the name to save the file as
     * @param uploadDir   the directory to save the file
     * @return the relative path of the uploaded file
     * @throws IllegalArgumentException if the file is invalid
     * @throws IOException if there are issues saving the file
     */
    public String uploadFile(MultipartFile file, String name, String uploadDir) throws IllegalArgumentException, IOException {
        validateFile(file);

        Path uploadPath = Paths.get(uploadDir);
        ensureDirectoryExists(uploadPath);

        Path filePath = uploadPath.resolve(name);
        log.info("Saving file to: {}", filePath.toAbsolutePath());
        Files.copy(file.getInputStream(), filePath);

        return filePath.toString(); // Return the full file path or customize as needed
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty.");
        }

        if (file.getSize() > maxFileSize) {
            throw new IllegalArgumentException("File size exceeds the limit of " + (maxFileSize / (1024 * 1024)) + " MB.");
        }

        String fileExtension = Objects.requireNonNull(StringUtils.getFilenameExtension(file.getOriginalFilename())).toLowerCase();
        if (!isValidImageFormat(fileExtension)) {
            throw new IllegalArgumentException("Only the following formats are allowed: " + String.join(", ", allowedExtensions));
        }
    }

    private boolean isValidImageFormat(String fileExtension) {
        for (String allowedExtension : allowedExtensions) {
            if (allowedExtension.equalsIgnoreCase(fileExtension)) {
                return true;
            }
        }
        return false;
    }

    private void ensureDirectoryExists(Path path) throws IOException {
        File directory = path.toFile();
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Could not create directory: " + path.toAbsolutePath());
        }
    }

    private String toUrlFriendlyString(String str) {
        return str.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-+|-+$", "");
    }
}
