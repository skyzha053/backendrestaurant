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
INSERT INTO public.menu_item (id, available, description, name, price)
VALUES
    (1, true, 'Knoflooksaus Kruidenboter Olijftapenade', 'Mediterraans Landbrood', 7.00),
    (2, true, 'Pommodori Knoflook Basilicum Olijfolie', 'Bruschetta Pommodori', 10.00),
    (3, true, 'Pommodori Tallegio Carpaccio Zalm Parmaham', 'Bruschetta Classico', 10.00);

-- Voeg koppelingen tussen menu-items en allergieën toe
INSERT INTO public.menu_item_allergenen (menu_items_id, allergenen_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 5),
    (3, 11);
-- Voeg dranken toe aan de tabel 'drank'
INSERT INTO public.drank (id, naam, prijs, type)
VALUES
    (1, 'Cola', 2.50, 'Frisdrank'),
    (2, 'Fanta', 2.50, 'Frisdrank');


INSERT INTO public.reservation (id, allergies, bedrijfsnaam, comments, email_address, name, number_of_persons, phone_number, reservation_date)
VALUES
    (1, 'geen', NULL, 'Graag bij het raam', 'r.terweele@live.nl', 'Remon ter Weele', 9, '0638998771', '2024-05-05');


INSERT INTO public.roles (active, id, description, role_name)
VALUES
    (true, 2, 'Gebruikersrollen', 'ROLE_USER'),
    (true, 3, 'Chef-kok', 'ROLE_CHEFKOK'),
    (true, 1, 'Administrator rollen', 'ROLE_BAAS'),
    (true, 4, 'Serveerster', 'ROLE_SERVEERSTERS'),
    (true, 5, 'Restaurantmanager', 'ROLE_RESTAURANTMANAGER');



    INSERT INTO public.users (are_credentials_expired, is_enabled, is_expired, is_locked, id, password, user_name)
VALUES
    (false, true, false, false, 8, '$2a$04$CEO.nbew/ulIV8uxlg.FeegqtxgOCXsis47peCxM6d4SRHVngufGa', 'Remon'),
    (false, true, false, false, 9, '$2a$04$GSZGLOno9slqQ1O024d8WeZKIZgOwHdvhaAdCJ3daV3B9e3dzxzDy', 'Yente'),
    (false, true, false, false, 10, '$2a$04$GSZGLOno9slqQ1O024d8WeZKIZgOwHdvhaAdCJ3daV3B9e3dzxzDy', 'Emma'),
    (false, true, false, false, 11, '$2a$04$tsAvQUlJ56C4E0bk4MTYwumQCgZCbg3/jR1JwVoxtrK.bAegkttYe', 'Chikit');

INSERT INTO public.user_role (user_id, role_id)
VALUES
    (8, 1),
    (9, 5),
    (11, 3),
    (10, 4);
