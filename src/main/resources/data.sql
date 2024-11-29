-- Insert category images
INSERT INTO cover (url) VALUES
    ('/public/img/categories/c-ctel__47f48fad-a34f-451e-867e-eb07b8390414.png'),
    ('/public/img/categories/novias__7b7599c9-db21-4a27-8417-01246bd15450.png'),
    ('/public/img/categories/quinces__4cab6898-828b-42c2-8f80-6ecde13f01e1.jpeg'),
    ('/public/img/categories/dama-honor__15b94168-44ee-4572-b1f2-955d1b5ecc22.png'),
    ('/public/img/categories/grado__48b7b12a-ef17-40e9-a6f3-18ed0dfc6eb7.png');

-- Insert categories
INSERT INTO category (name, description, cover_id) VALUES
    ('Coctel', 'Vestidos elegantes y modernos, ideales para eventos semiformales.', 1),
    ('Novias', 'Vestidos únicos y románticos, perfectos para el día de la boda.', 2),
    ('Quinces', 'Vestidos de gala amplios y elegantes para celebrar los quince años.', 3),
    ('Dama honor', 'Vestidos coordinados y sencillos para acompañantes de la novia.', 4),
    ('Grado', 'Vestidos formales y juveniles, perfectos para la ceremonia de graduación.', 5);

-- Insert sizes
INSERT INTO size (id, size) VALUES
    (1, 'XS'),
    (2, 'S'),
    (3, 'M'),
    (4, 'L'),
    (5, 'XL'),
    (6, 'XXL');

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
    ('/public/img/products/coctel/hollywood_1.jpg', 1),
    ('/public/img/products/coctel/hollywood_2.jpg', 1),
    ('/public/img/products/coctel/hollywood_3.jpg', 1),
    ('/public/img/products/coctel/hollywood_4.jpg', 1),
    ('/public/img/products/coctel/hollywood_5.jpg', 1),
    ('/public/img/products/coctel/brianna_1.jpg', 2),
    ('/public/img/products/coctel/brianna_2.jpg', 2),
    ('/public/img/products/coctel/brianna_3.jpg', 2),
    ('/public/img/products/coctel/brianna_4.jpg', 2),
    ('/public/img/products/coctel/brianna_5.jpg', 2),
    ('/public/img/products/coctel/gold_party_1.png', 3),
    ('/public/img/products/coctel/gold_party_2.png', 3),
    ('/public/img/products/coctel/gold_party_3.png', 3),
    ('/public/img/products/coctel/gold_party_4.png', 3),
    ('/public/img/products/coctel/gold_party_5.png', 3),
    ('/public/img/products/coctel/esplendor_dorado_1.jpg', 4),
    ('/public/img/products/coctel/esplendor_dorado_2.jpg', 4),
    ('/public/img/products/coctel/esplendor_dorado_3.jpg', 4),
    ('/public/img/products/coctel/esplendor_dorado_4.jpg', 4),
    ('/public/img/products/coctel/esplendor_dorado_5.jpg', 4),
    ('/public/img/products/coctel/luz_de_medianoche_1.png', 5),
    ('/public/img/products/coctel/luz_de_medianoche_2.png', 5),
    ('/public/img/products/coctel/luz_de_medianoche_3.png', 5),
    ('/public/img/products/coctel/luz_de_medianoche_4.png', 5),
    ('/public/img/products/coctel/luz_de_medianoche_5.png', 5),
    ('/public/img/products/coctel/esencia_esmeralda_1.png', 6),
    ('/public/img/products/coctel/esencia_esmeralda_2.png', 6),
    ('/public/img/products/coctel/esencia_esmeralda_3.png', 6),
    ('/public/img/products/coctel/esencia_esmeralda_4.png', 6),
    ('/public/img/products/coctel/esencia_esmeralda_5.png', 6);

