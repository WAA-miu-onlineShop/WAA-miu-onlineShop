create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

INSERT INTO UserRole (ID, VERSION, NAME, DESCRIPTION, ROLE_TYPE) VALUES (1,1, 'ADMIN','This is the administrator','ADMIN');
INSERT INTO UserRole  (ID, VERSION, NAME, DESCRIPTION, ROLE_TYPE) VALUES (2,1, 'SELLER','This is the Seller','SELLER');
INSERT INTO UserRole   (ID, VERSION, NAME, DESCRIPTION, ROLE_TYPE) VALUES (3,1, 'BUYER','This is the buyer','BUYER');

INSERT INTO User (id, firstName, lastName, username, password, user_role_id) VALUES(21, 'Moses', 'Wasswa', 'mwasswa', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy', 1);
INSERT INTO User (id, firstName, lastName, username, password, user_role_id) VALUES(22, 'Elias', 'Rurangwa', 'erurangwa', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy',2);
INSERT INTO User (id, firstName, lastName, username, password, user_role_id) VALUES(23, 'Jirom', 'Jirom', 'jirom', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy',3);
INSERT INTO User (id, firstName, lastName, username, password, user_role_id) VALUES(24, 'Erik', 'Erik', 'erik', '$2a$10$kAsVIEI4C0xcEp2abBVM.uzrsfe94lWMAb2wy8pF9jB5031cJWwIy',3);
