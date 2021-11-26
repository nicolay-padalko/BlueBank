package br.com.panacademy.bluebank.dto.cliente;

import br.com.panacademy.bluebank.modelo.Cliente;

public class AtualizarCredenciaisClienteDTO {
    private String cpf;
    private String email;
    private String senha;

    public AtualizarCredenciaisClienteDTO() {

    }

    public AtualizarCredenciaisClienteDTO(Cliente entidade) {
        this.cpf = entidade.getCpf();
        this.email = entidade.getEmail();
        this.senha = entidade.getSenha();
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
