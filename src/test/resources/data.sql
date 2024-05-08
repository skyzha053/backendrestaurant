-- Tabel voor allergieën
CREATE TABLE IF NOT EXISTS allergie (
                                        id BIGINT PRIMARY KEY,
                                        naam VARCHAR(255) NOT NULL
    );

-- Tabel voor menu-items
CREATE TABLE IF NOT EXISTS menu_item (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         available BOOLEAN NOT NULL,
                                         description VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
    );

-- Tabel voor de koppeling tussen menu-items en allergieën
CREATE TABLE IF NOT EXISTS menu_item_allergenen (
                                                    menu_item_id BIGINT NOT NULL,
                                                    allergie_id BIGINT NOT NULL,
                                                    FOREIGN KEY (menu_item_id) REFERENCES menu_item(id),
    FOREIGN KEY (allergie_id) REFERENCES allergie(id),
    PRIMARY KEY (menu_item_id, allergie_id)
    );

-- Tabel voor dranken
CREATE TABLE IF NOT EXISTS drank (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     naam VARCHAR(255) NOT NULL,
    prijs DECIMAL(10, 2) NOT NULL,
    type VARCHAR(255) NOT NULL
    );

-- Tabel voor reserveringen
CREATE TABLE IF NOT EXISTS reservation (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           allergies VARCHAR(255),
    bedrijfsnaam VARCHAR(255),
    comments VARCHAR(255),
    email_address VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    number_of_persons INT NOT NULL,
    phone_number VARCHAR(20),
    reservation_date DATE NOT NULL
    );

-- Tabel voor rollen
CREATE TABLE IF NOT EXISTS roles (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     active BOOLEAN NOT NULL,
                                     description VARCHAR(255),
    role_name VARCHAR(255) NOT NULL
    );

-- Tabel voor gebruikers
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     are_credentials_expired BOOLEAN NOT NULL,
                                     is_enabled BOOLEAN NOT NULL,
                                     is_expired BOOLEAN NOT NULL,
                                     is_locked BOOLEAN NOT NULL,
                                     password VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL
    );

-- Tabel voor de koppeling tussen gebruikers en rollen
CREATE TABLE IF NOT EXISTS user_role (
                                         user_id BIGINT NOT NULL,
                                         role_id BIGINT NOT NULL,
                                         FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
    );

-- Voeg allergieën toe
INSERT INTO allergie (id, naam)
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
INSERT INTO menu_item (available, description, name, price) VALUES
                                                                (true, 'Knoflooksaus Kruidenboter Olijftapenade', 'Mediterraans Landbrood', 7.00),
                                                                (true, 'Pommodori Knoflook Basilicum Olijfolie', 'Bruschetta Pommodori', 10.00),
                                                                (true, 'Pommodori Tallegio Carpaccio Zalm Parmaham', 'Bruschetta Classico', 10.00);

-- Voeg koppelingen tussen menu-items en allergieën toe
INSERT INTO menu_item_allergenen (menu_item_id, allergie_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 5),
    (3, 11);

-- Voeg dranken toe aan de tabel 'drank'
INSERT INTO drank (naam, prijs, type)
VALUES
    ('Cola', 2.50, 'Frisdrank'),
    ('Fanta', 2.50, 'Frisdrank');

-- Voeg reservering toe
INSERT INTO reservation (allergies, bedrijfsnaam, comments, email_address, name, number_of_persons, phone_number, reservation_date)
VALUES
    ('geen', NULL, 'Graag bij het raam', 'r.terweele@live.nl', 'Remon ter Weele', 9, '0638998771', '2024-05-05');

-- Voeg rollen toe
INSERT INTO roles (active, description, role_name)
VALUES
    (true, 'Administrator rollen', 'ROLE_BAAS'),
    (true, 'Restaurantmanager', 'ROLE_RESTAURANTMANAGER'),
    (true, 'Serveerster', 'ROLE_SERVEERSTERS'),
    (true, 'Chef-kok', 'ROLE_CHEFKOK');

-- Voeg gebruikers toe
INSERT INTO users (are_credentials_expired, is_enabled, is_expired, is_locked, password, user_name)
VALUES
    (false, true, false, false, '$2a$04$CEO.nbew/ulIV8uxlg.FeegqtxgOCXsis47peCxM6d4SRHVngufGa', 'Remon'),
    (false, true, false, false, '$2a$04$GSZGLOno9slqQ1O024d8WeZKIZgOwHdvhaAdCJ3daV3B9e3dzxzDy', 'Yente'),
    (false, true, false, false, '$2a$04$GSZGLOno9slqQ1O024d8WeZKIZgOwHdvhaAdCJ3daV3B9e3dzxzDy', 'Emma'),
    (false, true, false, false, '$2a$04$tsAvQUlJ56C4E0bk4MTYwumQCgZCbg3/jR1JwVoxtrK.bAegkttYe', 'Chikit');

-- Koppel gebruikers aan rollen
INSERT INTO user_role (user_id, role_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4);
