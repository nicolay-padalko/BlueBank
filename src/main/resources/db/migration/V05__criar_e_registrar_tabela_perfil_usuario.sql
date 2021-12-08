CREATE TABLE tb_perfil(
    perfil_id INT(9) PRIMARY KEY AUTO_INCREMENT,
    nome      VARCHAR(45) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO tb_perfil (perfil_id, nome) VALUES (1, "ROLE_ADMIN");
INSERT INTO tb_perfil (perfil_id, nome) VALUES (2, "ROLE_CLIENTE");


CREATE TABLE tb_usuario_perfil(
    tb_usuario_id INT NOT NULL,
    tb_perfil_id INT NOT NULL,
    PRIMARY KEY (tb_usuario_id, tb_perfil_id),
    CONSTRAINT fk_tb_usuario_has_tb_perfil_tb_usuario
        FOREIGN KEY (tb_usuario_id)
            REFERENCES tb_usuario(id),
    CONSTRAINT fk_tb_usuario_has_tb_perfil_tb_perfil1
        FOREIGN KEY (tb_perfil_id)
            REFERENCES tb_perfil(perfil_id)
) ENGINE = InnoDB;

INSERT INTO tb_usuario_perfil (tb_usuario_id, tb_perfil_id) VALUES (1,2);
INSERT INTO tb_usuario_perfil (tb_usuario_id, tb_perfil_id) VALUES (2,2);
INSERT INTO tb_usuario_perfil (tb_usuario_id, tb_perfil_id) VALUES (3,2);
INSERT INTO tb_usuario_perfil (tb_usuario_id, tb_perfil_id) VALUES (4,1);
INSERT INTO tb_usuario_perfil (tb_usuario_id, tb_perfil_id) VALUES (4,2);