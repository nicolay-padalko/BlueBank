package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.servico.TransacaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoControle {

    private final TransacaoServico transacaoServico;

    public TransacaoControle(TransacaoServico transacaoServico) {
        this.transacaoServico = transacaoServico;
    }
}
