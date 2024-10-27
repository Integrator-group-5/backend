INSERT INTO category (id, name, description) VALUES
    (1, 'Gala', 'Vestidos para eventos formales.'),
    (2, 'Elegante', 'Ropa de lujo para ocasiones especiales.'),
    (3, 'Casual', 'Vestidos cómodos y sencillos para el día a día.'),
    (4, 'Fiesta', 'Vestidos para eventos de celebración.'),
    (5, 'Vintage', 'Ropa de estilo retro o inspirada en décadas pasadas.');

-- Insert sizes
INSERT INTO size (id, size) VALUES
    (1, 'S'),
    (2, 'M'),
    (3, 'L'),
    (4, 'XL');

-- Insert products
INSERT INTO product (product_id, name, reference, description, material, color, designer, price) VALUES
    (1, 'Vestido de Gala Elegante', 'G-235', 'Un vestido de gala de seda con detalles brillantes.', 'Seda', 'Rojo', 'Carolina Herrera', 120.50),
    (2, 'Vestido Casual Veraniego', 'Q-784', 'Un vestido ligero y fresco, perfecto para el verano.', 'Algodón', 'Blanco con estampado floral', 'Zara', 45.00),
    (3, 'Vestido de Fiesta con Lentejuelas', 'N-453', 'Un vestido corto y llamativo, perfecto para una fiesta nocturna.', 'Poliéster con lentejuelas', 'Negro', 'Balmain', 85.00),
    (4, 'Vestido Vintage Clásico', 'C-784', 'Un vestido inspirado en los años 50 con un diseño clásico.', 'Seda y encaje', 'Azul claro', 'Dior', 95.00);

-- Insert images for each product
INSERT INTO image (image_id, url, product_id) VALUES
    (101, 'https://example.com/images/vestido_gala_1.jpg', 1),
    (102, 'https://example.com/images/vestido_gala_2.jpg', 1),
    (103, 'https://example.com/images/vestido_gala_3.jpg', 1),
    (104, 'https://example.com/images/vestido_gala_4.jpg', 1),
    (105, 'https://example.com/images/vestido_gala_5.jpg', 1),
    (106, 'https://example.com/images/vestido_casual_1.jpg', 2),
    (107, 'https://example.com/images/vestido_casual_2.jpg', 2),
    (108, 'https://example.com/images/vestido_casual_3.jpg', 2),
    (109, 'https://example.com/images/vestido_casual_4.jpg', 2),
    (110, 'https://example.com/images/vestido_casual_5.jpg', 2),
    (111, 'https://example.com/images/vestido_fiesta_1.jpg', 3),
    (112, 'https://example.com/images/vestido_fiesta_2.jpg', 3),
    (113, 'https://example.com/images/vestido_fiesta_3.jpg', 3),
    (114, 'https://example.com/images/vestido_fiesta_4.jpg', 3),
    (115, 'https://example.com/images/vestido_fiesta_5.jpg', 3),
    (116, 'https://example.com/images/vestido_vintage_1.jpg', 4),
    (117, 'https://example.com/images/vestido_vintage_2.jpg', 4),
    (118, 'https://example.com/images/vestido_vintage_3.jpg', 4),
    (119, 'https://example.com/images/vestido_vintage_4.jpg', 4),
    (120, 'https://example.com/images/vestido_vintage_5.jpg', 4);

-- Associate products with categories
INSERT INTO product_has_category (product_id, category_id) VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 4),
    (3, 2),
    (4, 5);

-- Associate products with sizes
INSERT INTO product_has_size (product_id, size_id) VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 1),
    (3, 4),
    (4, 2);