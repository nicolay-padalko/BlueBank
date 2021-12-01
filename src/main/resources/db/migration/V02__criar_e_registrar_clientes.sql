CREATE TABLE tb_cliente(
    id INT(9) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    sobrenome VARCHAR(200) NOT NULL,
    telefone VARCHAR(200) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(200) NOT NULL,
    conta_id INT(9) NOT NULL,
    FOREIGN KEY (conta_id) REFERENCES tb_conta(conta_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_cliente (id, nome, sobrenome, telefone, cpf, email, senha, conta_id) VALUES (1, "Teste", "Teste 2", "00 0000-0000", "999.412.111-11", "teste@gmail.com", "123", 50001);
INSERT INTO tb_cliente (id, nome, sobrenome, telefone, cpf, email, senha, conta_id) VALUES (2, "Teste 1", "Teste 3", "01 0000-0000", "339.412.111-11", "teste1@gmail.com", "321", 50002);
INSERT INTO tb_cliente (id, nome, sobrenome, telefone, cpf, email, senha, conta_id) VALUES (3, "Teste 2", "Teste 4", "02 0000-0000", "339.412.111-11", "teste1@gmail.com", "32131", 50003);

