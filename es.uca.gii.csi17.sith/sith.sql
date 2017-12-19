CREATE TABLE IF NOT EXISTS ticket (
	id int(10) NOT NULL AUTO_INCREMENT,
	fecha datetime NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS raza (
	id int(32) NOT NULL AUTO_INCREMENT,
	nombre varchar(64) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cliente (
	id int(10) NOT NULL AUTO_INCREMENT,
	nombre varchar(64) NOT NULL UNIQUE,
	id_Raza int(32) NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (id_Raza) REFERENCES raza(id) ON DELETE RESTRICT
);

#INSERT INTO ticket VALUES(1, '2010-10-10 01:02:03');
#INSERT INTO ticket VALUES(2, '2011-11-11 01:02:03');

INSERT INTO cliente(nombre, id_Raza) VALUES('javi', 1);
INSERT INTO cliente(nombre, id_Raza) VALUES('george', 1);
INSERT INTO raza(nombre) VALUES('wookiee');
INSERT INTO raza(nombre) VALUES('humano');
