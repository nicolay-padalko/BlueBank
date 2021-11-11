package br.com.panacademy.bluebank.model;

import javax.persistence.*;

@Entity
@Table(name = "funcao")
public class Funcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Funcao() {
    }


    public Funcao(String name) {
        super();
        this.name = name;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

}
