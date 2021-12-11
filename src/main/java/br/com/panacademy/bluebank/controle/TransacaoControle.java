package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.config.security.TokenServico;
import br.com.panacademy.bluebank.config.swagger.RespostasApi;
import br.com.panacademy.bluebank.dto.transacao.DepositarDTO;
import br.com.panacademy.bluebank.dto.transacao.OperacaoEntradaDTO;
import br.com.panacademy.bluebank.dto.transacao.SacarDTO;
import br.com.panacademy.bluebank.dto.transacao.TransferirDTO;
import br.com.panacademy.bluebank.modelo.Conta;
import br.com.panacademy.bluebank.modelo.Transacao;
import br.com.panacademy.bluebank.servico.ClienteServico;
import br.com.panacademy.bluebank.servico.ContaServico;
import br.com.panacademy.bluebank.servico.TransacaoServico;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transacoes")
public class TransacaoControle {

    private final TransacaoServico transacaoServico;
    private final TokenServico tokenServico;
    private final ContaServico contaServico;
    private final ClienteServico clienteServico;

    public TransacaoControle(TransacaoServico transacaoServico, TokenServico tokenServico, ContaServico contaServico, ClienteServico clienteServico) {
        this.transacaoServico = transacaoServico;
        this.tokenServico = tokenServico;
        this.contaServico = contaServico;
        this.clienteServico = clienteServico;
    }

    @PostMapping(value = "depositar")
    @ApiOperation("Efetua um depósito na conta do cliente")
    @RespostasApi
    public ResponseEntity<DepositarDTO> depositar(HttpServletRequest request, @Valid @RequestBody OperacaoEntradaDTO dto){
        String token = tokenServico.recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        Conta conta = contaServico.filtrarContaPorIdUsuario(idUsuario);
        DepositarDTO depositar = transacaoServico.depositar(conta.getContaId(), dto);
        return ResponseEntity.ok(depositar);
    }

    @PostMapping(value = "sacar")
    @ApiOperation("Efetua um saque da conta do cliente")
    @RespostasApi
    public ResponseEntity<SacarDTO> sacar(HttpServletRequest request, @RequestBody OperacaoEntradaDTO dto){
        String token = tokenServico.recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        Conta conta = contaServico.filtrarContaPorIdUsuario(idUsuario);
        SacarDTO sacar = transacaoServico.sacar(conta.getContaId(), dto);
        return ResponseEntity.ok(sacar);
    }

    @PostMapping(value = "transferir/{contaIdDestino}")
    @ApiOperation("Efetua uma transferência para outro cliente do banco")
    @RespostasApi
    public ResponseEntity<TransferirDTO> transferir(HttpServletRequest request,
                                                    @PathVariable("contaIdDestino") Long idDestino,
                                                    @Valid @RequestBody OperacaoEntradaDTO dto){

        String token = tokenServico.recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        Conta conta = contaServico.filtrarContaPorIdUsuario(idUsuario);
        TransferirDTO transferirDTO = transacaoServico.transferir(conta.getContaId(), idDestino, dto);
        return ResponseEntity.ok(transferirDTO);

    }

    @PostMapping(value = "depositar/{contaId}")
    @ApiOperation("Efetua um depósito na conta do cliente, filtrado pelo ID")
    @RespostasApi
    public ResponseEntity<DepositarDTO> depositar(@PathVariable("contaId") Long id, @Valid @RequestBody OperacaoEntradaDTO dto){
        DepositarDTO depositar = transacaoServico.depositar(id, dto);
        return ResponseEntity.ok(depositar);
    }

    @PostMapping(value = "sacar/{contaId}")
    @ApiOperation("Efetua um saque da conta do cliente, filtrado pelo ID")
    @RespostasApi
    public ResponseEntity<SacarDTO> sacar(@PathVariable("contaId") Long id, @RequestBody OperacaoEntradaDTO dto){
        SacarDTO sacar = transacaoServico.sacar(id, dto);
        return ResponseEntity.ok(sacar);
    }

    @PostMapping(value = "transferir/{contaIdOrigem}/{contaIdDestino}")
    @ApiOperation("Transferência entre contas do banco, filtrada pelos ID´s")
    @RespostasApi
    public ResponseEntity<TransferirDTO> transferir(@PathVariable("contaIdOrigem") Long id,
                                                    @PathVariable("contaIdDestino") Long idDestino,
                                                    @Valid @RequestBody OperacaoEntradaDTO dto){

        TransferirDTO transferir = transacaoServico.transferir(id, idDestino, dto);
        return ResponseEntity.ok(transferir);

    }

    @GetMapping
    @ApiOperation("Lista todas as transações efetuadas")
    public ResponseEntity<List<Transacao>> listarTodasTransacoes(HttpServletRequest request){
        String token = tokenServico.recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        String tipo = clienteServico.identificaTipoPorId(idUsuario);
        List<Transacao> listaTransacoesDTO = new ArrayList<>();
        if(tipo.equals("ADMIN")) {
            listaTransacoesDTO = transacaoServico.listarTodos();
        }else if(tipo.equals("CLIENTE")){
            Conta conta = contaServico.filtrarContaPorIdUsuario(idUsuario);
            listaTransacoesDTO = transacaoServico.listarTodosDoUsuario(conta.getContaId());
        }
        return ResponseEntity.ok(listaTransacoesDTO);
    }
}
