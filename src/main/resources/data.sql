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

-- Insert products

-- Category: Cóctel (total 6 products)
INSERT INTO product (name, reference, description, material, color, designer, price) VALUES
    ('Hollywood', 'C-453', 'Deslumbra en cualquier evento con este vestido de cóctel. Adornado con lentejuelas brillantes, este vestido no solo capta todas las miradas, sino que también realza tu figura de manera espectacular. Perfecto para cualquier ocasión especial, te hará sentir segura y radiante.', 'Algodón con lentejuelas', 'Plateado', 'Carolina Herrera', 200000),
    ('Brianna', 'C-784', 'Deslumbra en cualquier evento con este vestido de cóctel plateado. Adornado con brillantes y tiras de voleros que añaden un toque visual espectacular, este vestido corto y ajustado realza tu figura de manera impresionante. Perfecto para destacar en cualquier ocasión especial.', 'Seda y poliéster con lentejuelas de alta calidad.', 'Plateado', 'Oscar de la Renta', 150000),
    ('Gold party', 'C-754', 'Impresiona en cualquier evento con este vestido de cóctel tipo blazer. De color dorado y adornado con brillantines, este vestido de manga larga y diseño corto combina a la perfección sensualidad y elegancia. Ideal para destacar en cualquier ocasión especial.', 'Lurex', 'Dorado brillante', 'Versace', 300000),
    ('Esplendor Dorado', 'C-882', 'Este vestido de cóctel dorado es perfecto para brillar. Con detalles en encaje y corte ajustado, resalta la figura de manera elegante.', 'Seda y encaje', 'Dorado', 'Elie Saab', 270000),
    ('Luz de Medianoche', 'C-475', 'Un vestido azul oscuro con lentejuelas que refleja la elegancia de la noche. Ideal para eventos formales y ocasiones especiales.', 'Poliéster con lentejuelas', 'Azul oscuro', 'Dolce & Gabbana', 220000),
    ('Esencia Esmeralda', 'C-993', 'Vestido de cóctel en un tono verde esmeralda con detalles en encaje. Ideal para ocasiones especiales.', 'Encaje y satén', 'Verde esmeralda', 'Zuhair Murad', 250000);

-- Category: Novias (total 6 products)
INSERT INTO product (name, reference, description, material, color, designer, price) VALUES
    ('Camelia', 'N-784', 'Libera tu espíritu bohemio con este vestido de novia único. El encaje floral, con motivos botánicos, cubre el cuerpo de manera ligera y sensual.', 'Seda natural con encaje', 'Blanco', 'Inbal Dror', 2500000),
    ('Flor de Lirio', 'N-321', 'Vestido de novia con detalles florales y un corte princesa. Perfecto para una boda de cuento de hadas.', 'Encaje francés y tul', 'Marfil', 'Zuhair Murad', 2800000),
    ('Encanto Real', 'N-892', 'Este vestido de novia estilo sirena cuenta con un elegante encaje y una cola larga que agrega sofisticación.', 'Encaje y tul', 'Blanco puro', 'Marchesa', 3000000),
    ('Sueño Eterno', 'N-561', 'Un vestido de novia clásico, con una larga cola y detalles en encaje, ideal para un toque romántico y elegante.', 'Seda y encaje', 'Blanco puro', 'Pronovias', 2800000),
    ('Primavera Floral', 'N-762', 'Vestido de novia de estilo bohemio con flores bordadas en tonos pastel. Ligero y cómodo, perfecto para una boda al aire libre.', 'Tul y seda', 'Blanco marfil', 'Claire Pettibone', 2700000),
    ('Eterna Elegancia', 'N-213', 'Un vestido de novia sofisticado, estilo sirena, con bordados de encaje que destacan la figura.', 'Encaje francés', 'Blanco perla', 'Rosa Clará', 3000000);

