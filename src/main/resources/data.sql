-- Insert category images
INSERT INTO cover (url) VALUES
    ('/public/img/categories/c-ctel__47f48fad-a34f-451e-867e-eb07b8390414.png'),
    ('/public/img/categories/novias__7b7599c9-db21-4a27-8417-01246bd15450.png'),
    ('/public/img/categories/quinces__4cab6898-828b-42c2-8f80-6ecde13f01e1.jpeg'),
    ('/public/img/categories/dama-honor__15b94168-44ee-4572-b1f2-955d1b5ecc22.png'),
    ('/public/img/categories/grado__48b7b12a-ef17-40e9-a6f3-18ed0dfc6eb7.png');

-- Insert categories
INSERT INTO category (name, description, cover_id) VALUES
    ('Cóctel', 'Vestidos elegantes y modernos, ideales para eventos semiformales.', 1),
    ('Novias', 'Vestidos únicos y románticos, perfectos para el día de la boda.', 2),
    ('Quinces', 'Vestidos de gala amplios y elegantes para celebrar los quince años.', 3),
    ('Dama honor', 'Vestidos coordinados y sencillos para acompañantes de la novia.', 4),
    ('Grado', 'Vestidos formales y juveniles, perfectos para la ceremonia de graduación.', 5);

-- Insert sizes
INSERT INTO size (id, size) VALUES
    (1, 'S'),
    (2, 'M'),
    (3, 'L'),
    (4, 'XL');

-- Insert products

-- Category: Cóctel (total 6 products)
INSERT INTO product (name, reference, description, material, color, designer, price, category_id) VALUES
    ('Hollywood', 'C-453', 'Deslumbra en cualquier evento con este vestido de cóctel. Adornado con lentejuelas brillantes, este vestido no solo capta todas las miradas, sino que también realza tu figura de manera espectacular. Perfecto para cualquier ocasión especial, te hará sentir segura y radiante.', 'Algodón con lentejuelas', 'Plateado', 'Carolina Herrera', 200000, 1),
    ('Brianna', 'C-784', 'Deslumbra en cualquier evento con este vestido de cóctel plateado. Adornado con brillantes y tiras de voleros que añaden un toque visual espectacular, este vestido corto y ajustado realza tu figura de manera impresionante. Perfecto para destacar en cualquier ocasión especial.', 'Seda y poliéster con lentejuelas de alta calidad.', 'Plateado', 'Oscar de la Renta', 150000, 1),
    ('Gold party', 'C-754', 'Impresiona en cualquier evento con este vestido de cóctel tipo blazer. De color dorado y adornado con brillantines, este vestido de manga larga y diseño corto combina a la perfección sensualidad y elegancia. Ideal para destacar en cualquier ocasión especial.', 'Lurex', 'Dorado brillante', 'Versace', 300000, 1),
    ('Esplendor Dorado', 'C-882', 'Este vestido de cóctel dorado es perfecto para brillar. Con detalles en encaje y corte ajustado, resalta la figura de manera elegante.', 'Seda y encaje', 'Dorado', 'Elie Saab', 270000, 1),
    ('Luz de Medianoche', 'C-475', 'Un vestido azul oscuro con lentejuelas que refleja la elegancia de la noche. Ideal para eventos formales y ocasiones especiales.', 'Poliéster con lentejuelas', 'Azul oscuro', 'Dolce & Gabbana', 220000, 1),
    ('Esencia Esmeralda', 'C-993', 'Vestido de cóctel en un tono verde esmeralda con detalles en encaje. Ideal para ocasiones especiales.', 'Encaje y satén', 'Verde esmeralda', 'Zuhair Murad', 250000, 1);

