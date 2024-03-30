-- Voeg allergieën toe
INSERT INTO public.allergie (id, naam)
VALUES
    (1, 'gluten'),
    (2, 'noten'),
    (3, 'ei'),
    (4, 'lupine'),
    (5, 'melk'),
    (6, 'sesamzaad'),
    (7, 'zwaveldioxide'),
    (8, 'selderij'),
    (9, 'pindas'),
    (10, 'mosterd'),
    (11, 'vis'),
    (12, 'weekdieren'),
    (13, 'soja'),
    (14, 'schaaldieren');

-- Voeg menu-items toe
INSERT INTO public.menu_item (available, description, name, price) VALUES
(true, 'Knoflooksaus Kruidenboter Olijftapenade', 'Mediterraans Landbrood', 7.00),
(true, 'Pommodori Knoflook Basilicum Olijfolie', 'Bruschetta Pommodori', 10.00),
(true, 'Pommodori Tallegio Carpaccio Zalm Parmaham', 'Bruschetta Classico', 10.00);

-- Voeg koppelingen tussen menu-items en allergieën toe
INSERT INTO public.menu_item_allergenen (menu_items_id, allergenen_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 5),
    (3, 11);
-- Voeg dranken toe aan de tabel 'drank'
INSERT INTO public.drank ( naam, prijs, type)
VALUES
    ( 'Cola', 2.50, 'Frisdrank'),
    ( 'Fanta', 2.50, 'Frisdrank');


INSERT INTO public.reservation ( allergies, bedrijfsnaam, comments, email_address, name, number_of_persons, phone_number, reservation_date)
VALUES
    ( 'geen', NULL, 'Graag bij het raam', 'r.terweele@live.nl', 'Remon ter Weele', 9, '0638998771', '2024-05-05');


INSERT INTO public.roles (active, description, role_name)
VALUES
    (true,  'Administrator rollen', 'ROLE_BAAS'),
    (true,  'Restaurantmanager', 'ROLE_RESTAURANTMANAGER'),
    (true,  'Serveerster', 'ROLE_SERVEERSTERS'),
    (true,  'Chef-kok', 'ROLE_CHEFKOK');




    INSERT INTO public.users (are_credentials_expired, is_enabled, is_expired, is_locked,  password, user_name)
VALUES
    (false, true, false, false,  '$2a$04$CEO.nbew/ulIV8uxlg.FeegqtxgOCXsis47peCxM6d4SRHVngufGa', 'Remon'),
    (false, true, false, false,  '$2a$04$GSZGLOno9slqQ1O024d8WeZKIZgOwHdvhaAdCJ3daV3B9e3dzxzDy', 'Yente'),
    (false, true, false, false,  '$2a$04$GSZGLOno9slqQ1O024d8WeZKIZgOwHdvhaAdCJ3daV3B9e3dzxzDy', 'Emma'),
    (false, true, false, false,  '$2a$04$tsAvQUlJ56C4E0bk4MTYwumQCgZCbg3/jR1JwVoxtrK.bAegkttYe', 'Chikit');

INSERT INTO public.user_role (user_id, role_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4);