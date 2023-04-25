CREATE TABLE product
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR (30),
    product_status VARCHAR (30),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
);
