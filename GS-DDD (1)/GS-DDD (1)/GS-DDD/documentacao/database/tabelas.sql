-- alter session set "_ORACLE_SCRIPT"=true;

-- Create sequences
DROP SEQUENCE seq_paciente
CREATE SEQUENCE seq_paciente
    start with 1
    increment by 1
    minvalue 1
    maxvalue 1000
    nocycle;

DROP SEQUENCE seq_dependente;
CREATE SEQUENCE seq_dependente
    start with 1
    increment by 1
    minvalue 1
    maxvalue 1000
    nocycle;

DROP SEQUENCE seq_hospital;
CREATE SEQUENCE seq_hospital
    start with 1
    increment by 1
    minvalue 1
    maxvalue 1000
    nocycle;

DROP SEQUENCE seq_plano_de_saude;
CREATE SEQUENCE seq_plano_de_saude
    start with 1
    increment by 1
    minvalue 1
    maxvalue 1000
    nocycle;

--DROP TABLE T_PS_PACIENTE cascade constraints
CREATE TABLE T_PS_PACIENTE (
                               id_paciente NUMERIC(9) PRIMARY KEY,
                               nm_paciente VARCHAR(255) NOT NULL,
                               nr_cpf NUMERIC(38) NOT NULL,
                               nr_rg NUMERIC(38) NOT NULL,
                               ds_email VARCHAR(255) NOT NULL,
                               ds_endereco VARCHAR(255) NOT NULL,
                               nr_telefone NUMERIC(38) NOT NULL,
                               nr_cep NUMERIC(38) NOT NULL,
                               ds_genero CHAR(1),
                               ds_complento varchar(255),
                               CONSTRAINT chk_genero CHECK (ds_genero IN ('M', 'F'))
);

--DROP TABLE  T_PS_HOSPITAL
create table T_PS_HOSPITAL(
                              id_hospital numeric(9) primary key,
                              nm_hospital varchar(255) not null,
                              nr_cnpj numeric(38) not null,
                              ds_endereco varchar (255) not null,
                              nr_telefone numeric (38) not null,
                              id_paciente numeric(9),
                              FOREIGN KEY(id_paciente) REFERENCES T_PS_PACIENTE(id_paciente)

);

--DROP TABLE  T_PS_DEPENDENTE
create table T_PS_DEPENDENTE(
                                id_dependente numeric(9) primary key,
                                nm_dependente varchar(255) not null,
                                nr_cpf numeric(38) not null,
                                nr_rg numeric(38) not null,
                                ds_email varchar(255) not null,
                                ds_endereco varchar (255) not null,
                                nr_telefone numeric (38) not null,
                                id_paciente numeric(9),
                                ds_complento varchar(255),
                                ds_genero char(1) not null,
                                CONSTRAINT cht_genero CHECK (ds_genero IN ('M', 'F')),

                                FOREIGN KEY(id_paciente) REFERENCES T_PS_PACIENTE(id_paciente)
);

--DROP TABLE  T_PS_PLANO_DE_SAUDE
create table T_PS_PLANO_DE_SAUDE(
                                    id_plano numeric (9) primary key,
                                    vl_valor numeric (38) not null,
                                    nm_plano varchar(255) not null,
                                    ds_modelo varchar (255) not null,
                                    dt_inicio date not null,
                                    id_paciente numeric(9),

                                    FOREIGN KEY(id_paciente) REFERENCES T_PS_PACIENTE(id_paciente)
);




--insert paciente
INSERT INTO T_PS_PACIENTE (id_paciente, nm_paciente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone, nr_cep,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'joao vito', 1234567891, 123456789, 'jaozin@email.com.br', 'Sao paulo', 78945612, 32165498,'M','CASA1');

INSERT INTO T_PS_PACIENTE (id_paciente, nm_paciente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone, nr_cep,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'lucas monte', 1234567892, 123456788, 'lucs@email.com.br', 'Sao paulo', 78945613, 32165497,'M''CASA2');

INSERT INTO T_PS_PACIENTE (id_paciente, nm_paciente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone, nr_cep,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'gustavo imparato', 1234567893, 123456787, 'lucs@email.com.br', 'Sao paulo', 78945614, 32165496,'M','CASA3');

INSERT INTO T_PS_PACIENTE (id_paciente, nm_paciente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone, nr_cep,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'marcos henrique', 1234567894, 123456786, 'marks@email.com.br', 'Sao paulo', 78945615, 32165495,'M','CASA9');

INSERT INTO T_PS_PACIENTE (id_paciente, nm_paciente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone, nr_cep,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'izabelly', 1234567895, 123456785, 'iza@email.com.br', 'Sao paulo', 78945616, 32165494,'F','CASA4');

INSERT INTO T_PS_PACIENTE (id_paciente, nm_paciente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone, nr_cep,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'leo bruno', 1234567896, 123456784, 'iza@email.com.br', 'Sao paulo', 78945617,'M','CASA5');

