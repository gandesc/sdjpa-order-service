ALTER TABLE order_approval
    ADD COLUMN order_header_id BIGINT,
    ADD CONSTRAINT order_approval_order_header_fk FOREIGN KEY (order_header_id) REFERENCES order_header (id);