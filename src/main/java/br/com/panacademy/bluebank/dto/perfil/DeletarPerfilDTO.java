package br.com.panacademy.bluebank.dto.perfil;

public class DeletarPerfilDTO {

    private String nome;


    public DeletarPerfilDTO(String nome) {
        this.nome = nome;
    }

    public DeletarPerfilDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