INSERT INTO T_PS_PACIENTE (id_paciente, nm_paciente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone, nr_cep,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'vinicius andrade', 1234567897, 123456783, 'vinizin@email.com.br', 'Sao paulo', 78945618, 32165492,'M','CASA6');

INSERT INTO T_PS_PACIENTE (id_paciente, nm_paciente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone, nr_cep,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'neymar jr', 1234567898, 123456782, 'njr@email.com.br', 'Sao paulo', 78945619, 32165491,'M','CASA7');

INSERT INTO T_PS_PACIENTE (id_paciente, nm_paciente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone, nr_cep,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'mbappe', 1234567899, 123456781, 'tourtle@email.com.br', 'Sao paulo', 78945610, 32165490,'M','CASA8');

INSERT INTO T_PS_PACIENTE (id_paciente, nm_paciente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone, nr_cep,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'pipokinha', 12345678910, 123456715, 'pops@email.com.br', 'Sao paulo', 78945636, 32165498,'F','AP10');



--insert hospital
INSERT INTO T_PS_HOSPITAL (id_hospital, nm_hospital, nr_cnpj, ds_endereco, nr_telefone,id_paciente)
VALUES (seq_paciente.nextval, 'sao luiz', 12345678912345, 'Sao paulo', 78945611,1);

INSERT INTO T_PS_HOSPITAL (id_hospital, nm_hospital, nr_cnpj, ds_endereco, nr_telefone,id_paciente)
VALUES (seq_paciente.nextval, 'sirio libanes', 12345678912346, 'Sao paulo', 78945612,2);

INSERT INTO T_PS_HOSPITAL (id_hospital, nm_hospital, nr_cnpj, ds_endereco, nr_telefone,id_paciente)
VALUES (seq_paciente.nextval, 'albert einstein', 12345678912347, 'Sao paulo', 78945613,3);

INSERT INTO T_PS_HOSPITAL (id_hospital, nm_hospital, nr_cnpj, ds_endereco, nr_telefone,id_paciente)
VALUES (seq_paciente.nextval, 'hospital familly', 12345678912348, 'Sao paulo', 78945614,4);

INSERT INTO T_PS_HOSPITAL (id_hospital, nm_hospital, nr_cnpj, ds_endereco, nr_telefone,id_paciente)
VALUES (seq_paciente.nextval, 'oswaldo cruz', 12345678912349, 'Sao paulo', 78945615,5);

INSERT INTO T_PS_HOSPITAL (id_hospital, nm_hospital, nr_cnpj, ds_endereco, nr_telefone,id_paciente)
VALUES (seq_paciente.nextval, 'santa isabel', 12345678912340, 'Sao paulo', 78945616,6);

INSERT INTO T_PS_HOSPITAL (id_hospital, nm_hospital, nr_cnpj, ds_endereco, nr_telefone,id_paciente)
VALUES (seq_paciente.nextval, 'lefort', 12345678912341, 'Sao paulo', 78945617,7);

INSERT INTO T_PS_HOSPITAL (id_hospital, nm_hospital, nr_cnpj, ds_endereco, nr_telefone,id_paciente)
VALUES (seq_paciente.nextval, 'samaritano', 12345678912342, 'Sao paulo', 78945618,8);

INSERT INTO T_PS_HOSPITAL (id_hospital, nm_hospital, nr_cnpj, ds_endereco, nr_telefone,id_paciente)
VALUES (seq_paciente.nextval, 'hcor', 12345678912344, 'Sao paulo', 78945610,9);

INSERT INTO T_PS_HOSPITAL (id_hospital, nm_hospital, nr_cnpj, ds_endereco, nr_telefone,id_paciente)
VALUES (seq_paciente.nextval, 'santa catarina', 12345678912111, 'Sao paulo', 725845610,10);



--insert dependente
INSERT INTO T_PS_DEPENDENTE (id_dependente, nm_dependente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone,id_paciente,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'oliano leo', 98765432, 32145678, 'oli@email.com.br', 'Sao paulo', 36980201,1,'M','AP1');

INSERT INTO T_PS_DEPENDENTE (id_dependente, nm_dependente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone,id_paciente,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'Lucas shigs', 00044444, 00033333, 'shigs@email.com.br', 'Sao paulo', 00000001,2,'M','AP2');

INSERT INTO T_PS_DEPENDENTE (id_dependente, nm_dependente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone,id_paciente,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'bianca valentin', 00044333, 00038883, 'bianc@email.com.br', 'Sao paulo', 00000002,3,'F','AP3');

INSERT INTO T_PS_DEPENDENTE (id_dependente, nm_dependente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone,id_paciente,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'matheus rusb', 00042474, 00033789, 'rusb@email.com.br', 'Sao paulo', 00000003,4,'M','AP4');

INSERT INTO T_PS_DEPENDENTE (id_dependente, nm_dependente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone,id_paciente,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'giovani', 12342474, 08965789, 'gio@email.com.br', 'Sao paulo', 00000004,5,'M','AP5');

