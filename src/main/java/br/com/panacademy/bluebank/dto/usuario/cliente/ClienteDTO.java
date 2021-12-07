package br.com.panacademy.bluebank.dto.usuario.cliente;

import br.com.panacademy.bluebank.modelo.usuario.Cliente;

public class ClienteDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private String email;
    private Long numeroConta;
    private Double saldo;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nome, String sobrenome, String telefone, String cpf, String email, Long numeroConta, Double saldo) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public ClienteDTO(Cliente entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.sobrenome = entidade.getSobrenome();
        this.telefone = entidade.getTelefone();
        this.cpf = entidade.getCpf();
        this.email = entidade.getEmail();
        this.numeroConta = entidade.getConta().getContaId();
        this.saldo = entidade.getConta().getSaldo();
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

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
