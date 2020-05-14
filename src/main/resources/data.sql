create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

INSERT INTO UserRole (ID, VERSION, NAME, DESCRIPTION, ROLE_TYPE) VALUES (1,1, 'ADMIN','This is the administrator','ADMIN');
INSERT INTO UserRole  (ID, VERSION, NAME, DESCRIPTION, ROLE_TYPE) VALUES (2,1, 'SELLER','This is the Seller','SELLER');
INSERT INTO UserRole   (ID, VERSION, NAME, DESCRIPTION, ROLE_TYPE) VALUES (3,1, 'BUYER','This is the buyer','BUYER');

INSERT INTO User (id, version, firstName, lastName, username, password, user_role_id) VALUES(21, 1, 'Moses', 'Wasswa', 'mwasswa', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy', 1);
INSERT INTO User (id, version, firstName, lastName, username, password, user_status, user_role_id) VALUES(22, 1, 'Elias', 'Rurangwa', 'erurangwa', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy','APPROVED',2);
INSERT INTO User (id, version, firstName, lastName, username, password, user_role_id) VALUES(23, 1, 'Jirom', 'Jirom', 'jirom', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy',3);
INSERT INTO User (id, version, firstName, lastName, username, password, user_role_id) VALUES(24, 1, 'Erik', 'Erik', 'erik', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy',3);
INSERT INTO User (id, version, firstName, lastName, username, password, user_status, user_role_id) VALUES(25, 1, 'Moses', 'Wasswa', 'mwseller', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy','NEW',2);


INSERT INTO product_category (id,version, description, name, quantity_available, quantity_purchased, seller_id)
VALUES(21,1,'Electronic Devices phones', 'Phones',100,20,22);

INSERT INTO product_category (id,version, description, name, quantity_available, quantity_purchased, seller_id)
VALUES(22,1,'Electronic Devices laptops', 'Laptops',120,10,22);

INSERT INTO product_category (id,version, description, name, quantity_available, quantity_purchased, seller_id)
VALUES(23,1,'Shirts and Shorts', 'Clothing',80,20,22);

INSERT INTO product_category(id,version, description, name, quantity_available, quantity_purchased, seller_id)
VALUES(24,1,'Kitchen equipment', 'Kitchen Equipment',113,16,22);

INSERT INTO product_category(id,version, description, name, quantity_available, quantity_purchased, seller_id)
VALUES(25,1,'Furniture', 'Furniture',110,17,22);

INSERT INTO product_category(id,version, description, name, quantity_available, quantity_purchased, seller_id)
VALUES(26,1,'School and Office Stationery', 'Stationery',100,20,22);

INSERT INTO Product (ID, VERSION,DESCRIPTION,FILE,NAME,PRODUCT_NUMBER,PRODUCT_STATUS,SERIAL_NUMBER,UNIT_PRICE,PRODUCT_CATEGORY_ID,SELLER_ID)
VALUES (21,1, '3 piece suit one button business wedding suits','File not found','Salvatore','PR12020','NEW','S1',500,21,22);
INSERT INTO Product (ID, VERSION,DESCRIPTION,FILE,NAME,PRODUCT_NUMBER,PRODUCT_STATUS,SERIAL_NUMBER,UNIT_PRICE,PRODUCT_CATEGORY_ID,SELLER_ID)
VALUES (22,1, 'Samsung Galaxy Smartphone with both cameras','File not found','Samsung G7','SR12020','AVAILABLE','SAM1',200,26,22);
INSERT INTO Product (ID, VERSION,DESCRIPTION,FILE,NAME,PRODUCT_NUMBER,PRODUCT_STATUS,SERIAL_NUMBER,UNIT_PRICE,PRODUCT_CATEGORY_ID,SELLER_ID)
VALUES (23,1, 'Dell Laptop with webcam','File not found','Dell Lattitude','DR12020','AVAILABLE','S1',600,25,22);
INSERT INTO Product (ID, VERSION,DESCRIPTION,FILE,NAME,PRODUCT_NUMBER,PRODUCT_STATUS,SERIAL_NUMBER,UNIT_PRICE,PRODUCT_CATEGORY_ID,SELLER_ID)
VALUES (24,1, 'Shorts for gentlemen','File not found','Office Suits','CR12020','NEW','S1',800,24,22);
INSERT INTO Product (ID, VERSION,DESCRIPTION,FILE,NAME,PRODUCT_NUMBER,PRODUCT_STATUS,SERIAL_NUMBER,UNIT_PRICE,PRODUCT_CATEGORY_ID,SELLER_ID)
VALUES (25,1, 'Sneakers for running and jogging','File not found','Addidas Fast Sneakers','AR12020','AVAILABLE','S1',900,23,22);
INSERT INTO Product (ID, VERSION,DESCRIPTION,FILE,NAME,PRODUCT_NUMBER,PRODUCT_STATUS,SERIAL_NUMBER,UNIT_PRICE,PRODUCT_CATEGORY_ID,SELLER_ID)
VALUES (26,1, 'Refrigulators for multipurpose use','File not found','Samsung LP001','LP001','AVAILABLE','S1',400,23,22);
INSERT INTO Product (ID, VERSION,DESCRIPTION,FILE,NAME,PRODUCT_NUMBER,PRODUCT_STATUS,SERIAL_NUMBER,UNIT_PRICE,PRODUCT_CATEGORY_ID,SELLER_ID)
VALUES (27,1, 'Dell Laptop with webcam','File not found','Dell Lattitude','PR12020','AVAILABLE','SAM1',600,25,22);