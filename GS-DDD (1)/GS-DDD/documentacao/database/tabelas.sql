-- alter session set "_ORACLE_SCRIPT"=true;

-- Create sequences
CREATE SEQUENCE SQ_CLIENTE;
CREATE SEQUENCE SQ_BIKE;
CREATE SEQUENCE SQ_ACESSORIO;
CREATE SEQUENCE SQ_SEGURO;

-- Create tables
CREATE TABLE TB_CLIENTE
(
    ID_CLIENTE     NUMBER PRIMARY KEY,
    NM_CLIENTE     VARCHAR2(255 BYTE),
    DS_EMAIL       VARCHAR2(255 BYTE),
    NR_CPF         VARCHAR2(50 BYTE),
    NR_TELEFONE    VARCHAR2(255 BYTE),
    NR_CEP         VARCHAR2(255 BYTE),
    DS_ENDERECO    VARCHAR2(255 BYTE),
    DS_COMPLEMENTO VARCHAR2(255 BYTE)
);

CREATE TABLE TB_BIKE
(
    ID_BIKE         NUMBER PRIMARY KEY,
    NM_MODELO          VARCHAR2(255 BYTE),
    NR_SERIE        VARCHAR2(255 BYTE),
    VL_BIKE         NUMBER,
    DT_LANCAMENTO   DATE,
    ID_CLIENTE         NUMBER REFERENCES TB_CLIENTE(ID_CLIENTE)
);

CREATE TABLE TB_ACESSORIO
(
    ID_ACESSORIO    NUMBER PRIMARY KEY,
    NM_ACESSORIO    VARCHAR2(255 BYTE),
    VL_ACESSORIO    NUMBER,
    ID_BIKE         NUMBER REFERENCES TB_BIKE(ID_BIKE)
);

CREATE TABLE TB_SEGURO
(
    ID_SEGURO       NUMBER PRIMARY KEY,
    VL_SEGURO       VARCHAR2(50),
    DT_INICIO       DATE,
    ID_CLIENTE      NUMBER REFERENCES TB_CLIENTE(ID_CLIENTE),


);

-- Triggers
CREATE OR REPLACE TRIGGER TG_SQ_SEGURO
              BEFORE INSERT OR UPDATE ON TB_SEGURO
    FOR EACH ROW
BEGIN
    IF :new.ID_SEGURO IS NULL OR :new.ID_SEGURO < 1 THEN
        :new.ID_SEGURO := SQ_SEGURO.NEXTVAL;
END IF;
END;

CREATE OR REPLACE TRIGGER TG_SQ_ACESSORIO
              BEFORE INSERT OR UPDATE ON TB_ACESSORIO
    FOR EACH ROW
BEGIN
    IF :new.ID_ACESSORIO IS NULL OR :new.ID_ACESSORIO < 1 THEN
        :new.ID_ACESSORIO := SQ_ACESSORIO.NEXTVAL;
END IF;
END;

CREATE OR REPLACE TRIGGER TG_SQ_CLIENTE
              BEFORE INSERT OR UPDATE ON TB_CLIENTE
    FOR EACH ROW
BEGIN
    IF :new.ID_CLIENTE IS NULL OR :new.ID_CLIENTE < 1 THEN
        :new.ID_CLIENTE := SQ_CLIENTE.NEXTVAL;
END IF;
END;

CREATE OR REPLACE TRIGGER TG_SQ_BIKE
              BEFORE INSERT OR UPDATE ON TB_BIKE
    FOR EACH ROW
BEGIN
    IF :new.ID_BIKE IS NULL OR :new.ID_BIKE < 1 THEN
        :new.ID_BIKE := SQ_BIKE.NEXTVAL;
END IF;
END;

-- Indexes
CREATE INDEX TB_CLIENTE_ID_CLIENTE ON TB_CLIENTE (ID_CLIENTE);
CREATE INDEX TB_BIKE_ID_BIKE ON TB_BIKE (ID_BIKE);

-- Constraints
ALTER TABLE TB_BIKE MODIFY (ID_BIKE NOT NULL);
ALTER TABLE TB_CLIENTE MODIFY (ID_CLIENTE NOT NULL);

ALTER TABLE TB_ACESSORIO
    ADD CONSTRAINT TB_ACESSORIO_FK_BIKE
        FOREIGN KEY (ID_BIKE) REFERENCES TB_BIKE (ID_BIKE);

ALTER TABLE TB_BIKE
    ADD CONSTRAINT TB_BIKE_FK_ID_DONO
        FOREIGN KEY (ID_DONO) REFERENCES TB_CLIENTE (ID_CLIENTE);

ALTER TABLE TB_SEGURO
    ADD CONSTRAINT TB_SEGURO_FK_ID_CLIENTE
        FOREIGN KEY (ID_CLIENTE) REFERENCES TB_CLIENTE (ID_CLIENTE);

ALTER TABLE TB_SEGURO
    ADD CONSTRAINT TB_SEGURO_FK_BIKE
        FOREIGN KEY (BIKE) REFERENCES TB_BIKE (ID_BIKE);

ALTER TABLE TB_SEGURO
    ADD CONSTRAINT TB_SEGURO_FK_ID_ACESSORIO
        FOREIGN KEY (ID_ACESSORIO) REFERENCES TB_ACESSORIO (ID_ACESSORIO);