-- Category: Novias (total 6 products)
INSERT INTO product (name, reference, description, material, color, designer, price, category_id) VALUES
    ('Camelia', 'N-784', 'Libera tu espíritu bohemio con este vestido de novia único. El encaje floral, con motivos botánicos, cubre el cuerpo de manera ligera y sensual.', 'Seda natural con encaje', 'Blanco', 'Inbal Dror', 2500000, 2),
    ('Flor de Lirio', 'N-321', 'Vestido de novia con detalles florales y un corte princesa. Perfecto para una boda de cuento de hadas.', 'Encaje francés y tul', 'Marfil', 'Zuhair Murad', 2800000, 2),
    ('Encanto Real', 'N-892', 'Este vestido de novia estilo sirena cuenta con un elegante encaje y una cola larga que agrega sofisticación.', 'Encaje y tul', 'Blanco puro', 'Marchesa', 3000000, 2),
    ('Sueño Eterno', 'N-561', 'Un vestido de novia clásico, con una larga cola y detalles en encaje, ideal para un toque romántico y elegante.', 'Seda y encaje', 'Blanco puro', 'Pronovias', 2800000, 2),
    ('Primavera Floral', 'N-762', 'Vestido de novia de estilo bohemio con flores bordadas en tonos pastel. Ligero y cómodo, perfecto para una boda al aire libre.', 'Tul y seda', 'Blanco marfil', 'Claire Pettibone', 2700000, 2),
    ('Eterna Elegancia', 'N-213', 'Un vestido de novia sofisticado, estilo sirena, con bordados de encaje que destacan la figura.', 'Encaje francés', 'Blanco perla', 'Rosa Clará', 3000000, 2);

-- Category: Quinces (total 6 products)
INSERT INTO product (name, reference, description, material, color, designer, price, category_id) VALUES
    ('Sueño de quince', 'Q-235', 'Un vestido de quince años que te hará sentir como una verdadera princesa. Su diseño romántico y elegante, combinado con el delicado tono rosa, lo convierten en la elección perfecta para celebrar tu gran noche.', 'Tafetán de seda natural, encaje francés.', 'Rosa cuarzo', 'Ariana Vara', 900000, 3),
    ('Rosa Pastel', 'Q-322', 'Vestido de quinceañera en un tono rosa pastel con falda amplia y detalles brillantes. Ideal para una celebración memorable.', 'Organza y seda', 'Rosa pastel', 'Michael Cinco', 950000, 3),
    ('Noche de Estrellas', 'Q-128', 'Vestido azul con falda en capas y detalles de pedrería, perfecto para celebrar los quince años con estilo.', 'Gasa y pedrería', 'Azul celeste', 'Sherri Hill', 920000, 3),
    ('Nuevo amanecer', 'Q-453', 'Vestido de quinceañera en tono rosa claro con detalles brillantes que reflejan la luz. Ideal para destacar en una noche especial.', 'Organza y pedrería', 'Rosa Claro', 'Alfred Angelo', 930000, 3),
    ('Fantasía Rosa', 'Q-652', 'Vestido de quinceañera rosa con un diseño de falda en capas y detalles de pedrería. Perfecto para sentirte como una princesa.', 'Seda y tul', 'Rosa pastel', 'Sherri Hill', 950000, 3),
    ('Encanto de la Realeza', 'Q-818', 'Vestido de quinceañera en un tono dorado claro con detalles de encaje y brillos. Aporta elegancia y sofisticación.', 'Encaje y seda', 'Dorado claro', 'Giovanna', 970000, 3);

