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
