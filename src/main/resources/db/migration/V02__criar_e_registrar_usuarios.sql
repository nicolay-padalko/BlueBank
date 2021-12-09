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

INSERT INTO tb_usuario (tipo_usuario, id, nome, sobrenome, telefone, cpf, email, senha, conta_id) VALUES ("C", 1, "NomeClienteTeste1", "SobrenomeClienteTeste1", "11 91111-1111", "111.111.111-11", "cliente1@gmail.com", "$2a$10$xkWFwzIUNrMtU/YcDcatEeNrSEvKqhMQnreA92U/aVdKV83G1mv46", 5001);
INSERT INTO tb_usuario (tipo_usuario, id, nome, sobrenome, telefone, cpf, email, senha, conta_id) VALUES ("C", 2, "NomeClienteTeste2", "SobrenomeClienteTeste2", "21 92222-2222", "222.222.222-22", "cliente2@gmail.com", "$2a$10$SewBGjehIJ2J/KCulnAXEOBGY9pWAmUJ/9zpwGSHdlXOryyEhO2pS", 5002);
INSERT INTO tb_usuario (tipo_usuario, id, nome, sobrenome, telefone, cpf, email, senha, conta_id) VALUES ("C", 3, "NomeClienteTeste3", "SobrenomeClienteTeste3", "31 93333-3333", "333.333.333-33", "cliente3@gmail.com", "$2a$10$SewBGjehIJ2J/KCulnAXEOBGY9pWAmUJ/9zpwGSHdlXOryyEhO2pS", 5003);
INSERT INTO tb_usuario (tipo_usuario, id, nome, sobrenome, telefone, cpf, email, senha) VALUES ("F", 4, "NomeFuncionarioTeste1", "SobrenomeFuncionarioTeste1", "41 94444-4444", "444.444.444-44", "funcionario1@gmail.com", "$2a$10$vXDRhWXGlEgotQd.hO3IB.GKxXI3eW9pzZiNPS24zjttClM1HTi2W");

