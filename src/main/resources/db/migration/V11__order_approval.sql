CREATE TABLE order_approval
(
    id                 BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    approved_by        VARCHAR(50),
    created_date       TIMESTAMP,
    last_modified_date TIMESTAMP
);

ALTER TABLE order_header
    ADD COLUMN order_approval_id BIGINT,
    ADD CONSTRAINT order_approval_fk FOREIGN KEY (order_approval_id) REFERENCES order_approval (id);