-- Category: Novias
INSERT INTO image (url, product_id) VALUES
    ('/public/img/products/novias/camelia_1.jpg', 7),
    ('/public/img/products/novias/camelia_2.jpg', 7),
    ('/public/img/products/novias/camelia_3.jpg', 7),
    ('/public/img/products/novias/camelia_4.jpg', 7),
    ('/public/img/products/novias/camelia_5.jpg', 7),
    ('/public/img/products/novias/flor_de_lirio_1.png', 8),
    ('/public/img/products/novias/flor_de_lirio_2.png', 8),
    ('/public/img/products/novias/flor_de_lirio_3.png', 8),
    ('/public/img/products/novias/flor_de_lirio_4.png', 8),
    ('/public/img/products/novias/flor_de_lirio_5.png', 8),
    ('/public/img/products/novias/encanto_real_1.png', 9),
    ('/public/img/products/novias/encanto_real_2.png', 9),
    ('/public/img/products/novias/encanto_real_3.png', 9),
    ('/public/img/products/novias/encanto_real_4.jpg', 9),
    ('/public/img/products/novias/encanto_real_5.png', 9),
    ('/public/img/products/novias/sueno_eterno_1.jpg', 10),
    ('/public/img/products/novias/sueno_eterno_2.png', 10),
    ('/public/img/products/novias/sueno_eterno_3.png', 10),
    ('/public/img/products/novias/sueno_eterno_4.png', 10),
    ('/public/img/products/novias/sueno_eterno_5.png', 10),
    ('/public/img/products/novias/primavera_floral_1.jpg', 11),
    ('/public/img/products/novias/primavera_floral_2.jpg', 11),
    ('/public/img/products/novias/primavera_floral_3.jpg', 11),
    ('/public/img/products/novias/primavera_floral_4.jpg', 11),
    ('/public/img/products/novias/primavera_floral_5.jpg', 11),
    ('/public/img/products/novias/eterna_elegancia_1.png', 12),
    ('/public/img/products/novias/eterna_elegancia_2.png', 12),
    ('/public/img/products/novias/eterna_elegancia_3.png', 12),
    ('/public/img/products/novias/eterna_elegancia_4.png', 12),
    ('/public/img/products/novias/eterna_elegancia_5.png', 12);

-- Category: Quinces
INSERT INTO image (url, product_id) VALUES
    ('/public/img/products/quinces/sueno_de_quince_1.jpg', 13),
    ('/public/img/products/quinces/sueno_de_quince_2.jpg', 13),
    ('/public/img/products/quinces/sueno_de_quince_3.jpg', 13),
    ('/public/img/products/quinces/sueno_de_quince_4.jpg', 13),
    ('/public/img/products/quinces/sueno_de_quince_5.jpg', 13),
    ('/public/img/products/quinces/rosa_pastel_1.jpg', 14),
    ('/public/img/products/quinces/rosa_pastel_2.png', 14),
    ('/public/img/products/quinces/rosa_pastel_3.png', 14),
    ('/public/img/products/quinces/rosa_pastel_4.png', 14),
    ('/public/img/products/quinces/rosa_pastel_5.png', 14),
    ('/public/img/products/quinces/noche_de_estrellas_1.jpg', 15),
    ('/public/img/products/quinces/noche_de_estrellas_2.png', 15),
    ('/public/img/products/quinces/noche_de_estrellas_3.png', 15),
    ('/public/img/products/quinces/noche_de_estrellas_4.png', 15),
    ('/public/img/products/quinces/noche_de_estrellas_5.png', 15),
    ('/public/img/products/quinces/nuevo_amanecer_1.jpg', 16),
    ('/public/img/products/quinces/nuevo_amanecer_2.png', 16),
    ('/public/img/products/quinces/nuevo_amanecer_3.png', 16),
    ('/public/img/products/quinces/nuevo_amanecer_4.png', 16),
    ('/public/img/products/quinces/nuevo_amanecer_5.png', 16),
    ('/public/img/products/quinces/fantasia_rosa_1.jpg', 17),
    ('/public/img/products/quinces/fantasia_rosa_2.png', 17),
    ('/public/img/products/quinces/fantasia_rosa_3.png', 17),
    ('/public/img/products/quinces/fantasia_rosa_4.png', 17),
    ('/public/img/products/quinces/fantasia_rosa_5.png', 17),
    ('/public/img/products/quinces/encanto_real_1.jpg', 18),
    ('/public/img/products/quinces/encanto_real_2.png', 18),
    ('/public/img/products/quinces/encanto_real_3.png', 18),
    ('/public/img/products/quinces/encanto_real_4.png', 18),
    ('/public/img/products/quinces/encanto_real_5.png', 18);

