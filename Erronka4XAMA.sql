DROP DATABASE IF EXISTS IndiUsurbil;

-- Datu-basea sortu eta hautatu
CREATE DATABASE IF NOT EXISTS IndiUsurbil;
USE IndiUsurbil;

-- 1. KATEGORIAK: Produktuak sailkatzeko
CREATE TABLE KATEGORIAK (
    id INT AUTO_INCREMENT PRIMARY KEY,
    izena VARCHAR(100) NOT NULL UNIQUE
);

-- 2. ERABILTZAILEAK: Webguneko bezeroak
CREATE TABLE ERABILTZAILEAK (
    id INT AUTO_INCREMENT PRIMARY KEY,
    izena VARCHAR(100) NOT NULL,
    emaila VARCHAR(150) UNIQUE NOT NULL,
    pasahitza VARCHAR(255) NOT NULL,
    sorkuntza_data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. PRODUKTUAK: Oinarrizko informazioa
CREATE TABLE PRODUKTUAK (
    id INT AUTO_INCREMENT PRIMARY KEY,
    izena VARCHAR(150) NOT NULL,
    deskribapena TEXT,
    irudia_url varchar(255),
    prezioa DECIMAL(10, 2) NOT NULL,
    stocka INT NOT NULL DEFAULT 0,
    sorkuntza_data DATE NOT NULL,
    kategoria_id INT,
    CONSTRAINT fk_prod_kat FOREIGN KEY (kategoria_id) 
        REFERENCES KATEGORIAK(id) ON DELETE SET NULL
);

-- 4. ESKAERAK: Eskaeren jarraipena egiteko
CREATE TABLE ESKAERAK (
    id INT AUTO_INCREMENT PRIMARY KEY,
    erabiltzaile_id INT,
    eskaera_data DATETIME DEFAULT CURRENT_TIMESTAMP,
    guztira_prezioa DECIMAL(10, 2) DEFAULT 0.00,
    CONSTRAINT fk_eskaera_erab FOREIGN KEY (erabiltzaile_id) 
        REFERENCES ERABILTZAILEAK(id) ON DELETE CASCADE
);

-- 5. ESKAERA_LERROAK: Eskaera bakoitzeko produktuak eta kopurua
CREATE TABLE ESKAERA_LERROAK (
    eskaera_id INT,
    produktu_id INT,
    kopurua INT NOT NULL,
    unitate_prezioa DECIMAL(10, 2) NOT NULL, -- Erosketa unean zuen prezioa gordetzeko
    PRIMARY KEY (eskaera_id, produktu_id),
    CONSTRAINT fk_lerroa_eskaera FOREIGN KEY (eskaera_id) REFERENCES ESKAERAK(id),
    CONSTRAINT fk_lerroa_prod FOREIGN KEY (produktu_id) REFERENCES PRODUKTUAK(id)
);

INSERT INTO KATEGORIAK (izena) VALUES 
("men's clothing"),
("jewelery"),
("electronics"),
("women's clothing");

-- Gure produktuak sartuko ditugu banan-banan
INSERT INTO PRODUKTUAK (izena, deskribapena, prezioa, stocka, sorkuntza_data, kategoria_id) VALUES 

-- Men's Clothing
('Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops', 'Your perfect pack for everyday use and walks in the forest.', 109.95, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "men's clothing")),
('Mens Casual Premium Slim Fit T-Shirts', 'Slim-fitting style, contrast raglan long sleeve...', 22.3, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "men's clothing")),
('Mens Cotton Jacket', 'Great outerwear jackets for Spring/Autumn/Winter...', 55.99, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "men's clothing")),
('Mens Casual Slim Fit', 'The color could be slightly different between on the screen and in practice.', 15.99, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "men's clothing")),

-- Jewelery
('John Hardy Women\'s Legends Naga Gold & Silver Dragon Bracelet', 'From our Legends Collection, inspired by the mythical water dragon.', 695.00, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "jewelery")),
('Solid Gold Petite Micropave', 'Satisfaction Guaranteed. Return or exchange any order within 30 days.', 168.00, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "jewelery")),
('White Gold Plated Princess', 'Classic Created Wedding Engagement Solitaire Diamond Promise Ring.', 9.99, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "jewelery")),
('Pierced Owl Rose Gold Plated Stainless Steel Double', 'Rose Gold Plated Double Flared Tunnel Plug Earrings.', 10.99, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "jewelery")),

-- Electronics
('WD 2TB Elements Portable External Hard Drive - USB 3.0', 'USB 3.0 and USB 2.0 Compatibility, Fast data transfers.', 64.00, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "electronics")),
('SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s', 'Easy upgrade for faster boot up, shutdown, application load.', 109.00, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "electronics")),
('Silicon Power 256GB SSD 3D NAND A55', '3D NAND flash are applied to deliver high transfer speeds.', 109.00, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "electronics")),
('WD 4TB Gaming Drive Works with Playstation 4', 'Expand your PS4 gaming experience, Play anywhere Fast and easy.', 114.00, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "electronics")),
('Acer SB220Q bi 21.5 inches Full HD IPS', '21.5 inches Full HD widescreen IPS display.', 599.00, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "electronics")),
('Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor', '49 INCH SUPER ULTRAWIDE 32:9 CURVED GAMING MONITOR.', 999.99, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "electronics")),

-- Women's Clothing
('BIYLACLESEN Women\'s 3-in-1 Snowboard Jacket', 'Material: 100% Polyester; Detachable Liner Fabric: Warm Fleece.', 56.99, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "women's clothing")),
('Lock and Love Women\'s Removable Hooded Jacket', 'Faux leather material for style and comfort.', 29.95, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "women's clothing")),
('Rain Jacket Women Windbreaker Striped Climbing Raincoats', 'Lightweight perfect for trip or casual wear.', 39.99, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "women's clothing")),
('MBJ Women\'s Solid Short Sleeve Boat Neck V', '95% RAYON 5% SPANDEX, Lightweight fabric with great stretch.', 9.85, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "women's clothing")),
('Opna Women\'s Short Sleeve Moisture', '100% cationic polyester interlock, Machine Wash & Pre Shrunk.', 7.95, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "women's clothing")),
('DANVOUY Womens T Shirt Casual Cotton Short', '95% Cotton, 5% Spandex, soft and has some stretch.', 12.99, 100, CURDATE(), (SELECT id FROM KATEGORIAK WHERE izena = "women's clothing"));

SELECT p.id, p.izena, p.prezioa, p.deskribapena, k.izena AS kategoria
FROM PRODUKTUAK p
JOIN KATEGORIAK k ON p.kategoria_id = k.id
ORDER BY k.izena;