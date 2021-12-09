
package br.com.panacademy.bluebank.excecao.modelo;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroModelo {
    private List<CampoMensagem> erros = new ArrayList<>();

    public ErroValidacao() {
    }

    public ErroValidacao(ErroModelo erroModelo) {
        super(erroModelo.getTimestamp(), erroModelo.getStatus(), erroModelo.getError(), erroModelo.getMessage(), erroModelo.getPath());
    }

    public List<CampoMensagem> getErros() {
        return erros;
    }


    public void adicionarErros(String nomeCampo, String mensagem) {
        erros.add(new CampoMensagem(nomeCampo, mensagem));
    }

}
