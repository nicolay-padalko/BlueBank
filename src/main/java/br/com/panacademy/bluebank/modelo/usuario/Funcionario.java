package br.com.panacademy.bluebank.modelo.usuario;

import javax.persistence.*;

@Entity
@DiscriminatorValue("F")
public class Funcionario extends Usuario {

    public Funcionario() {
    }

}
