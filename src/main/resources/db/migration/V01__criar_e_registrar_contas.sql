CREATE TABLE tb_conta(
    conta_id INT(9) PRIMARY KEY AUTO_INCREMENT,
    saldo DECIMAL(10,2) NOT NULL,
    criado_em TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_conta (conta_id, saldo, criado_em) VALUES (5001, 0.0, now());
INSERT INTO tb_conta (conta_id, saldo, criado_em) VALUES (5002, 44.0, now());
INSERT INTO tb_conta (conta_id, saldo, criado_em) VALUES (5003, 434.5, now());