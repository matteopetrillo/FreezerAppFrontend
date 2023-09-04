/*
 Inserisco Fake Data
 */
INSERT INTO item (item_category, name)
VALUES
('CAVI','SDI 10m'),
('CAVI','SDI 50m'),
('CAMERA','Z150'),
('REGIA','Atem Extreme'),
('REGIA','Atem 2 M/E'),
('CAMERA','Blackmagic Pocket 6K');

/*
 In base ai fake data inseriti ottengo l'id e imposto le quantità
 */
INSERT INTO item_inventory (available_quantity, total_quantity, item_id)
VALUES (10, 10, (SELECT id FROM item WHERE name = 'SDI 10m'));

INSERT INTO item_inventory (available_quantity, total_quantity, item_id)
VALUES (5, 5, (SELECT id FROM item WHERE name = 'SDI 50m'));

INSERT INTO item_inventory (available_quantity, total_quantity, item_id)
VALUES (7, 7, (SELECT id FROM item WHERE name = 'Z150'));

INSERT INTO item_inventory (available_quantity, total_quantity, item_id)
VALUES (1, 1, (SELECT id FROM item WHERE name = 'Atem Extreme'));

INSERT INTO item_inventory (available_quantity, total_quantity, item_id)
VALUES (1, 1, (SELECT id FROM item WHERE name = 'Atem 2 M/E'));

INSERT INTO item_inventory (available_quantity, total_quantity, item_id)
VALUES (2, 2, (SELECT id FROM item WHERE name = 'Blackmagic Pocket 6K'));

/*
 Inserisco delle fake prenotazioni sempre ottenendo l'id dal nome
 */
INSERT INTO item_rented (ending_date, quantity, starting_date, item_id)
VALUES ('2023-01-05', 2, '2023-01-02',(SELECT id FROM item WHERE name = 'SDI 10m'));

INSERT INTO item_rented (ending_date, quantity, starting_date, item_id)
VALUES ('2023-01-06', 1, '2023-01-03',(SELECT id FROM item WHERE name = 'SDI 10m'));

INSERT INTO item_rented (ending_date, quantity, starting_date, item_id)
VALUES ('2023-01-07', 1, '2023-01-04',(SELECT id FROM item WHERE name = 'SDI 50m'));

INSERT INTO item_rented (ending_date, quantity, starting_date, item_id)
VALUES ('2023-01-08', 2, '2023-01-05',(SELECT id FROM item WHERE name = 'Z150'));

INSERT INTO item_rented (ending_date, quantity, starting_date, item_id)
VALUES ('2023-01-10', 1, '2023-01-07',(SELECT id FROM item WHERE name = 'Atem 2 M/E'));

INSERT INTO item_rented (ending_date, quantity, starting_date, item_id)
VALUES ('2023-01-11', 2, '2023-01-08',(SELECT id FROM item WHERE name = 'Blackmagic Pocket 6K'));






