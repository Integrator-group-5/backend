-- Tabla de Imagen de Categoría
CREATE TABLE IF NOT EXISTS cover (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(255) NOT NULL
);

-- Create the 'category' table
CREATE TABLE IF NOT EXISTS category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    cover_id BIGINT NOT NULL,
    FOREIGN KEY (cover_id) REFERENCES cover(id) ON DELETE CASCADE
);

-- Create the 'product' table first as other tables depend on it
CREATE TABLE IF NOT EXISTS product (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    reference VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    material VARCHAR(100),
    color VARCHAR(50),
    designer VARCHAR(100),
    price DECIMAL(10, 2) NOT NULL,
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);

-- Add indexes for the 'product' table
CREATE INDEX idx_product_name ON product (name);
CREATE INDEX idx_product_color ON product (color);
CREATE INDEX idx_product_designer ON product (designer);
CREATE INDEX idx_product_reference ON product (reference);

-- Composite index for the `product` table
CREATE INDEX idx_product_name_deleted ON product (name, deleted);
CREATE INDEX idx_product_reference_deleted ON product (reference, deleted);
CREATE INDEX idx_product_color_deleted ON product (color, deleted);


-- Create the 'size' table
CREATE TABLE IF NOT EXISTS size (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    size VARCHAR(10) NOT NULL
);

-- Create the 'image' table with a foreign key reference to 'product'
CREATE TABLE IF NOT EXISTS image (
    image_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

-- Create the 'product_has_size' join table
CREATE TABLE IF NOT EXISTS product_has_size (
    product_id BIGINT NOT NULL,
    size_id BIGINT NOT NULL,
    PRIMARY KEY (product_id, size_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE,
    FOREIGN KEY (size_id) REFERENCES size(id) ON DELETE CASCADE
);

-- Create the 'app_user' table
CREATE TABLE IF NOT EXISTS app_user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    dni VARCHAR(255),
    phone_number VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255),
    user_role VARCHAR(255) CHECK (user_role IN ('USER', 'ADMIN'))
);

CREATE TABLE IF NOT EXISTS address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(255) NOT NULL,
    province VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    detail VARCHAR(255),
    postal_code VARCHAR(20),
    user_id BIGINT,
    CONSTRAINT fk_address_user FOREIGN KEY (user_id) REFERENCES app_user(user_id) ON DELETE CASCADE
);

-- Add an index for `user_id` in the `address` table
CREATE INDEX idx_address_user_id ON address (user_id);

-- Create the 'reservation' table
CREATE TABLE IF NOT EXISTS reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    address_id BIGINT NOT NULL,
    dni VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_cost DECIMAL(10, 2) NOT NULL,
    reservation_code VARCHAR(255) NOT NULL UNIQUE,
    shipping BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_reservation_user FOREIGN KEY (user_id) REFERENCES app_user(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_reservation_product FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE,
    CONSTRAINT fk_reservation_address FOREIGN KEY (address_id) REFERENCES address(id) ON DELETE CASCADE
);

-- Add indexes for the 'reservation' table
CREATE INDEX idx_reservation_date_range ON reservation (start_date, end_date);
CREATE INDEX idx_reservation_product_id ON reservation (product_id);
CREATE INDEX idx_reservation_address_id ON reservation (address_id);

CREATE TABLE IF NOT EXISTS user_favorites (
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES app_user(user_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE IF NOT EXISTS country (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(3) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS country_departments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    country_id BIGINT NOT NULL,
    department VARCHAR(255) NOT NULL,
    FOREIGN KEY (country_id) REFERENCES country(id) ON DELETE CASCADE
);