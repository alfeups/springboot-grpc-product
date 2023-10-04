CREATE TABLE PRODUCT (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         nome VARCHAR(255) NOT NULL,
                         price FLOAT NOT NULL,
                         quantity_in_stock INTEGER NOT NULL,
                         CONSTRAINT id UNIQUE (id)
);

INSERT INTO product (id, nome, price, quantity_in_stock) VALUES (1, 'Product A', 10.99, 10);
INSERT INTO product (id, nome, price, quantity_in_stock) VALUES (2, 'Product B', 10.99, 10);