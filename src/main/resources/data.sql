create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

INSERT INTO UserRole (VERSION, ID, NAME, DESCRIPTION, ROLE_TYPE) VALUES (1,1,'ADMIN','This is the administrator','ADMIN');
INSERT INTO UserRole (VERSION, ID, NAME, DESCRIPTION, ROLE_TYPE) VALUES (1,2,'SELLER','This is the administrator','SELLER');
INSERT INTO UserRole (VERSION, ID, NAME, DESCRIPTION, ROLE_TYPE) VALUES (1,3,'BUYER','This is the administrator','BUYER');
