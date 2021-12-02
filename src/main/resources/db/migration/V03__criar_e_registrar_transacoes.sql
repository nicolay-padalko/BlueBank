CREATE TABLE tb_transacao(
    id             INT(9) PRIMARY KEY AUTO_INCREMENT,
    tipo_transacao VARCHAR(20)    NOT NULL,
    data_transacao  TIMESTAMP      NOT NULL,
    descricao      VARCHAR(255),
    valor          DECIMAL(10, 2) NOT NULL,
    conta_id       INT(9),
    FOREIGN KEY (conta_id) REFERENCES tb_conta (conta_id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO tb_transacao (id, tipo_transacao, data_transacao, descricao, valor, conta_id) VALUES (1, "DEPOSITO", now(), "", 50.00, 5001);
INSERT INTO tb_transacao (id, tipo_transacao, data_transacao, descricao, valor, conta_id) VALUES (2, "TRANSFERENCIA", now(), "", 10.00, 5002);
INSERT INTO tb_transacao (id, tipo_transacao, data_transacao, descricao, valor, conta_id) VALUES (3, "SAQUE", now(), "", 10.00, 5003);