CREATE TABLE IF NOT EXISTS ticket (
	id int(10) NOT NULL AUTO_INCREMENT,
	fecha text NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cliente (
	id int(10) NOT NULL AUTO_INCREMENT,
	nombre text NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO ticket VALUES(1, '2010-10-10 01:02:03');
INSERT INTO ticket VALUES(2, '2011-11-11 01:02:03');

INSERT INTO cliente VALUES(1, 'javi');
INSERT INTO cliente VALUES(2, 'george');

/*
CREATE TABLE IF NOT EXISTS guiada (
	id int(10) NOT NULL AUTO_INCREMENT,
	id_ticket int(10) NOT NULL,
	precio_original int(10) NULL,
	tiene_descuento boolean default NULL,
	precio_final int(10) NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (id_ticket) REFERENCES ticket (id)
);

CREATE TABLE IF NOT EXISTS guiada (
	id int(10) NOT NULL AUTO_INCREMENT,
	id_ticket int(10) NOT NULL,
	precio_original int(10) NULL,
	tiene_descuento boolean NULL,
	precio_final int(10) NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (id_ticket) REFERENCES ticket (id)
);

CREATE TABLE IF NOT EXISTS cliente (
	id int(10) NOT NULL AUTO_INCREMENT,
	biometrico text NOT NULL,
	nombre text NOT NULL,
	apellidos text NULL,
	email text NULL,
	telefono text NULL,
	frecuencia text NULL,
	PRIMARY KEY (id)
);*/
