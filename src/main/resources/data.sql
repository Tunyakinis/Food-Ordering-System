INSERT INTO cuisine (id, name)
VALUES (1, 'Polish'),
       (2, 'Mexican'),
       (3, 'Italian');

INSERT INTO meal (cuisine_id, name, price, type)
VALUES (1, 'Pierogi', 10, 'main course'),
       (1, 'Golabki', 12, 'main course'),
       (1, 'Bigos', 17, 'main course'),
       (1, 'Gulasz', 20, 'main course'),
       (1, 'Kluski', 11, 'main course'),
       (2, 'Burritos', 20, 'main course'),
       (2, 'Tacos', 14, 'main course'),
       (2, 'Gorditas', 9, 'main course'),
       (2, 'Tamales', 6, 'main course'),
       (2, 'Chilaquiles', 13, 'main course'),
       (3, 'Pizza', 10, 'main course'),
       (3, 'Pasta', 18, 'main course'),
       (3, 'Lasagna', 13, 'main course'),
       (3, 'Ravioli', 16, 'main course'),
       (3, 'Focaccia', 8, 'main course'),
       (1, 'Szarlotka', 3, 'dessert'),
       (1, 'Mazurek', 6, 'dessert'),
       (1, 'Wuzetka', 5, 'dessert'),
       (1, 'Kremowka', 4, 'dessert'),
       (1, 'Babka', 6, 'dessert'),
       (2, 'Capirotada', 2, 'dessert'),
       (2, 'Conchas', 3, 'dessert'),
       (2, 'Sopaipillas', 5, 'dessert'),
       (2, 'Bunuelos', 4, 'dessert'),
       (2, 'Flan', 3, 'dessert'),
       (3, 'Tiramisu', 6, 'dessert'),
       (3, 'Pizzele', 3, 'dessert'),
       (3, 'Biscotti', 6, 'dessert'),
       (3, 'Zabaglione', 5, 'dessert'),
       (3, 'Bomboloni', 3, 'dessert');

INSERT INTO drink (name, price)
VALUES ('Fruit tea', 3),
       ('Green tea', 3),
       ('Black tea', 3),
       ('Latte', 5),
       ('Americano', 5);