CREATE TABLE order_line (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_header_id BIGINT,
    quantity_ordered INT,
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    CONSTRAINT order_header_fk FOREIGN KEY (order_header_id) REFERENCES order_header(id)
);