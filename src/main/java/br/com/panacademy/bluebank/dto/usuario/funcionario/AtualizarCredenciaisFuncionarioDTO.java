package br.com.panacademy.bluebank.dto.usuario.funcionario;

import br.com.panacademy.bluebank.modelo.usuario.Funcionario;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class AtualizarCredenciaisFuncionarioDTO {
    @CPF
    private String cpf;
    @Email
    private String email;
    @Size(min=8, max=30)
    private String senha;

    public AtualizarCredenciaisFuncionarioDTO() {
    }

    public AtualizarCredenciaisFuncionarioDTO(Funcionario entidade, AtualizarCredenciaisFuncionarioDTO dto) {
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
