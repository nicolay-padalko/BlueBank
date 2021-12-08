package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.dto.transacao.TransferirDTO;
import br.com.panacademy.bluebank.dto.transacao.DepositarDTO;
import br.com.panacademy.bluebank.dto.transacao.SacarDTO;
import br.com.panacademy.bluebank.modelo.Transacao;
import br.com.panacademy.bluebank.servico.TransacaoServico;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transacoes")
public class TransacaoControle {

    private final TransacaoServico transacaoServico;

    public TransacaoControle(TransacaoServico transacaoServico) {
        this.transacaoServico = transacaoServico;
    }

    @PostMapping(value = "depositar/{contaId}")
    @ApiOperation("Depósito na conta do cliente, filtrado por ID")
    public ResponseEntity<DepositarDTO> depositar(@PathVariable("contaId") Long id, @Valid @RequestBody DepositarDTO dto, BindingResult result){
        dto = transacaoServico.depositar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "sacar/{contaId}")
    @ApiOperation("Saque da conta do cliente, filtrado por ID")
    public ResponseEntity<SacarDTO> sacar(@PathVariable("contaId") Long id, @RequestBody SacarDTO dto){
        dto = transacaoServico.sacar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "transferir/{contaIdOrigem}/{contaIdDestino}")
    @ApiOperation("Transferência entre clientes do banco")
    public ResponseEntity<TransferirDTO> transferir(@PathVariable("contaIdOrigem") Long idOrigem,
                                                    @PathVariable("contaIdDestino") Long idDestino,
                                                    @Valid @RequestBody TransferirDTO dto){

        dto = transacaoServico.transferir(idOrigem, idDestino, dto);
        return ResponseEntity.ok(dto);

    }

    @GetMapping
    @ApiOperation("Lista todas as transações efetuadas")
    public ResponseEntity<List<Transacao>> listarTodasTransacoes(){
        List<Transacao> listaTransacoesDTO = transacaoServico.listarTodos();
        return ResponseEntity.ok(listaTransacoesDTO);
    }

}
