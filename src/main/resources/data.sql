create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

//USERROLE

INSERT INTO UserRole (ID, VERSION, NAME, DESCRIPTION, ROLE_TYPE) VALUES (1,1, 'ADMIN','This is the administrator','ADMIN');
INSERT INTO UserRole  (ID, VERSION, NAME, DESCRIPTION, ROLE_TYPE) VALUES (2,1, 'SELLER','This is the Seller','SELLER');
INSERT INTO UserRole   (ID, VERSION, NAME, DESCRIPTION, ROLE_TYPE) VALUES (3,1, 'BUYER','This is the buyer','BUYER');

//USER

INSERT INTO User (id, version, firstName, lastName, username, password, user_role_id) VALUES(21, 1, 'Moses', 'Wasswa', 'mwasswa', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy', 1);
INSERT INTO User (id, version, firstName, lastName, username, password, user_status, user_role_id) VALUES(22, 1, 'Elias', 'Rurangwa', 'erurangwa', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy','APPROVED',2);
INSERT INTO User (id, version, firstName, lastName, username, password, user_role_id) VALUES(23, 1, 'Jirom', 'Jirom', 'jirom', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy',3);
INSERT INTO User (id, version, firstName, lastName, username, password, user_role_id) VALUES(24, 1, 'Erik', 'Erik', 'erik', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy',3);
INSERT INTO User (id, version, firstName, lastName, username, password, user_status, user_role_id) VALUES(25, 1, 'Moses', 'Wasswa', 'mwseller', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy','NEW',2);

//PRODUCT_CATEGORY

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
VALUES (21,1, '3 piece suit one button business wedding suits','File not found','Salvatore','PR12020','NEW','SN1',500,23,22);
INSERT INTO Product (ID, VERSION,DESCRIPTION,FILE,NAME,PRODUCT_NUMBER,PRODUCT_STATUS,SERIAL_NUMBER,UNIT_PRICE,PRODUCT_CATEGORY_ID,SELLER_ID)
VALUES (22,1, 'Samsung Galaxy Smartphone with both cameras','File not found','Samsung G7','PR22020','AVAILABLE','SN2',200,26,22);
INSERT INTO Product (ID, VERSION,DESCRIPTION,FILE,NAME,PRODUCT_NUMBER,PRODUCT_STATUS,SERIAL_NUMBER,UNIT_PRICE,PRODUCT_CATEGORY_ID,SELLER_ID)
VALUES (23,1, 'Dell Laptop with webcam','File not found','Dell Lattitude','PR32020','AVAILABLE','SN3',600,25,22);
INSERT INTO Product (ID, VERSION,DESCRIPTION,FILE,NAME,PRODUCT_NUMBER,PRODUCT_STATUS,SERIAL_NUMBER,UNIT_PRICE,PRODUCT_CATEGORY_ID,SELLER_ID)
VALUES (24,1, 'Shorts for gentlemen','File not found','Office Suits','PR42020','NEW','SN4',800,24,22);
INSERT INTO Product (ID, VERSION,DESCRIPTION,FILE,NAME,PRODUCT_NUMBER,PRODUCT_STATUS,SERIAL_NUMBER,UNIT_PRICE,PRODUCT_CATEGORY_ID,SELLER_ID)
VALUES (25,1, 'Sneakers for running and jogging','File not found','Addidas Fast Sneakers','PR52020','AVAILABLE','SN5',900,23,22);
INSERT INTO Product (ID, VERSION,DESCRIPTION,FILE,NAME,PRODUCT_NUMBER,PRODUCT_STATUS,SERIAL_NUMBER,UNIT_PRICE,PRODUCT_CATEGORY_ID,SELLER_ID)
VALUES (26,1, 'Refrigulators for multipurpose use','File not found','Samsung LP001','PR62020','AVAILABLE','SN6',400,23,22);
INSERT INTO Product (ID, VERSION,DESCRIPTION,FILE,NAME,PRODUCT_NUMBER,PRODUCT_STATUS,SERIAL_NUMBER,UNIT_PRICE,PRODUCT_CATEGORY_ID,SELLER_ID)
VALUES (27,1, 'Dell Laptop with webcam','File not found','Dell Lattitude','PR72020','AVAILABLE','SN7',600,25,22);


//ADDRESS
INSERT INTO ADDRESS (ID, VERSION,ADDRESS_ROLE,CITY,STATE,STREET,ZIP_CODE,USER_ID)
VALUES (3,1,'SHIPPING','Ottumwa','IA','2024 W 10TH ST',53661,23);
INSERT INTO ADDRESS (ID, VERSION,ADDRESS_ROLE,CITY,STATE,STREET,ZIP_CODE,USER_ID)
VALUES (4,1,'SHIPPING','Oskalousa','IA','4021 N 1TH ST',61831,24);
INSERT INTO ADDRESS (ID, VERSION,ADDRESS_ROLE,CITY,STATE,STREET,ZIP_CODE,USER_ID)
VALUES (5,1,'BILLING','Iowa city','IA','2222 N 33TH ST',52373,22);


//orders

INSERT INTO Orders (ID, VERSION,COUPONS,ORDER_NUMBER,ORDER_STATUS,TOTAL_AMOUNT,BUYER_ID,SHIPPING_ADDRESS_ID)
VALUES (1,1, 1.00,'OR12020','NEW',1000.00,23,3);
INSERT INTO Orders (ID, VERSION,COUPONS,ORDER_NUMBER,ORDER_STATUS,TOTAL_AMOUNT,BUYER_ID,SHIPPING_ADDRESS_ID)
VALUES (2,1, 1.00,'OR22020','PAID',500.00,24,4);
INSERT INTO Orders (ID, VERSION,COUPONS,ORDER_NUMBER,ORDER_STATUS,TOTAL_AMOUNT,BUYER_ID,SHIPPING_ADDRESS_ID)
VALUES (3,1, 1.00,'OR42020','NEW',10000.00,24,4);
INSERT INTO Orders (ID, VERSION,COUPONS,ORDER_NUMBER,ORDER_STATUS,TOTAL_AMOUNT,BUYER_ID,SHIPPING_ADDRESS_ID)
VALUES (4,1, 1.00,'OR42020','PAID',100.00,24,4);


//ORDER_LINE

INSERT INTO ORDER_LINE (ID, VERSION,AMOUNT,DESCRIPTION,QUANTITY,ORDER_ID,PRODUCT_ID)
VALUES (1,1,1000.00,'Samsung Galaxy Smartphone with both cameras',3.00,1,22);
INSERT INTO ORDER_LINE (ID, VERSION,AMOUNT,DESCRIPTION,QUANTITY,ORDER_ID,PRODUCT_ID)
VALUES (2,2,2000.00,'Sneakers for running and jogging',1.00,2,25);
INSERT INTO ORDER_LINE (ID, VERSION,AMOUNT,DESCRIPTION,QUANTITY,ORDER_ID,PRODUCT_ID)
VALUES (3,1,10000.00,'3 piece suit ',1.00,3,21);
INSERT INTO ORDER_LINE (ID, VERSION,AMOUNT,DESCRIPTION,QUANTITY,ORDER_ID,PRODUCT_ID)
VALUES (4,1,100.00,'Refregilators for multipurpose use',1.00,4,26);

//ORDER_HISTORY
INSERT INTO ORDER_HISTORY (ID, VERSION,DESCRIPTION,HISTORY_DATE,ORDER_STATUS,ORDER_ID,USER_ID)
VALUES (1,1,'Samsung Galaxy ','2020-05-14','NEW',1,23);
INSERT INTO ORDER_HISTORY (ID, VERSION,DESCRIPTION,HISTORY_DATE,ORDER_STATUS,ORDER_ID,USER_ID)
VALUES (2,2,'Addidas Fast Sneakers ','2020-05-20','PAID',2,24);
INSERT INTO ORDER_HISTORY (ID, VERSION,DESCRIPTION,HISTORY_DATE,ORDER_STATUS,ORDER_ID,USER_ID)
VALUES (3,1,'3 piece suit','2020-03-01','NEW',3,24);
INSERT INTO ORDER_HISTORY (ID, VERSION,DESCRIPTION,HISTORY_DATE,ORDER_STATUS,ORDER_ID,USER_ID)
VALUES (4,1,'Refrigulators for multipurpose use','2020-04-25','PAID',4,24);

//COUPON

INSERT INTO COUPON(ID, VERSION,COUPON_NUMBER,TOTAL_POINT,BUYER_ID)
VALUES (1,1,'CP12020',1,23);
INSERT INTO COUPON(ID, VERSION,COUPON_NUMBER,TOTAL_POINT,BUYER_ID)
VALUES (2,1,'CP22020',1,24);
INSERT INTO COUPON(ID, VERSION,COUPON_NUMBER,TOTAL_POINT,BUYER_ID)
VALUES (3,1,'CP32020',2,24);
INSERT INTO COUPON(ID, VERSION,COUPON_NUMBER,TOTAL_POINT,BUYER_ID)
VALUES (4,1,'CP42020',3,24);
