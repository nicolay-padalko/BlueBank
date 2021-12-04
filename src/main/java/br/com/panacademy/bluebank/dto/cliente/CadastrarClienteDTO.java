package br.com.panacademy.bluebank.dto.cliente;

import br.com.panacademy.bluebank.modelo.Cliente;
import br.com.panacademy.bluebank.modelo.Conta;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

public class CadastrarClienteDTO {


    @NotNull
    @Size(min=2, max=30)
    private String nome;

    @NotNull
    @Size(min=2, max=30)
    private String sobrenome;

    @NotNull
    @Size(min=2, max=30)
//    @Pattern(regexp="(^$|[0-9]{9})")
    private String telefone;

    @NotNull
    @CPF(message = "CPF NAO PODE SER NULO")
    private String cpf;

    @NotNull
    @Size(min=2, max=30)
    private String email;

    @NotNull
    @Size(min=8, max=30)
    private String senha;

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
