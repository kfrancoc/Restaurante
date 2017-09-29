-- Database: "SistemaRestaurante"

-- DROP DATABASE "SistemaRestaurante";

--CREATE DATABASE "SistemaRestaurante"
--  WITH OWNER = postgres
--       ENCODING = 'UTF8'
--       TABLESPACE = pg_default
--       LC_COLLATE = 'Spanish_Spain.1252'
--       LC_CTYPE = 'Spanish_Spain.1252'
--       CONNECTION LIMIT = -1;



-- Table: usuario

-- DROP TABLE usuario;

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

CREATE OR REPLACE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE usuario
(
  usuarioid serial NOT NULL,
  dni character varying(30) NOT NULL,
  nombre character varying(30) NOT NULL,
  direccion character varying(30),
  tipo character varying(30),
  password character varying(30) NOT NULL,
  CONSTRAINT pk_usuario PRIMARY KEY (usuarioid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE usuario
  OWNER TO postgres;

-- Table: cliente

-- DROP TABLE cliente;

CREATE TABLE cliente
(
  clienteid serial NOT NULL,
  dni character varying(8) NOT NULL,
  nombre character varying(30) NOT NULL,
  apellidos character varying(20),
  CONSTRAINT pk_cliente PRIMARY KEY (clienteid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente
  OWNER TO postgres;

-- Table: mesa

-- DROP TABLE mesa;

CREATE TABLE mesa
(
  mesaid serial NOT NULL,
  estado character varying(30),
  numero integer,
  CONSTRAINT pk_mesa PRIMARY KEY (mesaid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE mesa
  OWNER TO postgres;

-- Table: producto

-- DROP TABLE producto;

CREATE TABLE platillo
(
  platilloid serial NOT NULL,
  descripcion character varying(30),
  estado character varying(20),
  precio double precision,
  stock integer,
  tipo character varying(20),
  CONSTRAINT pk_platillo PRIMARY KEY (platilloid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE platillo
  OWNER TO postgres;

-- Table: pedido

-- DROP TABLE pedido;

CREATE TABLE pedido
(
  pedidoid serial NOT NULL,
  estado character varying(30),
  fecha date,
  monto double precision,
  mesaid integer NOT NULL,
  usuarioid integer NOT NULL,
  CONSTRAINT pk_pedido PRIMARY KEY (pedidoid),
  CONSTRAINT fk_mesa FOREIGN KEY (mesaid)
      REFERENCES mesa (mesaid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_usuario FOREIGN KEY (usuarioid)
      REFERENCES usuario (usuarioid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pedido
  OWNER TO postgres;


-- Table: lineapedido

-- DROP TABLE lineapedido;

CREATE TABLE lineadepedido
(
  cantidad integer,
  condicion character varying(20),
  precio double precision,
  platilloid integer NOT NULL,
  pedidoid integer NOT NULL,
  CONSTRAINT pk_lineadepedido PRIMARY KEY (platilloid,pedidoid),
  CONSTRAINT fk_platillo FOREIGN KEY (platilloid)
      REFERENCES platillo (platilloid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT fk_pedido FOREIGN KEY (pedidoid)
      REFERENCES pedido (pedidoid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE lineadepedido
  OWNER TO postgres;

-- Table: comprobantedepago

-- DROP TABLE comprobantedepago;

CREATE TABLE comprobantedepago
(
  comprobantedepagoid serial NOT NULL,
  fecha date NOT NULL,
  igv double precision,
  montoapagar double precision NOT NULL,
  usuarioid integer,
  clienteid integer,
  pedidoid integer NOT NULL,
  CONSTRAINT pk_comprobantedepago PRIMARY KEY (comprobantedepagoid),
  CONSTRAINT fk_clienteid FOREIGN KEY (clienteid)
      REFERENCES public.cliente (clienteid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_pedidoid FOREIGN KEY (pedidoid)
      REFERENCES public.pedido (pedidoid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_usuario FOREIGN KEY (usuarioid)
      REFERENCES public.usuario (usuarioid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE comprobantedepago
  OWNER TO postgres;


INSERT INTO usuario(dni, nombre, direccion, tipo, password)
VALUES  ('48736253','Calors','Jose Sabogal #456','Administrador', '123456'),
	('78627364','Juan','La Noria #321', 'Mesero', '123456'),
	('48785932','Pedro','Mansiche #432', 'Mesero', '123456'),
	('70127364','María','Santa Inés #765','Mesero', '123456'),
	('48728394','José','Trujillo', 'Administrador', '123456'),
	('73728491','Roverto','Trujillo', 'Mesero', '123456');
	
  
----------------------------------------------------
INSERT INTO platillo(descripcion, estado, precio, stock, tipo)
	VALUES 
	('Ceviche','DISPONIBLE',15.0,20,'MENU'),
	('Cabrito','DISPONIBLE',18.0,9,'MENU'),
	('Arroz con mariscos','DISPONIBLE',25.0,10,'MENU'),
	('chicharron de pescado','DISPONIBLE',15.0,25,'MENU'),
        ('Ají de gallina','DISPONIBLE',20.0,8,'MENU'),
        ('Inka cola 1 litro','DISPONIBLE',6.0,23,'BEBIDA'),
	('Arroz con pato','DISPONIBLE',22.0,15,'MENU'),
        ('Chicha morada 1 litro','DISPONIBLE',5.0,30,'BEBIDA');
    
---------------------------------------------------------------

INSERT INTO cliente(dni, nombre, apellidos)
	VALUES  ('73647571','Maria','Gonzales Ruiz'),
		('48716234','Susana','Suarez Guerrero'),
		('70176354','Luis','Fernández Gutierrez'),
		('48456321','Herman','Vera Rodriguez'),
		('48716284','Juana','Guerrero Vera'),
		('48928173','Alberto','Rodriguez Ruiz');	

----------------------------------------------------------------
INSERT INTO mesa(estado, numero)
	VALUES  
	  ('DISPONIBLE',1),
          ('DISPONIBLE',2),
          ('DISPONIBLE',3),
          ('DISPONIBLE',4),
          ('DISPONIBLE',5),
          ('DISPONIBLE',6),
          ('DISPONIBLE',7),
          ('DISPONIBLE',8),
          ('DISPONIBLE',9),
          ('DISPONIBLE',10),
          ('DISPONIBLE',11);
  