-- Category: Dama de honor
INSERT INTO image (url, product_id) VALUES
    ('/public/img/products/dama-honor/niebla_de_medianoche_1.jpg', 19),
    ('/public/img/products/dama-honor/niebla_de_medianoche_2.jpg', 19),
    ('/public/img/products/dama-honor/niebla_de_medianoche_3.jpg', 19),
    ('/public/img/products/dama-honor/niebla_de_medianoche_4.jpg', 19),
    ('/public/img/products/dama-honor/niebla_de_medianoche_5.jpg', 19),
    ('/public/img/products/dama-honor/amanecer_coral_1.jpg', 20),
    ('/public/img/products/dama-honor/amanecer_coral_2.png', 20),
    ('/public/img/products/dama-honor/amanecer_coral_3.png', 20),
    ('/public/img/products/dama-honor/amanecer_coral_4.png', 20),
    ('/public/img/products/dama-honor/amanecer_coral_5.png', 20),
    ('/public/img/products/dama-honor/rosa_encantada_1.jpg', 21),
    ('/public/img/products/dama-honor/rosa_encantada_2.png', 21),
    ('/public/img/products/dama-honor/rosa_encantada_3.png', 21),
    ('/public/img/products/dama-honor/rosa_encantada_4.png', 21),
    ('/public/img/products/dama-honor/rosa_encantada_5.png', 21),
    ('/public/img/products/dama-honor/belleza_natural_1.jpg', 22),
    ('/public/img/products/dama-honor/belleza_natural_2.png', 22),
    ('/public/img/products/dama-honor/belleza_natural_3.png', 22),
    ('/public/img/products/dama-honor/belleza_natural_4.png', 22),
    ('/public/img/products/dama-honor/belleza_natural_5.png', 22),
    ('/public/img/products/dama-honor/brisa_de_mar_1.jpg', 23),
    ('/public/img/products/dama-honor/brisa_de_mar_2.png', 23),
    ('/public/img/products/dama-honor/brisa_de_mar_3.png', 23),
    ('/public/img/products/dama-honor/brisa_de_mar_4.png', 23),
    ('/public/img/products/dama-honor/brisa_de_mar_5.png', 23),
    ('/public/img/products/dama-honor/rosa_de_ensueno_1.jpg', 24),
    ('/public/img/products/dama-honor/rosa_de_ensueno_2.png', 24),
    ('/public/img/products/dama-honor/rosa_de_ensueno_3.png', 24),
    ('/public/img/products/dama-honor/rosa_de_ensueno_4.png', 24),
    ('/public/img/products/dama-honor/rosa_de_ensueno_5.png', 24);

