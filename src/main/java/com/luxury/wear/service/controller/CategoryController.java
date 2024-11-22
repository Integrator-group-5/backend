package com.luxury.wear.service.controller;

import com.luxury.wear.service.entity.Category;
import com.luxury.wear.service.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
@Tag(name = "Category", description = "Endpoints for managing categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Create a new category")
    @ApiResponse(
            responseCode = "201", description = "Category created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))
    )
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Category not found."))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string",
                                    example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        Category existingCategory = categoryService.getCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(existingCategory);
    }

    @GetMapping
    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all categories",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Category.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string",
                                    example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> products = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/paginated")
    @Operation(summary = "Get all categories paginated with default page size of 6")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all categories paginated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string",
                                    example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<Page<Category>> getAllCategoriesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryService.getAllCategories(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(categoryPage);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))),
            @ApiResponse(responseCode = "404", description = "Category not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Category not found."))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string",
                                    example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
    }

    @DeleteMapping("/delete-category/{id}")
    @Operation(summary = "Delete a category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Category not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Category not found."))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string",
                                    example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
