
DROP TABLE IF EXISTS merchant;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS dedup;

CREATE TABLE merchant (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  merchant_id VARCHAR(250) NOT NULL
);

INSERT INTO merchant(merchant_id) VALUES (101);
INSERT INTO merchant(merchant_id) VALUES (102);
INSERT INTO merchant(merchant_id) VALUES (103);
INSERT INTO merchant(merchant_id) VALUES (104);
INSERT INTO merchant(merchant_id) VALUES (105);


CREATE TABLE items (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  merchant_id VARCHAR(250) NOT NULL,
  item_id VARCHAR(250) NOT NULL,
  item_count INT NOT NULL,
  version INT
);

INSERT INTO items(merchant_id, item_id, item_count, version) VALUES (101,1,1000,0);
INSERT INTO items(merchant_id, item_id, item_count, version) VALUES (101,2,1000,0);
INSERT INTO items(merchant_id, item_id, item_count, version) VALUES (102,1,1000,0);
INSERT INTO items(merchant_id, item_id, item_count, version) VALUES (102,2,1000,0);


CREATE TABLE transactions (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  merchant_id VARCHAR(250) NOT NULL,
  transaction_id VARCHAR(250) NOT NULL,
  transaction_amount VARCHAR(250) NOT NULL, 
  transaction_currency VARCHAR(250) NOT NULL,
  card_number VARCHAR(250) NOT NULL,
  card_expirydate VARCHAR(250) NOT NULL,
  card_csv VARCHAR(250) NOT NULL,
  item_id INT NOT NULL,
  item_count INT NOT NULL
);


CREATE TABLE dedup (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  correlation_id VARCHAR(250) NOT NULL,
  transaction_id VARCHAR(250) NOT NULL
);