-- Category: Grado
INSERT INTO image (url, product_id) VALUES
    ('/public/img/products/grado/destello_plateado_1.png', 25),
    ('/public/img/products/grado/destello_plateado_2.png', 25),
    ('/public/img/products/grado/destello_plateado_3.png', 25),
    ('/public/img/products/grado/destello_plateado_4.png', 25),
    ('/public/img/products/grado/destello_plateado_5.png', 25),
    ('/public/img/products/grado/primavera_azul_1.png', 26),
    ('/public/img/products/grado/primavera_azul_2.png', 26),
    ('/public/img/products/grado/primavera_azul_3.png', 26),
    ('/public/img/products/grado/primavera_azul_4.png', 26),
    ('/public/img/products/grado/primavera_azul_5.png', 26),
    ('/public/img/products/grado/elegancia_purpura_1.png', 27),
    ('/public/img/products/grado/elegancia_purpura_2.png', 27),
    ('/public/img/products/grado/elegancia_purpura_3.png', 27),
    ('/public/img/products/grado/elegancia_purpura_4.png', 27),
    ('/public/img/products/grado/elegancia_purpura_5.png', 27),
    ('/public/img/products/grado/estrella_plateada_1.png', 28),
    ('/public/img/products/grado/estrella_plateada_2.png', 28),
    ('/public/img/products/grado/estrella_plateada_3.png', 28),
    ('/public/img/products/grado/estrella_plateada_4.png', 28),
    ('/public/img/products/grado/estrella_plateada_5.png', 28),
    ('/public/img/products/grado/primavera_rosa_1.png', 29),
    ('/public/img/products/grado/primavera_rosa_2.png', 29),
    ('/public/img/products/grado/primavera_rosa_3.png', 29),
    ('/public/img/products/grado/primavera_rosa_4.png', 29),
    ('/public/img/products/grado/primavera_rosa_5.png', 29),
    ('/public/img/products/grado/atardecer_dorado_1.png', 30),
    ('/public/img/products/grado/atardecer_dorado_2.png', 30),
    ('/public/img/products/grado/atardecer_dorado_3.png', 30),
    ('/public/img/products/grado/atardecer_dorado_4.png', 30),
    ('/public/img/products/grado/atardecer_dorado_5.png', 30);


-- Associate products with sizes

-- Category: Cóctel
INSERT INTO product_has_size (product_id, size_id) VALUES
    (1, 1),
    (1, 2),
    (1, 6),
    (2, 3),
    (2, 4),
    (2, 5),
    (3, 1),
    (3, 4),
    (3, 6),
    (4, 1),
    (4, 2),
    (4, 5),
    (5, 4),
    (5, 2),
    (6, 2),
    (6, 3);

-- Category: Novias
INSERT INTO product_has_size (product_id, size_id) VALUES
    (7, 1),
    (7, 2),
    (7, 6),
    (8, 3),
    (8, 4),
    (8, 6),
    (9, 1),
    (9, 4),
    (10, 1),
    (10, 2),
    (10, 5),
    (11, 4),
    (11, 2),
    (11, 5),
    (12, 2),
    (12, 6),
    (12, 3);

-- Category: Quinces
INSERT INTO product_has_size (product_id, size_id) VALUES
    (13, 1),
    (13, 2),
    (13, 5),
    (14, 3),
    (14, 4),
    (14, 6),
    (15, 1),
    (15, 4),
    (15, 5),
    (16, 1),
    (16, 2),
    (16, 6),
    (17, 4),
    (17, 2),
    (17, 5),
    (18, 2),
    (18, 6),
    (18, 3);

-- Category: Dama de honor
INSERT INTO product_has_size (product_id, size_id) VALUES
    (19, 1),
    (19, 2),
    (19, 5),
    (19, 6),
    (20, 3),
    (20, 4),
    (20, 5),
    (21, 1),
    (21, 4),
    (21, 6),
    (22, 1),
    (22, 2),
    (22, 5),
    (22, 6),
    (23, 4),
    (23, 2),
    (23, 5),
    (24, 2),
    (24, 6),
    (24, 3);

-- Category: Grado
INSERT INTO product_has_size (product_id, size_id) VALUES
    (25, 1),
    (25, 2),
    (25, 3),
    (25, 5),
    (25, 6),
    (26, 3),
    (26, 5),
    (26, 4),
    (27, 1),
    (27, 6),
    (27, 4),
    (28, 1),
    (28, 2),
    (28, 5),
    (28, 6),
    (29, 4),
    (29, 2),
    (29, 6),
    (30, 2),
    (30, 5),
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
    ('Karen', 'Perez', 'karenpe1@mail.com', 'hashedPassword', 'ADMIN'),
    ('Victor', 'Falconí', 'victorf@mail.com', 'hashedPassword', 'ADMIN'),
    ('Luis', 'Pérez', 'luis.perez@mail.com', 'hashedPassword', 'USER'),
    ('María', 'Gómez', 'maria.gomez@mail.com', 'hashedPassword', 'USER'),
    ('Sofia', 'Martínez', 'sofia.m@mail.com', 'hashedPassword', 'USER'),
    ('Carlos', 'Ruiz', 'carlos.ruiz@mail.com', 'hashedPassword', 'USER'),
    ('Ana', 'López', 'analopez@mail.com', 'hashedPassword', 'USER');

