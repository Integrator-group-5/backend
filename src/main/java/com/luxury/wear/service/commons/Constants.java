package com.luxury.wear.service.commons;

import java.math.BigDecimal;

public class Constants {

    // File Operations
    public static final String CATEGORY_UPLOAD_DIR = "public/img/categories";
    public static final String PRODUCT_UPLOAD_DIR = "public/img/products";



    // Validation Messages
    public static final String FIRST_NAME = "First Name";
    public static final String LAST_NAME = "Last Name";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";

    public static final int NAME_MIN_LENGTH_VALUE = 3;
    public static final int PASSWORD_MIN_LENGTH_VALUE = 6;

    public static final String IS_REQUIRED = " is required and cannot be empty or blank.";
    public static final String IS_NOT_VALID = " is not valid.";

    public static final String FIRST_NAME_IS_REQUIRED = FIRST_NAME + IS_REQUIRED;
    public static final String LAST_NAME_IS_REQUIRED = LAST_NAME + IS_REQUIRED;
    public static final String EMAIL_IS_REQUIRED = EMAIL + IS_REQUIRED;
    public static final String PASSWORD_IS_REQUIRED = PASSWORD + IS_REQUIRED;

    public static final String EMAIL_IS_NOT_VALID = EMAIL + IS_NOT_VALID;
    public static final String PASSWORD_IS_NOT_VALID = PASSWORD + IS_NOT_VALID;

    public static final String FIRST_NAME_MIN_LENGTH = FIRST_NAME + " must be at least " + NAME_MIN_LENGTH_VALUE + " characters long.";
    public static final String LAST_NAME_MIN_LENGTH = LAST_NAME + " must be at least " + NAME_MIN_LENGTH_VALUE + " characters long.";
    public static final String PASSWORD_MIN_LENGTH = PASSWORD + " must be at least " + PASSWORD_MIN_LENGTH_VALUE + " characters long.";

    //ProductRequestDto
    public static final String NAME = "Name";
    public static final String REFERENCE = "Reference";
    public static final String DESCRIPTION = "Description";
    public static final String MATERIAL = "Material";
    public static final String COLOR = "Color";
    public static final String DESIGNER = "Designer";
    public static final String PRICE = "Price";
    public static final String IMAGES = "Images";
    public static final String CATEGORY = "Category";
    public static final String SIZE = "sizes";

    public static final int REFERENCE_MIN_LENGTH_VALUE = 1;
    public static final int DESCRIPTION_MIN_LENGTH_VALUE = 5;
    public static final int MATERIAL_MIN_LENGTH_VALUE = 4;
    public static final int COLOR_MIN_LENGTH_VALUE = 3;
    public static final int DESIGNER_MIN_LENGTH_VALUE = 3;
    public static final String PRICE_MIN_VALUE = "0.01";
    public static final String PRICE_MAX_VALUE = "99999999";
    public static final int IMAGES_MIN_LENGTH_VALUE = 1;
    public static final int IMAGES_MAX_LENGTH_VALUE = 5;
    public static final int SIZE_MIN_LENGTH_VALUE = 1;

    public static final String NAME_IS_REQUIRED = NAME + IS_REQUIRED;
    public static final String REFERENCE_IS_REQUIRED = REFERENCE + IS_REQUIRED;
    public static final String DESCRIPTION_IS_REQUIRED = DESCRIPTION + IS_REQUIRED;
    public static final String MATERIAL_IS_REQUIRED = MATERIAL + IS_REQUIRED;
    public static final String COLOR_IS_REQUIRED = COLOR + IS_REQUIRED;
    public static final String DESIGNER_IS_REQUIRED = DESIGNER + IS_REQUIRED;
    public static final String PRICE_IS_REQUIRED = PRICE + IS_REQUIRED;
    public static final String IMAGES_IS_REQUIRED = IMAGES + IS_REQUIRED;
    public static final String CATEGORY_IS_REQUIRED = CATEGORY + IS_REQUIRED;
    public static final String SIZE_IS_REQUIRED = SIZE + IS_REQUIRED;

    public static final String NAME_MIN_LENGTH = NAME + " must be at least " + NAME_MIN_LENGTH_VALUE + " characters long.";
    public static final String REFERENCE_MIN_LENGTH = REFERENCE + " must be at least " + REFERENCE_MIN_LENGTH_VALUE + " characters long.";
    public static final String DESCRIPTION_MIN_LENGTH = DESCRIPTION + " must be at least " + DESCRIPTION_MIN_LENGTH_VALUE + " characters long.";
    public static final String MATERIAL_MIN_LENGTH = MATERIAL + " must be at least " + MATERIAL_MIN_LENGTH_VALUE + " characters long.";
    public static final String COLOR_MIN_LENGTH = COLOR + " must be at least " + COLOR_MIN_LENGTH_VALUE + " characters long.";
    public static final String DESIGNER_MIN_LENGTH = DESIGNER + " must be at least " + DESIGNER_MIN_LENGTH_VALUE + " characters long.";
    public static final String PRICE_MIN_VALUE_MESSAGE = PRICE + " must be greater than " + PRICE_MIN_VALUE + " value.";
    public static final String PRICE_MAX_LENGTH_MESSAGE = PRICE + " must be less than " + PRICE_MAX_VALUE + " value.";
    public static final String IMAGES_MIN_LENGTH = IMAGES + " must be at least " + IMAGES_MIN_LENGTH_VALUE + " element.";
    public static final String IMAGES_MAX_LENGTH = IMAGES + " must be at least " + IMAGES_MAX_LENGTH_VALUE + " elements.";
    public static final String SIZE_MAX_LENGTH = SIZE + " must be at least " + SIZE_MIN_LENGTH_VALUE + " elements.";

    // Error Messages
    public static final String ERROR_EMAIL_ALREADY_EXISTS = "There is already a user created with email: ";
    public static final String ERROR_USER_NOT_FOUND_EMAIL = "User not found with email: ";
    public static final String ERROR_USER_NOT_FOUND_ID = "User not found with Id: ";

    public static final String ERROR_PRODUCT_NOT_FOUND_ID = "Product not found with Id: ";
    public static final String ERROR_PRODUCT_NOT_FOUND_REFERENCE = "Product not found with reference: ";
    public static final String ERROR_PRODUCT_NOT_FOUND_NAME = "Product not found with name: ";
    public static final String ERROR_PRODUCT_ALREADY_EXISTS_NAME = "Product already exists with name: ";
    public static final String ERROR_PRODUCT_ALREADY_EXISTS_REFERENCE = "Product already exists with reference: ";

    public static final String ERROR_CATEGORY_NOT_FOUND_ID = "Category not found with Id: ";

    public static final String ERROR_SIZE_NOT_FOUND_ID = "Size not found with Id: ";
}
