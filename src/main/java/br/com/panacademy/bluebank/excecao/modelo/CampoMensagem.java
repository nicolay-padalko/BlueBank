package br.com.panacademy.bluebank.excecao.modelo;

public class CampoMensagem {
    private String nomeCampo;
    private String mensagem;

    public CampoMensagem() {
    }

    public CampoMensagem(String nomeCampo, String mensagem) {
        this.nomeCampo = nomeCampo;
        this.mensagem = mensagem;
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    public void setNomeCampo(String nomeCampo) {
        this.nomeCampo = nomeCampo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