-- Category: Dama de honor (total 6 products)
INSERT INTO product (name, reference, description, material, color, designer, price, category_id) VALUES
    ('Niebla de medianoche', 'D-358', 'Este vestido largo de un solo hombro en tono gris perla, con tela fluida y detalles brillantes, es ideal para bodas y eventos formales. Su diseño elegante y movimiento etéreo destacan a quien lo lleva, añadiendo un toque de sofisticación y romanticismo.', 'Gasa de seda', 'Perla', 'Elena Castellani', 450000, 4),
    ('Amanecer Coral', 'D-412', 'Un vestido largo color coral con un diseño sencillo y elegante, perfecto para una dama de honor.', 'Seda y gasa', 'Coral', 'Jenny Yoo', 350000, 4),
    ('Rosa Encantada', 'D-503', 'Vestido en tono rosa con un corte fluido, ideal para acompañar a la novia en su día especial.', 'Gasa de seda', 'Rosa', 'Amsale', 400000, 4),
    ('Belleza Natural', 'D-213', 'Vestido largo color verde oliva con un corte elegante y simple, ideal para cualquier dama de honor.', 'Chiffon', 'Verde oliva', 'Adrianna Papell', 360000, 4),
    ('Brisa de Mar', 'D-789', 'Un vestido azul cielo de estilo griego con tirantes, perfecto para una boda al aire libre.', 'Seda y gasa', 'Azul cielo', 'Davids Bridal', 370000, 4),
    ('Rosa de Ensueño', 'D-654', 'Vestido rosa claro con un diseño simple y corte recto, ideal para acompañar a la novia en su día especial.', 'Gasa', 'Rosa claro', 'Jenny Yoo', 390000, 4);

-- Category: Grado (total 6 products)
INSERT INTO product (name, reference, description, material, color, designer, price, category_id) VALUES
    ('Destello Plateado', 'G-216', 'Un vestido de graduación corto en tono plateado, ideal para resaltar en la ceremonia.', 'Seda y lentejuelas', 'Plateado', 'Alice + Olivia', 200000, 5),
    ('Primavera Azul', 'G-319', 'Vestido juvenil en tono azul claro, perfecto para una ceremonia de graduación.', 'Chiffon', 'Azul claro', 'Reem Acra', 230000, 5),
    ('Elegancia Púrpura', 'G-472', 'Vestido de graduación en un tono púrpura profundo, corto y elegante, perfecto para una celebración.', 'Seda y encaje', 'Púrpura', 'BCBG Max Azria', 210000, 5),
    ('Estrella Plateada', 'G-583', 'Vestido corto en color plateado con detalles brillantes, ideal para una ceremonia de graduación.', 'Lentejuelas y tul', 'Plateado', 'Jovani', 230000, 5),
    ('Primavera Rosa', 'G-334', 'Vestido rosa claro, perfecto para una graduación en primavera. Corte juvenil y elegante.', 'Chiffon', 'Rosa claro', 'Alyce Paris', 220000, 5),
    ('Atardecer Dorado', 'G-421', 'Vestido dorado con corte en A y detalles de encaje, perfecto para una ceremonia de graduación.', 'Encaje y seda', 'Dorado', 'Mac Duggal', 250000, 5);

-- Insert images

-- Category: Cóctel
INSERT INTO image (url, product_id) VALUES
    ('img/products/coctel/hollywood_1.jpg', 1),
    ('img/products/coctel/hollywood_2.jpg', 1),
    ('img/products/coctel/hollywood_3.jpg', 1),
    ('img/products/coctel/hollywood_4.jpg', 1),
    ('img/products/coctel/hollywood_5.jpg', 1),
    ('img/products/coctel/brianna_1.jpg', 2),
    ('img/products/coctel/brianna_2.jpg', 2),
    ('img/products/coctel/brianna_3.jpg', 2),
    ('img/products/coctel/brianna_4.jpg', 2),
    ('img/products/coctel/brianna_5.jpg', 2),
    ('img/products/coctel/gold_party_1.png', 3),
    ('img/products/coctel/gold_party_2.png', 3),
    ('img/products/coctel/gold_party_3.png', 3),
    ('img/products/coctel/gold_party_4.png', 3),
    ('img/products/coctel/gold_party_5.png', 3),
    ('img/products/coctel/esplendor_dorado_1.jpg', 4),
    ('img/products/coctel/esplendor_dorado_2.jpg', 4),
    ('img/products/coctel/esplendor_dorado_3.jpg', 4),
    ('img/products/coctel/esplendor_dorado_4.jpg', 4),
    ('img/products/coctel/esplendor_dorado_5.jpg', 4),
    ('img/products/coctel/luz_de_medianoche_1.png', 5),
    ('img/products/coctel/luz_de_medianoche_2.png', 5),
    ('img/products/coctel/luz_de_medianoche_3.png', 5),
    ('img/products/coctel/luz_de_medianoche_4.png', 5),
    ('img/products/coctel/luz_de_medianoche_5.png', 5),
    ('img/products/coctel/esencia_esmeralda_1.png', 6),
    ('img/products/coctel/esencia_esmeralda_2.png', 6),
    ('img/products/coctel/esencia_esmeralda_3.png', 6),
    ('img/products/coctel/esencia_esmeralda_4.png', 6),
    ('img/products/coctel/esencia_esmeralda_5.png', 6);

