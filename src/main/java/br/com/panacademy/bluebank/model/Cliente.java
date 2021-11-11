package br.com.panacademy.bluebank.model;

import javax.persistence.*;
import java.util.Collection;



@Entity
@Table(name = "cliente", uniqueConstraints = @UniqueConstraint(columnNames = "cpf"))
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    private String email;
    private String senha;
    private String cpf;

    @ManyToMany(fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
    @JoinTable(
            name = "funcoes_cliente",
            joinColumns = @JoinColumn(
                    name = "cliente_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "funcao_id", referencedColumnName = "id"))

    private Collection<Funcao> funcoes;




    public Cliente(String nome, String sobrenome, String email, String senha, String cpf, Collection<Funcao> funcoes) {
        super();
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.funcoes = funcoes;
    }

    public Cliente() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobreNome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Collection<Funcao> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(Collection<Funcao> funcoes) {
        this.funcoes = funcoes;
    }
}
