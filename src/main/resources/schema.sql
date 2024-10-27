-- Create the 'product' table first as other tables depend on it
CREATE TABLE IF NOT EXISTS product (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    material VARCHAR(100),
    color VARCHAR(50),
    designer VARCHAR(100),
    price DECIMAL(10, 2) NOT NULL
);

-- Create the 'category' table
CREATE TABLE IF NOT EXISTS category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

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

-- Create the 'product_has_category' join table
CREATE TABLE IF NOT EXISTS product_has_category (
    product_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);

-- Create the 'product_has_size' join table
CREATE TABLE IF NOT EXISTS product_has_size (
    product_id BIGINT NOT NULL,
    size_id BIGINT NOT NULL,
    PRIMARY KEY (product_id, size_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE,
    FOREIGN KEY (size_id) REFERENCES size(id) ON DELETE CASCADE
);
