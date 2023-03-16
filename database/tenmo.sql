BEGIN TRANSACTION;

<<<<<<< HEAD
DROP TABLE IF EXISTS transfer, transfer_status, account, tenmo_user;
=======
DROP TABLE IF EXISTS transfer, account, tenmo_user, transfer_status, transfer_type;
>>>>>>> a66e4320870def4fae8e263d2020bcc12cd54b9c

DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id, seq_transfer_id;

CREATE TABLE transfer_type (
	transfer_type_id serial NOT NULL,
	transfer_type_desc varchar(10) NOT NULL,
	CONSTRAINT PK_transfer_type PRIMARY KEY (transfer_type_id)
);

CREATE TABLE transfer_status (
	transfer_status_id serial NOT NULL,
	transfer_status_desc varchar(10) NOT NULL,
	CONSTRAINT PK_transfer_status PRIMARY KEY (transfer_status_id)
);

-- Sequence to start user_id values at 1001 instead of 1
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

-- Sequence to start account_id values at 2001 instead of 1
-- Note: Use similar sequences with unique starting values for additional tables
CREATE SEQUENCE seq_account_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;

CREATE TABLE account (
	account_id int NOT NULL DEFAULT nextval('seq_account_id'),
	user_id int NOT NULL,
	balance decimal(13, 2) NOT NULL,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_tenmo_user FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id)
);

<<<<<<< HEAD
CREATE TABLE transfer_status (
	status_id serial NOT NULL,
	status_description varchar(10) NOT NULL,
	CONSTRAINT PK_transfer_status PRIMARY KEY (status_id)
);
	
CREATE TABLE transfer (
	transfer_id serial NOT NULL,
	to_account_id int NOT NULL,
	from_account_id int NOT NULL,
	amount decimal(13, 2) NOT NULL,
	status_id int NOT NULL,
	CONSTRAINT PK_transfer PRIMARY KEY (transfer_id),
	CONSTRAINT FK_transfer_account_to FOREIGN KEY (to_account_id) REFERENCES account (account_id),
	CONSTRAINT FK_transfer_account FOREIGN KEY (from_account_id) REFERENCES account (account_id),
	CONSTRAINT FK_transfer_transfer_status FOREIGN KEY (status_id) REFERENCES transfer_status (status_id)
=======
CREATE SEQUENCE seq_transfer_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE;
	
CREATE TABLE transfer (
	transfer_id int NOT NULL DEFAULT nextval('seq_transfer_id'),
	transfer_type_id int NOT NULL,
	transfer_status_id int NOT NULL,
	from_account_id int NOT NULL,
	to_account_id int NOT NULL,
	amount decimal(13, 2) NOT NULL,
	CONSTRAINT PK_transfer PRIMARY KEY (transfer_id),
	CONSTRAINT FK_transfer_account FOREIGN KEY (to_account_id) REFERENCES account (account_id),
	CONSTRAINT FK_transfer_account_to FOREIGN KEY (from_account_id) REFERENCES account (account_id),
	CONSTRAINT FK_transfer_transfer_status FOREIGN KEY (transfer_status_id) REFERENCES transfer_status (transfer_status_id),
	CONSTRAINT FK_transfer_transfer_type FOREIGN KEY (transfer_type_id) REFERENCES transfer_type (transfer_type_id)
>>>>>>> a66e4320870def4fae8e263d2020bcc12cd54b9c
);


COMMIT;
