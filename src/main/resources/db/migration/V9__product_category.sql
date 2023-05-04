CREATE TABLE category
(
    id                 BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description        VARCHAR(50),
    created_date       TIMESTAMP,
    last_modified_date TIMESTAMP
);

CREATE TABLE product_category
(
    product_id  BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT pc_product_id_fk FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT pc_category_id_fk FOREIGN KEY (category_id) REFERENCES category (id)
);

INSERT INTO product (description, product_status, created_date, last_modified_date)
VALUES ('PRODUCT1', 'NEW', NOW(), NOW()),
       ('PRODUCT2', 'NEW', NOW(), NOW()),
       ('PRODUCT3', 'NEW', NOW(), NOW()),
       ('PRODUCT4', 'NEW', NOW(), NOW());

INSERT INTO category (description, created_date, last_modified_date)
VALUES ('CAT1', NOW(), NOW()),
       ('CAT2', NOW(), NOW()),
       ('CAT3', NOW(), NOW());

INSERT INTO product_category (product_id, category_id)
SELECT p.id, c.id
FROM product p,
     category c
WHERE p.description = 'PRODUCT1'
  AND c.description = 'CAT1';

INSERT INTO product_category (product_id, category_id)
SELECT p.id, c.id
FROM product p,
     category c
WHERE p.description = 'PRODUCT2'
  AND c.description = 'CAT1';

INSERT INTO product_category (product_id, category_id)
SELECT p.id, c.id
FROM product p,
     category c
WHERE p.description = 'PRODUCT1'
  AND c.description = 'CAT3';

INSERT INTO product_category (product_id, category_id)
SELECT p.id, c.id
FROM product p,
     category c
WHERE p.description = 'PRODUCT4'
  AND c.description = 'CAT3';