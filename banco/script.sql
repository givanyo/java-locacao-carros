CREATE DATABASE IF NOT EXISTS java_locacao_carros;
USE java_locacao_carros;

CREATE TABLE IF NOT EXISTS usuario(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome_usuario VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    adm BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS cliente(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_usuario INT NOT NULL UNIQUE,
    FOREIGN KEY (id_usuario)
		REFERENCES usuario(id),
	nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
	cnh CHAR(11) NOT NULL UNIQUE,
    cpf CHAR(11) NOT NULL UNIQUE,
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS categoria( 
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    grupo VARCHAR(2),
    titulo VARCHAR(100),
    descricao VARCHAR(100),
    pessoas INT,
    valor_diaria DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS carro(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_categoria INT NOT NULL,
    FOREIGN KEY(id_categoria)
		REFERENCES categoria(id),
	modelo VARCHAR(100),
    placa CHAR(7) NOT NULL,
    disponivel BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS cartao_credito(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_cliente INT NOT NULL UNIQUE,
	FOREIGN KEY(id_cliente)
		REFERENCES cliente(id),
    nome_cartao VARCHAR(100),
    numero_cartao CHAR(20) UNIQUE,
    limite DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS pre_reserva(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_cliente)
		REFERENCES cliente(id),
	id_carro INT NOT NULL,
    FOREIGN KEY (id_carro)
		REFERENCES carro(id),
	previsao_inicio DATE NOT NULL,
    duracao_dias INT NOT NULL
);

CREATE TABLE IF NOT EXISTS locacao(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_pre_reserva INT UNIQUE NOT NULL,
		FOREIGN KEY(id_pre_reserva)
			REFERENCES pre_reserva(id),
	data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    status VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS transacao(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_pre_reserva INT UNIQUE NOT NULL,
    FOREIGN KEY(id_pre_reserva)
		REFERENCES pre_reserva(id),
	sinal DECIMAL(10, 2) NOT NULL,
    valor_restante DECIMAL(10, 2) NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    data_pag_sinal DATE
    data_pag_restante DATE
);

INSERT INTO usuario (nome_usuario, email, senha, adm) VALUES
	('jeff.riper', 'jeffinhoriper@email.com', 'jeff2026', FALSE),
	('bia.damasco', 'beatrizdamasco07@email.com', 'bia2026', FALSE),
	('rita.cassia', 'ritadecassia01@email.com', 'rita2026', FALSE),
    ('marcos.ferreira', 'marcosferreira88@email.com', 'marc2026', FALSE),
    ('admin', 'admin@email.com', 'admin2026', TRUE);

INSERT INTO cliente (id_usuario, nome, telefone, cnh, cpf) VALUES
	(1, 'Jeffinho Riper', '11912345678', '00123456789', '12345678901'),
    (2, 'Beatriz Damasco', '21912345678', '00223456789', '22345678901'),
    (3, 'Rita de Cássia', '31912345678', '00323456789', '32345678901'),
    (4, 'Marcos Ferreira', '41912345678', '00423456789', '42345678901');

INSERT INTO cartao_credito (id_cliente, nome_cartao, numero_cartao, limite) VALUES
    (1, 'Jeff. R.', '1234 5678 9012 3456', 50000.99),
    (2, 'Beatriz D.', '2234 5678 9012 3456', 21000.99),
    (3, 'Rita de C.', '3234 5678 9012 3456', 6400),
    (4, 'Marcos F.', '4234 5678 9012 3456', 1000.09);

INSERT INTO categoria (grupo, titulo, descricao, pessoas, valor_diaria) VALUES
	('B', 'Compacto Com Ar', 'FIAT MOBI 1.0, RENAULT KWID 1.0 OU SIMILAR', 4, 74.29),
    ('C', 'Econômico Com Ar', 'GM ONIX JOY 1.0, VW GOL 1.0 OU SIMILAR', 5, 85.99),
	('CE', 'Econômico Especial C/ar', 'HYUNDAI HB20 1.0, FIAT ARGO 1.0, VW POLO 1.0 OU SIMILAR', 5, 95.49);

INSERT INTO carro (id_categoria, modelo, placa) VALUES
	(1, 'FIAT MOBI 1.0', 'ABC1234'),
    (1, 'RENAULT KWID 1.0', 'DEF1234'),
    (2, 'GM ONIX JOY 1.0', 'GHI1234'),
    (2, 'VW GOL 1.0', 'JKL1234'),
    (3, 'HYUNDAI HB20 1.0', 'LMN1234'),
    (3, 'FIAT ARGO 1.0', 'OPQ1234'),
    (3, 'VW POLO 1.0', 'RST1234');
    

DELIMITER // 
CREATE TRIGGER after_inserir_reserva
AFTER INSERT ON pre_reserva
FOR EACH ROW
BEGIN
		INSERT INTO transacao (id_pre_reserva, sinal, valor_restante, valor_total)
        SELECT 
        NEW.id,
        categoria.valor_diaria * NEW.duracao_dias * 0.4,
        categoria.valor_diaria * NEW.duracao_dias * 0.6,
        categoria.valor_diaria * NEW.duracao_dias
        FROM carro
        INNER JOIN categoria
        ON carro.id_categoria = categoria.id
        WHERE carro.id = NEW.id_carro;
END //
DELIMITER ;

INSERT INTO pre_reserva (id_cliente, id_carro, previsao_inicio, duracao_dias) VALUES
    (1, 6, '2026-09-20', 5),
    (1, 5, '2026-09-26', 7),
    (2, 2, '2026-09-25', 3),
    (3, 3, '2026-11-11', 4);