-- Category: Novias
INSERT INTO image (url, product_id) VALUES
    ('img/products/novia/camelia_1.jpg', 7),
    ('img/products/novia/camelia_2.jpg', 7),
    ('img/products/novia/camelia_3.jpg', 7),
    ('img/products/novia/camelia_4.jpg', 7),
    ('img/products/novia/camelia_5.jpg', 7),
    ('img/products/novia/flor_de_lirio_1.png', 8),
    ('img/products/novia/flor_de_lirio_2.png', 8),
    ('img/products/novia/flor_de_lirio_3.png', 8),
    ('img/products/novia/flor_de_lirio_4.png', 8),
    ('img/products/novia/flor_de_lirio_5.png', 8),
    ('img/products/novia/encanto_real_1.png', 9),
    ('img/products/novia/encanto_real_2.png', 9),
    ('img/products/novia/encanto_real_3.png', 9),
    ('img/products/novia/encanto_real_4.jpg', 9),
    ('img/products/novia/encanto_real_5.png', 9),
    ('img/products/novia/sueno_eterno_1.jpg', 10),
    ('img/products/novia/sueno_eterno_2.png', 10),
    ('img/products/novia/sueno_eterno_3.png', 10),
    ('img/products/novia/sueno_eterno_4.png', 10),
    ('img/products/novia/sueno_eterno_5.png', 10),
    ('img/products/novia/primavera_floral_1.jpg', 11),
    ('img/products/novia/primavera_floral_2.jpg', 11),
    ('img/products/novia/primavera_floral_3.jpg', 11),
    ('img/products/novia/primavera_floral_4.jpg', 11),
    ('img/products/novia/primavera_floral_5.jpg', 11),
    ('img/products/novia/eterna_elegancia_1.png', 12),
    ('img/products/novia/eterna_elegancia_2.png', 12),
    ('img/products/novia/eterna_elegancia_3.png', 12),
    ('img/products/novia/eterna_elegancia_4.png', 12),
    ('img/products/novia/eterna_elegancia_5.png', 12);

-- Category: Quinces
INSERT INTO image (url, product_id) VALUES
    ('img/products/quince/sueno_de_quince_1.jpg', 13),
    ('img/products/quince/sueno_de_quince_2.jpg', 13),
    ('img/products/quince/sueno_de_quince_3.jpg', 13),
    ('img/products/quince/sueno_de_quince_4.jpg', 13),
    ('img/products/quince/sueno_de_quince_5.jpg', 13),
    ('img/products/quince/rosa_pastel_1.jpg', 14),
    ('img/products/quince/rosa_pastel_2.png', 14),
    ('img/products/quince/rosa_pastel_3.png', 14),
    ('img/products/quince/rosa_pastel_4.png', 14),
    ('img/products/quince/rosa_pastel_5.png', 14),
    ('img/products/quince/noche_de_estrellas_1.jpg', 15),
    ('img/products/quince/noche_de_estrellas_2.png', 15),
    ('img/products/quince/noche_de_estrellas_3.png', 15),
    ('img/products/quince/noche_de_estrellas_4.png', 15),
    ('img/products/quince/noche_de_estrellas_5.png', 15),
    ('img/products/quince/nuevo_amanecer_1.jpg', 16),
    ('img/products/quince/nuevo_amanecer_2.png', 16),
    ('img/products/quince/nuevo_amanecer_3.png', 16),
    ('img/products/quince/nuevo_amanecer_4.png', 16),
    ('img/products/quince/nuevo_amanecer_5.png', 16),
    ('img/products/quince/fantasia_rosa_1.jpg', 17),
    ('img/products/quince/fantasia_rosa_2.png', 17),
    ('img/products/quince/fantasia_rosa_3.png', 17),
    ('img/products/quince/fantasia_rosa_4.png', 17),
    ('img/products/quince/fantasia_rosa_5.png', 17),
    ('img/products/quince/encanto_real_1.jpg', 18),
    ('img/products/quince/encanto_real_2.png', 18),
    ('img/products/quince/encanto_real_3.png', 18),
    ('img/products/quince/encanto_real_4.png', 18),
    ('img/products/quince/encanto_real_5.png', 18);

