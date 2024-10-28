-- Insert categories
INSERT INTO category (id, name, description) VALUES
    (1, 'Cóctel', 'Vestidos elegantes y modernos, ideales para eventos semiformales.'),
    (2, 'Novias', 'Vestidos únicos y románticos, perfectos para el día de la boda.'),
    (3, 'Quinces', 'Vestidos de gala amplios y elegantes para celebrar los quince años.'),
    (4, 'Dama honor', 'Vestidos coordinados y sencillos para acompañantes de la novia.'),
    (5, 'Grado', 'Vestidos formales y juveniles, perfectos para la ceremonia de graduación.');

-- Insert sizes
INSERT INTO size (id, size) VALUES
    (1, 'S'),
    (2, 'M'),
    (3, 'L'),
    (4, 'XL');

-- Insert products without specifying product_id
INSERT INTO product (name, reference, description, material, color, designer, price) VALUES
    ('Sueño de quince', 'Q-235', 'Un vestido de quince años que te hará sentir como una verdadera princesa. Su diseño romántico y elegante, combinado con el delicado tono rosa, lo convierten en la elección perfecta para celebrar tu gran noche.', 'Tafetán de seda natural, encaje francés.', 'Rosa cuarzo', 'Ariana Vara', 900000),
    ('Camelia', 'N-784', 'Libera tu espíritu bohemio con este vestido de novia único. El encaje floral, con motivos botánicos, cubre el cuerpo de manera ligera y sensual.', 'Seda natural con encaje', 'Blanco', 'Inbal Dror', 2500000),
    ('Hollywood', 'C-453', 'Deslumbra en cualquier evento con este vestido de cóctel. Adornado con lentejuelas brillantes, este vestido no solo capta todas las miradas, sino que también realza tu figura de manera espectacular. Perfecto para cualquier ocasión especial, te hará sentir segura y radiante.', 'Algodón con lentejuelas', 'Plateado', 'Carolina Herrera', 200000),
    ('Brianna', 'C-784', 'Deslumbra en cualquier evento con este vestido de cóctel plateado. Adornado con brillantes y tiras de voleros que añaden un toque visual espectacular, este vestido corto y ajustado realza tu figura de manera impresionante. Perfecto para destacar en cualquier ocasión especial.', 'Seda y poliéster con lentejuelas de alta calidad.', 'Plateado', 'Oscar de la Renta', 150000),
    ('Gold party', 'C-754', 'Impresiona en cualquier evento con este vestido de cóctel tipo blazer. De color dorado y adornado con brillantines, este vestido de manga larga y diseño corto combina a la perfección sensualidad y elegancia. Ideal para destacar en cualquier ocasión especial.', 'Lurex', 'Dorado brillante', 'Versace', 300000),
    ('Niebla de medianoche', 'D-358', 'Este vestido largo de un solo hombro en tono gris perla, con tela fluida y detalles brillantes, es ideal para bodas y eventos formales. Su diseño elegante y movimiento etéreo destacan a quien lo lleva, añadiendo un toque de sofisticación y romanticismo.', 'Gasa de seda', 'Perla', 'Elena Castellani', 450000);

-- Insert images without specifying image_id
INSERT INTO image (url, product_id) VALUES
    ('img/products/quince/sueno_de_quince_1.jpg', 1),
    ('img/products/quince/sueno_de_quince_2.jpg', 1),
    ('img/products/quince/sueno_de_quince_3.jpg', 1),
    ('img/products/quince/sueno_de_quince_4.jpg', 1),
    ('img/products/quince/sueno_de_quince_5.jpg', 1),
    ('img/products/novia/camelia_1.jpg', 2),
    ('img/products/novia/camelia_2.jpg', 2),
    ('img/products/novia/camelia_3.jpg', 2),
    ('img/products/novia/camelia_4.jpg', 2),
    ('img/products/novia/camelia_5.jpg', 2),
    ('img/products/coctel/hollywood_1.jpg', 3),
    ('img/products/coctel/hollywood_2.jpg', 3),
    ('img/products/coctel/hollywood_3.jpg', 3),
    ('img/products/coctel/hollywood_4.jpg', 3),
    ('img/products/coctel/hollywood_5.jpg', 3),
    ('img/products/coctel/brianna_1.jpg', 4),
    ('img/products/coctel/brianna_2.jpg', 4),
    ('img/products/coctel/brianna_3.jpg', 4),
    ('img/products/coctel/brianna_4.jpg', 4),
    ('img/products/coctel/brianna_5.jpg', 4),
    ('img/products/coctel/gold_party_1.png', 5),
    ('img/products/coctel/gold_party_2.png', 5),
    ('img/products/coctel/gold_party_3.png', 5),
    ('img/products/coctel/gold_party_4.png', 5),
    ('img/products/coctel/gold_party_5.png', 5),
    ('img/products/dama_honor/niebla_de_medianoche_1.jpg', 6),
    ('img/products/dama_honor/niebla_de_medianoche_2.jpg', 6),
    ('img/products/dama_honor/niebla_de_medianoche_3.jpg', 6),
    ('img/products/dama_honor/niebla_de_medianoche_4.jpg', 6),
    ('img/products/dama_honor/niebla_de_medianoche_5.jpg', 6);

-- Associate products with categories
INSERT INTO product_has_category (product_id, category_id) VALUES
    (1, 3),
    (2, 2),
    (3, 1),
    (4, 1),
    (5, 1),
    (6, 4),
    (6, 5);

-- Associate products with sizes
INSERT INTO product_has_size (product_id, size_id) VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 1),
    (3, 4),
    (4, 1),
    (4, 2),
    (5, 4),
    (5, 2),
    (6, 2),
    (6, 3);
