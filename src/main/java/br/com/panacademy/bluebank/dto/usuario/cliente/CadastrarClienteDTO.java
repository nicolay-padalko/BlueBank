package br.com.panacademy.bluebank.dto.usuario.cliente;

import br.com.panacademy.bluebank.modelo.usuario.Cliente;
import br.com.panacademy.bluebank.modelo.Conta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

public class CadastrarClienteDTO {

    @NotBlank(message = "O campo nome não pode estar em branco")
    @Size(min=2, max=30)
    private String nome;

    @NotBlank(message = "O campo sobrenome não pode estar em branco")
    @Size(min=2, max=30)
    private String sobrenome;

    @NotBlank(message = "O campo telefone não pode estar em branco")
    @Pattern(regexp="^((\\(\\d{2}\\))|\\d{2})[- .]?\\d{5}[- .]?\\d{4}$")
    private String telefone;

    @NotBlank(message = "O campo CPF não pode estar em branco")
    @CPF(message = "Você deve inserir um CPF válido")
    private String cpf;

    @NotBlank(message = "O campo email não pode estar em branco")
    @Email
    private String email;

    @NotNull
    @Size(min=8, max=30)
    private String senha;

    @JsonIgnore
    private Conta conta;

    public CadastrarClienteDTO(String nome, String sobrenome, String telefone, String cpf, String email, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public CadastrarClienteDTO() {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Cliente toCliente(){
        Cliente cliente = new Cliente();
         cliente.setNome(this.nome);
         cliente.setSobrenome(this.sobrenome);
         cliente.setTelefone(this.telefone);
         cliente.setCpf(this.cpf);
         cliente.setEmail(this.email);
         cliente.setSenha(this.senha);
         cliente.setConta(this.conta);
         return cliente;
    }
}