-- Category: Quinces (total 6 products)
INSERT INTO product (name, reference, description, material, color, designer, price) VALUES
    ('Sueño de quince', 'Q-235', 'Un vestido de quince años que te hará sentir como una verdadera princesa. Su diseño romántico y elegante, combinado con el delicado tono rosa, lo convierten en la elección perfecta para celebrar tu gran noche.', 'Tafetán de seda natural, encaje francés.', 'Rosa cuarzo', 'Ariana Vara', 900000),
    ('Rosa Pastel', 'Q-322', 'Vestido de quinceañera en un tono rosa pastel con falda amplia y detalles brillantes. Ideal para una celebración memorable.', 'Organza y seda', 'Rosa pastel', 'Michael Cinco', 950000),
    ('Noche de Estrellas', 'Q-128', 'Vestido azul con falda en capas y detalles de pedrería, perfecto para celebrar los quince años con estilo.', 'Gasa y pedrería', 'Azul celeste', 'Sherri Hill', 920000),
    ('Nuevo amanecer', 'Q-453', 'Vestido de quinceañera en tono rosa claro con detalles brillantes que reflejan la luz. Ideal para destacar en una noche especial.', 'Organza y pedrería', 'Rosa Claro', 'Alfred Angelo', 930000),
    ('Fantasía Rosa', 'Q-652', 'Vestido de quinceañera rosa con un diseño de falda en capas y detalles de pedrería. Perfecto para sentirte como una princesa.', 'Seda y tul', 'Rosa pastel', 'Sherri Hill', 950000),
    ('Encanto Real', 'Q-818', 'Vestido de quinceañera en un tono dorado claro con detalles de encaje y brillos. Aporta elegancia y sofisticación.', 'Encaje y seda', 'Dorado claro', 'Giovanna', 970000);

-- Category: Dama de honor (total 6 products)
INSERT INTO product (name, reference, description, material, color, designer, price) VALUES
    ('Niebla de medianoche', 'D-358', 'Este vestido largo de un solo hombro en tono gris perla, con tela fluida y detalles brillantes, es ideal para bodas y eventos formales. Su diseño elegante y movimiento etéreo destacan a quien lo lleva, añadiendo un toque de sofisticación y romanticismo.', 'Gasa de seda', 'Perla', 'Elena Castellani', 450000),
    ('Amanecer Coral', 'D-412', 'Un vestido largo color coral con un diseño sencillo y elegante, perfecto para una dama de honor.', 'Seda y gasa', 'Coral', 'Jenny Yoo', 350000),
    ('Rosa Encantada', 'D-503', 'Vestido en tono rosa con un corte fluido, ideal para acompañar a la novia en su día especial.', 'Gasa de seda', 'Rosa', 'Amsale', 400000),
    ('Belleza Natural', 'D-213', 'Vestido largo color verde oliva con un corte elegante y simple, ideal para cualquier dama de honor.', 'Chiffon', 'Verde oliva', 'Adrianna Papell', 360000),
    ('Brisa de Mar', 'D-789', 'Un vestido azul cielo de estilo griego con tirantes, perfecto para una boda al aire libre.', 'Seda y gasa', 'Azul cielo', 'Davids Bridal', 370000),
    ('Rosa de Ensueño', 'D-654', 'Vestido rosa claro con un diseño simple y corte recto, ideal para acompañar a la novia en su día especial.', 'Gasa', 'Rosa claro', 'Jenny Yoo', 390000);