-- Category: Dama de honor
INSERT INTO image (url, product_id) VALUES
    ('img/products/dama_honor/niebla_de_medianoche_1.jpg', 19),
    ('img/products/dama_honor/niebla_de_medianoche_2.jpg', 19),
    ('img/products/dama_honor/niebla_de_medianoche_3.jpg', 19),
    ('img/products/dama_honor/niebla_de_medianoche_4.jpg', 19),
    ('img/products/dama_honor/niebla_de_medianoche_5.jpg', 19),
    ('img/products/dama_honor/amanecer_coral_1.jpg', 20),
    ('img/products/dama_honor/amanecer_coral_2.png', 20),
    ('img/products/dama_honor/amanecer_coral_3.png', 20),
    ('img/products/dama_honor/amanecer_coral_4.png', 20),
    ('img/products/dama_honor/amanecer_coral_5.png', 20),
    ('img/products/dama_honor/rosa_encantada_1.jpg', 21),
    ('img/products/dama_honor/rosa_encantada_2.png', 21),
    ('img/products/dama_honor/rosa_encantada_3.png', 21),
    ('img/products/dama_honor/rosa_encantada_4.png', 21),
    ('img/products/dama_honor/rosa_encantada_5.png', 21),
    ('img/products/dama_honor/belleza_natural_1.jpg', 22),
    ('img/products/dama_honor/belleza_natural_2.png', 22),
    ('img/products/dama_honor/belleza_natural_3.png', 22),
    ('img/products/dama_honor/belleza_natural_4.png', 22),
    ('img/products/dama_honor/belleza_natural_5.png', 22),
    ('img/products/dama_honor/brisa_de_mar_1.jpg', 23),
    ('img/products/dama_honor/brisa_de_mar_2.png', 23),
    ('img/products/dama_honor/brisa_de_mar_3.png', 23),
    ('img/products/dama_honor/brisa_de_mar_4.png', 23),
    ('img/products/dama_honor/brisa_de_mar_5.png', 23),
    ('img/products/dama_honor/rosa_de_ensueno_1.jpg', 24),
    ('img/products/dama_honor/rosa_de_ensueno_2.png', 24),
    ('img/products/dama_honor/rosa_de_ensueno_3.png', 24),
    ('img/products/dama_honor/rosa_de_ensueno_4.png', 24),
    ('img/products/dama_honor/rosa_de_ensueno_5.png', 24);

