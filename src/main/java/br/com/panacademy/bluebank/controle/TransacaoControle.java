package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.dto.transacao.TransferirDTO;
import br.com.panacademy.bluebank.dto.transacao.DepositarDTO;
import br.com.panacademy.bluebank.dto.transacao.SacarDTO;
import br.com.panacademy.bluebank.modelo.Transacao;
import br.com.panacademy.bluebank.servico.TransacaoServico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoControle {

    private final TransacaoServico transacaoServico;
    private final TokenServico tokenServico;

    public TransacaoControle(TransacaoServico transacaoServico, TokenServico tokenServico {
        this.transacaoServico = transacaoServico;
        this.tokenServico = tokenServico;
    }
/*
    @PostMapping(value = "depositar/{contaId}")
    public ResponseEntity<DepositarDTO> depositar(@PathVariable("contaId") Long id, @Valid @RequestBody DepositarDTO dto, BindingResult result){
        dto = transacaoServico.depositar(id, dto);
        return ResponseEntity.ok(dto);
    } */
    
    @PostMapping(value = "depositar")
    public ResponseEntity<DepositarDTO> depositar(HttpServletRequest request, @Valid @RequestBody DepositarDTO dto, BindingResult result){
        AutenticacaoViaTokenFiltro instancia = new AutenticacaoViaTokenFiltro();
        String token = instancia.recuperarToken(request);

        Long idUsuario = tokenServico.getIdUsuario(token);

        dto = transacaoServico.depositar(idUsuario, dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "sacar/{contaId}")
    public ResponseEntity<SacarDTO> sacar(@PathVariable("contaId") Long id, @RequestBody SacarDTO dto){
        dto = transacaoServico.sacar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "transferir/{contaIdOrigem}/{contaIdDestino}")
    public ResponseEntity<TransferirDTO> transferir(@PathVariable("contaIdOrigem") Long idOrigem,
                                                    @PathVariable("contaIdDestino") Long idDestino,
                                                    @Valid @RequestBody TransferirDTO dto){

        dto = transacaoServico.transferir(idOrigem, idDestino, dto);
        return ResponseEntity.ok(dto);

    }

    @GetMapping
    public ResponseEntity<List<Transacao>> listarTodasTransacoes(){
        List<Transacao> listaTransacoesDTO = transacaoServico.listarTodos();
        return ResponseEntity.ok(listaTransacoesDTO);
    }

}