-- Category: Grado (total 6 products)
INSERT INTO product (name, reference, description, material, color, designer, price) VALUES
    ('Destello Plateado', 'G-216', 'Un vestido de graduación corto en tono plateado, ideal para resaltar en la ceremonia.', 'Seda y lentejuelas', 'Plateado', 'Alice + Olivia', 200000),
    ('Primavera Azul', 'G-319', 'Vestido juvenil en tono azul claro, perfecto para una ceremonia de graduación.', 'Chiffon', 'Azul claro', 'Reem Acra', 230000),
    ('Elegancia Púrpura', 'G-472', 'Vestido de graduación en un tono púrpura profundo, corto y elegante, perfecto para una celebración.', 'Seda y encaje', 'Púrpura', 'BCBG Max Azria', 210000),
    ('Estrella Plateada', 'G-583', 'Vestido corto en color plateado con detalles brillantes, ideal para una ceremonia de graduación.', 'Lentejuelas y tul', 'Plateado', 'Jovani', 230000),
    ('Primavera Rosa', 'G-334', 'Vestido rosa claro, perfecto para una graduación en primavera. Corte juvenil y elegante.', 'Chiffon', 'Rosa claro', 'Alyce Paris', 220000),
    ('Atardecer Dorado', 'G-421', 'Vestido dorado con corte en A y detalles de encaje, perfecto para una ceremonia de graduación.', 'Encaje y seda', 'Dorado', 'Mac Duggal', 250000);

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

-- Associate products with categories

-- Category: Cóctel
INSERT INTO product_has_category (product_id, category_id) VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 1),
    (5, 1),
    (6, 1);

-- Category: Novias
INSERT INTO product_has_category (product_id, category_id) VALUES
    (7, 2),
    (8, 2),
    (9, 2),
    (10, 2),
    (11, 2),
    (12, 2);

-- Category: Quinces
INSERT INTO product_has_category (product_id, category_id) VALUES
    (13, 3),
    (14, 3),
    (15, 3),
    (16, 3),
    (17, 3),
    (18, 3);

-- Category: Dama de honor
INSERT INTO product_has_category (product_id, category_id) VALUES
    (19, 4),
    (20, 4),
    (21, 4),
    (22, 4),
    (23, 4),
    (24, 4);


-- Category: Grado
INSERT INTO product_has_category (product_id, category_id) VALUES
    (25, 5),
    (26, 5),
    (27, 5),
    (28, 5),
    (29, 5),
    (30, 5);

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
INSERT INTO app_user (name, username, email, password, user_role) VALUES
    ('Tineo', 'tineojf', 'tineojf@mail.com', 'hashedPassword', 'ADMIN'),
    ('AndreaaGM', 'andreaaGM', 'andreaaGM@mail.com', 'hashedPassword', 'ADMIN'),
    ('avril-tihista', 'avril-tihista', 'avril@mail.com', 'hashedPassword', 'ADMIN'),
    ('Helen Bernal', 'bhelening', 'helen.b@mail.com', 'hashedPassword', 'ADMIN'),
    ('Carol Julieth Velez', 'CarolJVelez', 'carolv@mail.com', 'hashedPassword', 'ADMIN'),
    ('Claudia Heredia', 'cheredia10', 'cheredia@mail.com', 'hashedPassword', 'ADMIN'),
    ('David Blanco', 'DavidBlanco2825', 'davidb@mail.com', 'hashedPassword', 'ADMIN'),
    ('Ingrid Munera Valencia', 'ingridmv24', 'ingridmv@mail.com', 'hashedPassword', 'ADMIN'),
    ('Karenpe1', 'Karenpe1', 'karenpe1@mail.com', 'hashedPassword', 'ADMIN'),
    ('Victor Falconí', 'VictorFalconi', 'victorf@mail.com', 'hashedPassword', 'ADMIN'),
    ('Luis Pérez', 'luisperez', 'luis.perez@mail.com', 'hashedPassword', 'USER'),
    ('María Gómez', 'mariagomez', 'maria.gomez@mail.com', 'hashedPassword', 'USER'),
    ('Sofia Martínez', 'sofimartinez', 'sofia.m@mail.com', 'hashedPassword', 'USER'),
    ('Carlos Ruiz', 'carlosruiz', 'carlos.ruiz@mail.com', 'hashedPassword', 'USER'),
    ('Ana López', 'analopez', 'analopez@mail.com', 'hashedPassword', 'USER');

