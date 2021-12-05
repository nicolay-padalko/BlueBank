CREATE TABLE tb_perfil(
    perfil_id INT(9) PRIMARY KEY AUTO_INCREMENT,
    nome      VARCHAR(45) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE tb_cliente_perfil(
    tb_cliente_id INT NOT NULL,
    tb_perfil_id INT NOT NULL,
    PRIMARY KEY (tb_cliente_id, tb_perfil_id),
    CONSTRAINT fk_tb_cliente_has_tb_perfil_tb_cliente
        FOREIGN KEY (tb_cliente_id)
            REFERENCES tb_cliente(id),
    CONSTRAINT `fk_tb_cliente_has_tb_perfil_tb_perfil1`
        FOREIGN KEY (tb_perfil_id)
            REFERENCES tb_perfil(perfil_id)
) ENGINE = InnoDB;