-- Category: Grado
INSERT INTO image (url, product_id) VALUES
    ('img/products/grado/destello_plateado_1.png', 25),
    ('img/products/grado/destello_plateado_2.png', 25),
    ('img/products/grado/destello_plateado_3.png', 25),
    ('img/products/grado/destello_plateado_4.png', 25),
    ('img/products/grado/destello_plateado_5.png', 25),
    ('img/products/grado/primavera_azul_1.png', 26),
    ('img/products/grado/primavera_azul_2.png', 26),
    ('img/products/grado/primavera_azul_3.png', 26),
    ('img/products/grado/primavera_azul_4.png', 26),
    ('img/products/grado/primavera_azul_5.png', 26),
    ('img/products/grado/elegancia_purpura_1.png', 27),
    ('img/products/grado/elegancia_purpura_2.png', 27),
    ('img/products/grado/elegancia_purpura_3.png', 27),
    ('img/products/grado/elegancia_purpura_4.png', 27),
    ('img/products/grado/elegancia_purpura_5.png', 27),
    ('img/products/grado/estrella_plateada_1.png', 28),
    ('img/products/grado/estrella_plateada_2.png', 28),
    ('img/products/grado/estrella_plateada_3.png', 28),
    ('img/products/grado/estrella_plateada_4.png', 28),
    ('img/products/grado/estrella_plateada_5.png', 28),
    ('img/products/grado/primavera_rosa_1.png', 29),
    ('img/products/grado/primavera_rosa_2.png', 29),
    ('img/products/grado/primavera_rosa_3.png', 29),
    ('img/products/grado/primavera_rosa_4.png', 29),
    ('img/products/grado/primavera_rosa_5.png', 29),
    ('img/products/grado/atardecer_dorado_1.png', 30),
    ('img/products/grado/atardecer_dorado_2.png', 30),
    ('img/products/grado/atardecer_dorado_3.png', 30),
    ('img/products/grado/atardecer_dorado_4.png', 30),
    ('img/products/grado/atardecer_dorado_5.png', 30);


-- Associate products with sizes

-- Category: Cóctel
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

-- Category: Novias
INSERT INTO product_has_size (product_id, size_id) VALUES
    (7, 1),
    (7, 2),
    (8, 3),
    (8, 4),
    (9, 1),
    (9, 4),
    (10, 1),
    (10, 2),
    (11, 4),
    (11, 2),
    (12, 2),
    (12, 3);

-- Category: Quinces
INSERT INTO product_has_size (product_id, size_id) VALUES
    (13, 1),
    (13, 2),
    (14, 3),
    (14, 4),
    (15, 1),
    (15, 4),
    (16, 1),
    (16, 2),
    (17, 4),
    (17, 2),
    (18, 2),
    (18, 3);

-- Category: Dama de honor
INSERT INTO product_has_size (product_id, size_id) VALUES
    (19, 1),
    (19, 2),
    (20, 3),
    (20, 4),
    (21, 1),
    (21, 4),
    (22, 1),
    (22, 2),
    (23, 4),
    (23, 2),
    (24, 2),
    (24, 3);

-- Category: Grado
INSERT INTO product_has_size (product_id, size_id) VALUES
    (25, 1),
    (25, 2),
    (26, 3),
    (26, 4),
    (27, 1),
    (27, 4),
    (28, 1),
    (28, 2),
    (29, 4),
    (29, 2),
    (30, 2),
    (30, 3);

-- Insert users
INSERT INTO app_user (first_name, last_name, email, password, user_role) VALUES
    ('Tineo', 'Tineo', 'tineojf@mail.com', 'hashedPassword', 'ADMIN'),
    ('Andrea', 'G', 'andreaaGM@mail.com', 'hashedPassword', 'ADMIN'),
    ('Avril', 'Tihista', 'avril@mail.com', 'hashedPassword', 'ADMIN'),
    ('Helen', 'Bernal', 'helen.b@mail.com', 'hashedPassword', 'ADMIN'),
    ('Carol', 'Velez', 'carolv@mail.com', 'hashedPassword', 'ADMIN'),
    ('Claudia', 'Heredia', 'cheredia@mail.com', 'hashedPassword', 'ADMIN'),
    ('David', 'Blanco', 'davidb@mail.com', 'hashedPassword', 'ADMIN'),
    ('Ingrid', 'Munera Valencia', 'ingridmv@mail.com', 'hashedPassword', 'ADMIN'),
    ('Karenpe', 'Perez', 'karenpe1@mail.com', 'hashedPassword', 'ADMIN'),
    ('Victor', 'Falconí', 'victorf@mail.com', 'hashedPassword', 'ADMIN'),
    ('Luis', 'Pérez', 'luis.perez@mail.com', 'hashedPassword', 'USER'),
    ('María', 'Gómez', 'maria.gomez@mail.com', 'hashedPassword', 'USER'),
    ('Sofia', 'Martínez', 'sofia.m@mail.com', 'hashedPassword', 'USER'),
    ('Carlos', 'Ruiz', 'carlos.ruiz@mail.com', 'hashedPassword', 'USER'),
    ('Ana', 'López', 'analopez@mail.com', 'hashedPassword', 'USER');