-- Insert addresses
INSERT INTO address (country, province, city, address, postal_code, user_id) VALUES
    ('USA', 'California', 'Los Angeles', '123 Main St', '90001', 11), -- Luis Pérez
    ('USA', 'New York', 'New York', '456 Park Ave', '10001', 12), -- María Gómez
    ('USA', 'Texas', 'Houston', '789 Elm St', '77001', 13), -- Sofia Martínez
    ('USA', 'Florida', 'Miami', '321 Ocean Dr', '33101', 14), -- Carlos Ruiz
    ('USA', 'Illinois', 'Chicago', '654 Lake Shore Dr', '60601', 15), -- Ana López
    ('USA', 'California', 'San Francisco', '987 Mission St', '94101', 7), -- David Blanco
    ('USA', 'Nevada', 'Las Vegas', '111 Vegas Blvd', '89101', 6), -- Claudia Heredia
    ('USA', 'Colorado', 'Denver', '222 Rockies Rd', '80201', 8), -- Ingrid Munera Valencia
    ('USA', 'Georgia', 'Atlanta', '333 Peach St', '30301', 10); -- Victor Falconí


-- Insert reservations
-- User: Luis Pérez
INSERT INTO reservation (user_id, product_id, address_id, dni, phone_number, start_date, end_date, total_cost) VALUES
(11, 1, 1, 'LUIS123456', '555-1111', '2024-11-27', '2024-11-29', 400000),  -- 2 days * 200000 (Product: Hollywood)
(11, 7, 1, 'LUIS123456', '555-1111', '2024-12-04', '2024-12-06', 5000000); -- 2 days * 2500000 (Product: Camelia)

-- User: María Gómez
INSERT INTO reservation (user_id, product_id, address_id, dni, phone_number, start_date, end_date, total_cost) VALUES
(12, 2, 2, 'MARIA654321', '555-2222', '2024-12-11', '2024-12-14', 450000),   -- 3 days * 150000 (Product: Brianna)
(12, 8, 2, 'MARIA654321', '555-2222', '2024-12-18', '2024-12-23', 14000000); -- 5 days * 2800000 (Product: Flor de Lirio)

-- User: Sofia Martínez
INSERT INTO reservation (user_id, product_id, address_id, dni, phone_number, start_date, end_date, total_cost) VALUES
(13, 3, 3, 'SOFIA987654', '555-3333', '2024-12-25', '2024-12-28', 900000),  -- 3 days * 300000 (Product: Gold Party)
(13, 13, 3, 'SOFIA987654', '555-3333', '2025-01-01', '2025-01-02', 1800000); -- 2 days * 900000 (Product: Sueño de quince)

-- User: Carlos Ruiz
INSERT INTO reservation (user_id, product_id, address_id, dni, phone_number, start_date, end_date, total_cost) VALUES
(14, 4, 4, 'CARLOS456789', '555-4444', '2025-01-08', '2025-01-11', 1080000),  -- 4 days * 270000 (Product: Esplendor Dorado)
(14, 19, 4, 'CARLOS456789', '555-4444', '2025-01-15', '2025-01-19', 2250000); -- 5 days * 450000 (Product: Niebla de medianoche)

-- User: Ana López
INSERT INTO reservation (user_id, product_id, address_id, dni, phone_number, start_date, end_date, total_cost) VALUES
(15, 5, 5, 'ANA654789', '555-5555', '2025-01-22', '2025-01-23', 440000),  -- 2 days * 220000 (Product: Luz de Medianoche)
(15, 25, 5, 'ANA654789', '555-5555', '2025-01-29', '2025-01-31', 600000); -- 2 days * 300000 (Product: Destello Plateado)

