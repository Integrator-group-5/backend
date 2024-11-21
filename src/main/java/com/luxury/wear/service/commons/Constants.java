package com.luxury.wear.service.commons;

public class Constants {

    // File Operations
    public static final String CATEGORY_UPLOAD_DIR = "public/img/categories";

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

    // Error Messages
    public static final String ERROR_EMAIL_ALREADY_EXISTS = "There is already a user created with email: ";
    public static final String ERROR_USER_NOT_FOUND_EMAIL = "User not found with email: ";
    public static final String ERROR_USER_NOT_FOUND_ID = "User not found with Id: ";

    public static final String ERROR_PRODUCT_NOT_FOUND_ID = "Product not found with Id: ";
    public static final String ERROR_PRODUCT_NOT_FOUND_REFERENCE = "Product not found with reference: ";
    public static final String ERROR_PRODUCT_ALREADY_EXISTS_NAME = "Product already exists with name: ";
    public static final String ERROR_PRODUCT_ALREADY_EXISTS_REFERENCE = "Product already exists with reference: ";

    public static final String ERROR_CATEGORY_NOT_FOUND_ID = "Category not found with Id: ";
}
