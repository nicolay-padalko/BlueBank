CREATE TABLE tb_usuario(
    tipo_usuario VARCHAR(1) NOT NULL,
    id INT(9) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    sobrenome VARCHAR(200) NOT NULL,
    telefone VARCHAR(200) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(200) NOT NULL,
    conta_id INT(9),
    FOREIGN KEY (conta_id) REFERENCES tb_conta(conta_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_usuario (tipo_usuario, id, nome, sobrenome, telefone, cpf, email, senha, conta_id) VALUES ("C", 1, "Teste", "Teste 2", "00 0000-0000", "999.412.111-11", "teste@gmail.com", "$2a$10$NppPPr9UQ0Hlospz3bA9leVlqGfw3En9CK/eE.VoLimXSHL6QqTMW", 5001);
INSERT INTO tb_usuario (tipo_usuario, id, nome, sobrenome, telefone, cpf, email, senha, conta_id) VALUES ("C", 2, "Teste 1", "Teste 3", "01 0000-0000", "339.412.111-11", "teste1@gmail.com", "321", 5002);
INSERT INTO tb_usuario (tipo_usuario, id, nome, sobrenome, telefone, cpf, email, senha, conta_id) VALUES ("C", 3, "Teste 2", "Teste 4", "02 0000-0000", "339.412.111-11", "teste1@gmail.com", "32131", 5003);

