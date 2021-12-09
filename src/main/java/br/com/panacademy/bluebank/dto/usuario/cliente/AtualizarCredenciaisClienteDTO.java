package br.com.panacademy.bluebank.dto.usuario.cliente;

import br.com.panacademy.bluebank.modelo.usuario.Cliente;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class AtualizarCredenciaisClienteDTO {
    @CPF
    private String cpf;
    @Email
    private String email;
    @Size(min=8, max=30)
    private String senha;

    public AtualizarCredenciaisClienteDTO() {
    }

    public AtualizarCredenciaisClienteDTO(Cliente entidade, AtualizarCredenciaisClienteDTO dto) {
        this.cpf = entidade.getCpf();
        this.email = entidade.getEmail();
        this.senha = dto.getSenha();
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