-- User: David Blanco
INSERT INTO reservation (user_id, product_id, address_id, dni, phone_number, start_date, end_date, total_cost) VALUES
(7, 6, 6, 'DAVID123987', '555-6666', '2025-02-05', '2025-02-06', 500000),  -- 2 days * 250000 (Product: Esencia Esmeralda)
(7, 26, 6, 'DAVID123987', '555-6666', '2025-02-12', '2025-02-14', 690000); -- 3 days * 230000 (Product: Primavera Azul)

-- User: Claudia Heredia
INSERT INTO reservation (user_id, product_id, address_id, dni, phone_number, start_date, end_date, total_cost) VALUES
(6, 11, 7, 'CLAUDIA456123', '555-7777', '2025-02-19', '2025-02-21', 5400000), -- 3 days * 1800000 (Product: Primavera Floral)
(6, 12, 7, 'CLAUDIA456123', '555-7777', '2025-02-26', '2025-02-27', 6000000); -- 2 days * 3000000 (Product: Eterna Elegancia)

-- User: Ingrid Munera Valencia
INSERT INTO reservation (user_id, product_id, address_id, dni, phone_number, start_date, end_date, total_cost) VALUES
(8, 15, 8, 'INGRID789123', '555-8888', '2025-03-05', '2025-03-09', 4600000),  -- 4 days * 1150000 (Product: Noche de Estrellas)
(8, 20, 8, 'INGRID789123', '555-8888', '2025-03-12', '2025-03-13', 700000);   -- 2 days * 350000 (Product: Amanecer Coral)

-- User: Victor Falconí
INSERT INTO reservation (user_id, product_id, address_id, dni, phone_number, start_date, end_date, total_cost) VALUES
(10, 28, 9, 'VICTOR321654', '555-9999', '2025-03-19', '2025-03-20', 460000), -- 2 days * 230000 (Product: Estrella Plateada)
(10, 30, 9, 'VICTOR321654', '555-9999', '2025-03-26', '2025-03-28', 750000); -- 3 days * 250000 (Product: Atardecer Dorado)

-- Insert countries
INSERT INTO country (code, name) VALUES
('CO', 'Colombia'),
('PE', 'Peru'),
('EC', 'Ecuador'),
('MX', 'Mexico');

-- Departments for Colombia
INSERT INTO country_departments (country_id, department) VALUES
((SELECT id FROM country WHERE code = 'CO'), 'Amazonas'),
((SELECT id FROM country WHERE code = 'CO'), 'Antioquia'),
((SELECT id FROM country WHERE code = 'CO'), 'Arauca'),
((SELECT id FROM country WHERE code = 'CO'), 'Atlántico'),
((SELECT id FROM country WHERE code = 'CO'), 'Bolívar'),
((SELECT id FROM country WHERE code = 'CO'), 'Boyacá'),
((SELECT id FROM country WHERE code = 'CO'), 'Caldas'),
((SELECT id FROM country WHERE code = 'CO'), 'Caquetá'),
((SELECT id FROM country WHERE code = 'CO'), 'Casanare'),
((SELECT id FROM country WHERE code = 'CO'), 'Cauca'),
((SELECT id FROM country WHERE code = 'CO'), 'Cesar'),
((SELECT id FROM country WHERE code = 'CO'), 'Chocó'),
((SELECT id FROM country WHERE code = 'CO'), 'Córdoba'),
((SELECT id FROM country WHERE code = 'CO'), 'Cundinamarca'),
((SELECT id FROM country WHERE code = 'CO'), 'Guainía'),
((SELECT id FROM country WHERE code = 'CO'), 'Guaviare'),
((SELECT id FROM country WHERE code = 'CO'), 'Huila'),
((SELECT id FROM country WHERE code = 'CO'), 'La Guajira'),
((SELECT id FROM country WHERE code = 'CO'), 'Magdalena'),
((SELECT id FROM country WHERE code = 'CO'), 'Meta'),
((SELECT id FROM country WHERE code = 'CO'), 'Nariño'),
((SELECT id FROM country WHERE code = 'CO'), 'Norte de Santander'),
((SELECT id FROM country WHERE code = 'CO'), 'Putumayo'),
((SELECT id FROM country WHERE code = 'CO'), 'Quindío'),
((SELECT id FROM country WHERE code = 'CO'), 'Risaralda'),
((SELECT id FROM country WHERE code = 'CO'), 'San Andrés y Providencia'),
((SELECT id FROM country WHERE code = 'CO'), 'Santander'),
((SELECT id FROM country WHERE code = 'CO'), 'Sucre'),
((SELECT id FROM country WHERE code = 'CO'), 'Tolima'),
((SELECT id FROM country WHERE code = 'CO'), 'Valle del Cauca'),
((SELECT id FROM country WHERE code = 'CO'), 'Vaupés'),
((SELECT id FROM country WHERE code = 'CO'), 'Vichada');

