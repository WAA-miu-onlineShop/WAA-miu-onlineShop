create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

INSERT INTO UserRole (ID, VERSION, NAME, DESCRIPTION, ROLE_TYPE) VALUES (1,1, 'ADMIN','This is the administrator','ADMIN');
INSERT INTO UserRole  (ID, VERSION, NAME, DESCRIPTION, ROLE_TYPE) VALUES (2,1, 'SELLER','This is the Seller','SELLER');
INSERT INTO UserRole   (ID, VERSION, NAME, DESCRIPTION, ROLE_TYPE) VALUES (3,1, 'BUYER','This is the buyer','BUYER');
