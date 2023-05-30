ALTER TABLE product ADD COLUMN quantity_on_hand INTEGER DEFAULT 0 AFTER description;
UPDATE product SET quantity_on_hand=10 WHERE quantity_on_hand IS NULL;