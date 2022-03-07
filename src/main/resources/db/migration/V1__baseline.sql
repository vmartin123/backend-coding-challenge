CREATE TABLE address (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  street varchar(45) NOT NULL,
  city varchar(45) NOT NULL,
  state char(2) NOT NULL,
  zip char(5) NOT NULL,
  timezone varchar(45) DEFAULT NULL
);

CREATE TABLE property (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  create_time datetime DEFAULT NULL,
  type varchar(45) DEFAULT NULL,
  rent_price DECIMAL(20, 2) NOT NULL,
  address_id INT UNSIGNED DEFAULT NULL,
  email_address varchar(45) DEFAULT NULL,
  code char(10) DEFAULT NULL,
  CONSTRAINT address_id FOREIGN KEY (address_id) REFERENCES address (id),
  UNIQUE (address_id)
);

INSERT INTO address VALUES (1,'belgrano','chicago','IL','1200','CST');

INSERT INTO property VALUES
(1, '2022-03-06 09:27:03', 'TOWN_HOUSE',   1.11, NULL, 'vic@gmail.com','A1'),
(2, '2022-03-06 09:27:03', 'MULTI_FAMILY', 2.22, NULL, 'vic@gmail.com','A1'),
(3, '2022-03-06 09:27:03', 'CONDOMINIUM',  3.33, NULL, 'vic@gmail.com','A1'),
(4, '2022-03-06 09:27:03', 'TOWN_HOUSE',   4.44, NULL, 'vic@gmail.com','A1'),
(5, '2022-03-06 09:27:03', 'MULTI_FAMILY', 5.56, 1,    'vic@gmail.com','A1');