-- Departments for Peru
INSERT INTO country_departments (country_id, department) VALUES
((SELECT id FROM country WHERE code = 'PE'), 'Amazonas'),
((SELECT id FROM country WHERE code = 'PE'), 'Áncash'),
((SELECT id FROM country WHERE code = 'PE'), 'Apurímac'),
((SELECT id FROM country WHERE code = 'PE'), 'Arequipa'),
((SELECT id FROM country WHERE code = 'PE'), 'Ayacucho'),
((SELECT id FROM country WHERE code = 'PE'), 'Cajamarca'),
((SELECT id FROM country WHERE code = 'PE'), 'Callao'),
((SELECT id FROM country WHERE code = 'PE'), 'Cusco'),
((SELECT id FROM country WHERE code = 'PE'), 'Huancavelica'),
((SELECT id FROM country WHERE code = 'PE'), 'Huánuco'),
((SELECT id FROM country WHERE code = 'PE'), 'Ica'),
((SELECT id FROM country WHERE code = 'PE'), 'Junín'),
((SELECT id FROM country WHERE code = 'PE'), 'La Libertad'),
((SELECT id FROM country WHERE code = 'PE'), 'Lambayeque'),
((SELECT id FROM country WHERE code = 'PE'), 'Lima'),
((SELECT id FROM country WHERE code = 'PE'), 'Loreto'),
((SELECT id FROM country WHERE code = 'PE'), 'Madre de Dios'),
((SELECT id FROM country WHERE code = 'PE'), 'Moquegua'),
((SELECT id FROM country WHERE code = 'PE'), 'Pasco'),
((SELECT id FROM country WHERE code = 'PE'), 'Piura'),
((SELECT id FROM country WHERE code = 'PE'), 'Puno'),
((SELECT id FROM country WHERE code = 'PE'), 'San Martín'),
((SELECT id FROM country WHERE code = 'PE'), 'Tacna'),
((SELECT id FROM country WHERE code = 'PE'), 'Tumbes'),
((SELECT id FROM country WHERE code = 'PE'), 'Ucayali');

