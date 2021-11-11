package br.com.panacademy.bluebank.web.dto;

public class ClienteRegistrationDto {
    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;
    private String senha;

    public ClienteRegistrationDto() {
    }

    public ClienteRegistrationDto(String nome, String sobrenome, String email, String cpf, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
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

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
