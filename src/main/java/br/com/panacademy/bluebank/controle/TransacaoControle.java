package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.dto.TransacaoDTO;
import br.com.panacademy.bluebank.dto.transacao.DepositarDTO;
import br.com.panacademy.bluebank.servico.TransacaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
public class TransacaoControle {

    private final TransacaoServico transacaoServico;

    public TransacaoControle(TransacaoServico transacaoServico) {
        this.transacaoServico = transacaoServico;
    }

    @PostMapping(value = "depositar/{id}")
    public ResponseEntity<DepositarDTO> depositar(@PathVariable Long id, @RequestBody DepositarDTO dto){
        dto = transacaoServico.depositar(id, dto);
        return ResponseEntity.ok(dto);
    }

}