-- Departments for Ecuador
INSERT INTO country_departments (country_id, department) VALUES
((SELECT id FROM country WHERE code = 'EC'), 'Azuay'),
((SELECT id FROM country WHERE code = 'EC'), 'Bolívar'),
((SELECT id FROM country WHERE code = 'EC'), 'Cañar'),
((SELECT id FROM country WHERE code = 'EC'), 'Carchi'),
((SELECT id FROM country WHERE code = 'EC'), 'Chimborazo'),
((SELECT id FROM country WHERE code = 'EC'), 'Cotopaxi'),
((SELECT id FROM country WHERE code = 'EC'), 'El Oro'),
((SELECT id FROM country WHERE code = 'EC'), 'Esmeraldas'),
((SELECT id FROM country WHERE code = 'EC'), 'Galápagos'),
((SELECT id FROM country WHERE code = 'EC'), 'Guayas'),
((SELECT id FROM country WHERE code = 'EC'), 'Imbabura'),
((SELECT id FROM country WHERE code = 'EC'), 'Loja'),
((SELECT id FROM country WHERE code = 'EC'), 'Los Ríos'),
((SELECT id FROM country WHERE code = 'EC'), 'Manabí'),
((SELECT id FROM country WHERE code = 'EC'), 'Morona Santiago'),
((SELECT id FROM country WHERE code = 'EC'), 'Napo'),
((SELECT id FROM country WHERE code = 'EC'), 'Orellana'),
((SELECT id FROM country WHERE code = 'EC'), 'Pastaza'),
((SELECT id FROM country WHERE code = 'EC'), 'Pichincha'),
((SELECT id FROM country WHERE code = 'EC'), 'Santa Elena'),
((SELECT id FROM country WHERE code = 'EC'), 'Santo Domingo de los Tsáchilas'),
((SELECT id FROM country WHERE code = 'EC'), 'Sucumbíos'),
((SELECT id FROM country WHERE code = 'EC'), 'Tungurahua'),
((SELECT id FROM country WHERE code = 'EC'), 'Zamora Chinchipe');

-- States for Mexico
INSERT INTO country_departments (country_id, department) VALUES
((SELECT id FROM country WHERE code = 'MX'), 'Aguascalientes'),
((SELECT id FROM country WHERE code = 'MX'), 'Baja California'),
((SELECT id FROM country WHERE code = 'MX'), 'Baja California Sur'),
((SELECT id FROM country WHERE code = 'MX'), 'Campeche'),
((SELECT id FROM country WHERE code = 'MX'), 'Chiapas'),
((SELECT id FROM country WHERE code = 'MX'), 'Chihuahua'),
((SELECT id FROM country WHERE code = 'MX'), 'Ciudad de México'),
((SELECT id FROM country WHERE code = 'MX'), 'Coahuila'),
((SELECT id FROM country WHERE code = 'MX'), 'Colima'),
((SELECT id FROM country WHERE code = 'MX'), 'Durango'),
((SELECT id FROM country WHERE code = 'MX'), 'Guanajuato'),
((SELECT id FROM country WHERE code = 'MX'), 'Guerrero'),
((SELECT id FROM country WHERE code = 'MX'), 'Hidalgo'),
((SELECT id FROM country WHERE code = 'MX'), 'Jalisco'),
((SELECT id FROM country WHERE code = 'MX'), 'México'),
((SELECT id FROM country WHERE code = 'MX'), 'Michoacán'),
((SELECT id FROM country WHERE code = 'MX'), 'Morelos'),
((SELECT id FROM country WHERE code = 'MX'), 'Nayarit'),
((SELECT id FROM country WHERE code = 'MX'), 'Nuevo León'),
((SELECT id FROM country WHERE code = 'MX'), 'Oaxaca'),
((SELECT id FROM country WHERE code = 'MX'), 'Puebla'),
((SELECT id FROM country WHERE code = 'MX'), 'Querétaro'),
((SELECT id FROM country WHERE code = 'MX'), 'Quintana Roo'),
((SELECT id FROM country WHERE code = 'MX'), 'San Luis Potosí'),
((SELECT id FROM country WHERE code = 'MX'), 'Sinaloa'),
((SELECT id FROM country WHERE code = 'MX'), 'Sonora'),
((SELECT id FROM country WHERE code = 'MX'), 'Tabasco'),
((SELECT id FROM country WHERE code = 'MX'), 'Tamaulipas'),
((SELECT id FROM country WHERE code = 'MX'), 'Tlaxcala'),
((SELECT id FROM country WHERE code = 'MX'), 'Veracruz'),
((SELECT id FROM country WHERE code = 'MX'), 'Yucatán'),
((SELECT id FROM country WHERE code = 'MX'), 'Zacatecas');
