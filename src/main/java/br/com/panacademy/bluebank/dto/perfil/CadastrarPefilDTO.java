package br.com.panacademy.bluebank.dto.perfil;

public class CadastrarPefilDTO {

    private String nome;


    public CadastrarPefilDTO(String nome) {
        this.nome = nome;
    }

    public CadastrarPefilDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