-- Insert reservations
-- User: Luis Pérez
INSERT INTO reservation (user_id, product_id, start_date, end_date, total_cost) VALUES
(11, 1, '2024-11-20', '2024-11-22', 400000), -- 2 days * 200000 (Product: Hollywood)
(11, 7, '2024-12-01', '2024-12-03', 5000000); -- 2 days * 2500000 (Product: Camelia)

-- User: María Gómez
INSERT INTO reservation (user_id, product_id, start_date, end_date, total_cost) VALUES
(12, 2, '2024-11-15', '2024-11-18', 450000), -- 3 days * 150000 (Product: Brianna)
(12, 8, '2024-12-05', '2024-12-10', 14000000); -- 5 days * 2800000 (Product: Flor de Lirio)

-- User: Sofia Martínez
INSERT INTO reservation (user_id, product_id, start_date, end_date, total_cost) VALUES
(13, 3, '2024-11-25', '2024-11-28', 900000), -- 3 days * 300000 (Product: Gold Party)
(13, 13, '2024-12-01', '2024-12-02', 1800000); -- 2 days * 900000 (Product: Sueño de quince)

-- User: Carlos Ruiz
INSERT INTO reservation (user_id, product_id, start_date, end_date, total_cost) VALUES
(14, 4, '2024-11-10', '2024-11-13', 1080000), -- 4 days * 270000 (Product: Esplendor Dorado)
(14, 19, '2024-12-01', '2024-12-05', 2250000); -- 5 days * 450000 (Product: Niebla de medianoche)

-- User: Ana López
INSERT INTO reservation (user_id, product_id, start_date, end_date, total_cost) VALUES
(15, 5, '2024-11-22', '2024-11-23', 440000), -- 2 days * 220000 (Product: Luz de Medianoche)
(15, 25, '2024-12-10', '2024-12-12', 600000); -- 2 days * 300000 (Product: Destello Plateado)

-- User: David Blanco
INSERT INTO reservation (user_id, product_id, start_date, end_date, total_cost) VALUES
(7, 6, '2024-11-16', '2024-11-17', 500000), -- 2 days * 250000 (Product: Esencia Esmeralda)
(7, 26, '2024-12-03', '2024-12-05', 690000); -- 3 days * 230000 (Product: Primavera Azul)

-- User: Claudia Heredia
INSERT INTO reservation (user_id, product_id, start_date, end_date, total_cost) VALUES
(6, 11, '2024-11-18', '2024-11-20', 5400000), -- 3 days * 1800000 (Product: Primavera Floral)
(6, 12, '2024-12-06', '2024-12-07', 6000000); -- 2 days * 3000000 (Product: Eterna Elegancia)

-- User: Ingrid Munera Valencia
INSERT INTO reservation (user_id, product_id, start_date, end_date, total_cost) VALUES
(8, 15, '2024-11-23', '2024-11-27', 4600000), -- 4 days * 1150000 (Product: Noche de Estrellas)
(8, 20, '2024-12-08', '2024-12-09', 700000); -- 2 days * 350000 (Product: Amanecer Coral)

-- User: Victor Falconí
INSERT INTO reservation (user_id, product_id, start_date, end_date, total_cost) VALUES
(10, 28, '2024-11-29', '2024-11-30', 460000), -- 2 days * 230000 (Product: Estrella Plateada)
(10, 30, '2024-12-12', '2024-12-14', 750000); -- 3 days * 250000 (Product: Atardecer Dorado)
