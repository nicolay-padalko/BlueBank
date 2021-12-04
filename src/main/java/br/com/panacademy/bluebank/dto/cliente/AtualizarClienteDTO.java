package br.com.panacademy.bluebank.dto.cliente;

import br.com.panacademy.bluebank.modelo.Cliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class AtualizarClienteDTO {
    private String telefone;
    @Email
    private String email;
    @Size(min=8, max=30)
    private String senha;

    public AtualizarClienteDTO() {
    }

    public AtualizarClienteDTO(Cliente entidade) {
        this.telefone = entidade.getCpf();
        this.email = entidade.getEmail();
        this.senha = entidade.getSenha();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