INSERT INTO T_PS_DEPENDENTE (id_dependente, nm_dependente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone,id_paciente,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'orochi', 12365474, 04785789, 'oroq@email.com.br', 'Sao paulo', 00000005,6,'M','AP6');

INSERT INTO T_PS_DEPENDENTE (id_dependente, nm_dependente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone,id_paciente,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'wellington', 17535474, 04785125, 'rato@email.com.br', 'Sao paulo', 00000006,7,'M','AP7');

INSERT INTO T_PS_DEPENDENTE (id_dependente, nm_dependente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone,id_paciente,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'calleri', 17534574, 04780325, 'lindo@email.com.br', 'Sao paulo', 00000007,8,'M','AP8');

INSERT INTO T_PS_DEPENDENTE (id_dependente, nm_dependente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone,id_paciente,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'rogerio', 17533694, 01590325, 'ceni@email.com.br', 'Sao paulo', 00000008,9,'M','AP9');

INSERT INTO T_PS_DEPENDENTE (id_dependente, nm_dependente, nr_cpf, nr_rg, ds_email, ds_endereco, nr_telefone,ds_genero,ds_complento)
VALUES (seq_paciente.nextval, 'messi', 99933694, 01593625, 'mess@email.com.br', 'Sao paulo', 00000009,10,'M','AP10');



--inserts plano de saude
INSERT INTO T_PS_PLANO_DE_SAUDE (id_plano, nm_plano, vl_valor, ds_modelo, dt_inicio,id_paciente)
VALUES (seq_paciente.nextval, 'solo1', 500, 'bronze', TO_DATE('2023-06-01', 'YYYY-MM-DD'),1);

INSERT INTO T_PS_PLANO_DE_SAUDE (id_plano, nm_plano, vl_valor, ds_modelo, dt_inicio,id_paciente)
VALUES (seq_paciente.nextval, 'solo2', 750, 'prata', TO_DATE('2023-01-02', 'YYYY-MM-DD'),2);

INSERT INTO T_PS_PLANO_DE_SAUDE (id_plano, nm_plano, vl_valor, ds_modelo, dt_inicio,id_paciente)
VALUES (seq_paciente.nextval, 'solo3', 1000, 'ouro', TO_DATE('2023-02-03', 'YYYY-MM-DD'),3);

INSERT INTO T_PS_PLANO_DE_SAUDE (id_plano, nm_plano, vl_valor, ds_modelo, dt_inicio,id_paciente)
VALUES (seq_paciente.nextval, 'solo4', 1500, 'platina', TO_DATE('2023-08-05', 'YYYY-MM-DD'),4);

INSERT INTO T_PS_PLANO_DE_SAUDE (id_plano, nm_plano, vl_valor, ds_modelo, dt_inicio,id_paciente)
VALUES (seq_paciente.nextval, 'solo5', 1750, 'diamante', TO_DATE('2023-05-04', 'YYYY-MM-DD'),5);

INSERT INTO T_PS_PLANO_DE_SAUDE (id_plano, nm_plano, vl_valor, ds_modelo, dt_inicio,id_paciente)
VALUES (seq_paciente.nextval, 'conjunto1', 2000, 'bronze1', TO_DATE('2023-03-03', 'YYYY-MM-DD'),6);

INSERT INTO T_PS_PLANO_DE_SAUDE (id_plano, nm_plano, vl_valor, ds_modelo, dt_inicio,id_paciente)
VALUES (seq_paciente.nextval, 'conjunto2', 3000, 'prata1', TO_DATE('2023-04-04', 'YYYY-MM-DD'),7);

INSERT INTO T_PS_PLANO_DE_SAUDE (id_plano, nm_plano, vl_valor, ds_modelo, dt_inicio,id_paciente)
VALUES (seq_paciente.nextval, 'conjunto3', 4000, 'ouro1', TO_DATE('2023-09-09', 'YYYY-MM-DD'),8);

INSERT INTO T_PS_PLANO_DE_SAUDE (id_plano, nm_plano, vl_valor, ds_modelo, dt_inicio,id_paciente)
VALUES (seq_paciente.nextval, 'conjunto4', 5000, 'ouro1', TO_DATE('2023-10-07', 'YYYY-MM-DD',9));

INSERT INTO T_PS_PLANO_DE_SAUDE (id_plano, nm_plano, vl_valor, ds_modelo, dt_inicio,id_paciente)
VALUES (seq_paciente.nextval, 'conjunto5', 6000, 'platina1', TO_DATE('2023-11-12', 'YYYY-MM-DD',10));


--SELECTS
-- Seleciona todos os pacientes
SELECT * FROM T_PS_PACIENTE;

-- Seleciona todos os hospitais com informa��es de pacientes associadas
SELECT * FROM T_PS_HOSPITAL;

-- Seleciona todos os dependentes com informa��es de pacientes associadas
SELECT * FROM T_PS_DEPENDENTE;

-- Seleciona todos os planos de sa�de com informa��es de pacientes associadas
SELECT * FROM T_PS_PLANO_DE_SAUDE;
