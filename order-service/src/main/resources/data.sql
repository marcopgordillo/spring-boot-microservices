-- DELETE FROM orders;
-- DELETE FROM order_items;

insert into orders(customer_email, customer_address) VALUES
('cliente1@mail.com', 'Av. 10 de Agosto'),
('cliente2@mail.com', 'Av. Patria');

insert into order_items(product_id, order_id, quantity, product_price) VALUES
(1, 1, 3, 34.50),
(2, 1, 1, 124.50);
