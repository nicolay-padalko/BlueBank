package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.config.security.TokenServico;
import br.com.panacademy.bluebank.dto.transacao.TransferirDTO;
import br.com.panacademy.bluebank.dto.transacao.DepositarDTO;
import br.com.panacademy.bluebank.dto.transacao.SacarDTO;
import br.com.panacademy.bluebank.modelo.Conta;
import br.com.panacademy.bluebank.modelo.Transacao;
import br.com.panacademy.bluebank.servico.ClienteServico;
import br.com.panacademy.bluebank.servico.ContaServico;
import br.com.panacademy.bluebank.servico.TransacaoServico;
<<<<<<< HEAD
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
=======
>>>>>>> 9a9138dbca151b7ea0b75aa5cd3c64d9cd6c64b6
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<DepositarDTO> depositar(HttpServletRequest request, @Valid @RequestBody DepositarDTO dto, BindingResult result){
        String token = recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        Conta conta = localizaConta(idUsuario);
        dto = transacaoServico.depositar(conta.getContaId(), dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "sacar")
    public ResponseEntity<SacarDTO> sacar(HttpServletRequest request, @RequestBody SacarDTO dto){
        String token = recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        Conta conta = localizaConta(idUsuario);
        dto = transacaoServico.sacar(conta.getContaId(), dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "transferir/{contaIdDestino}")
    public ResponseEntity<TransferirDTO> transferir(HttpServletRequest request,
                                                    @PathVariable("contaIdDestino") Long idDestino,
                                                    @Valid @RequestBody TransferirDTO dto){

        String token = recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        Conta conta = localizaConta(idUsuario);
        dto = transacaoServico.transferir(conta.getContaId(), idDestino, dto);
        return ResponseEntity.ok(dto);

    }

    @PostMapping(value = "depositar/{contaId}")
    @ApiOperation("Depósito na conta do cliente, filtrado por ID")
    public ResponseEntity<DepositarDTO> depositar(@PathVariable("contaId") Long id, @Valid @RequestBody DepositarDTO dto, BindingResult result){
        dto = transacaoServico.depositar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "sacar/{contaId}")
    @ApiOperation("Saque da conta do cliente, filtrado por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "O saldo informado é insuficiente"),
    })
    public ResponseEntity<SacarDTO> sacar(@PathVariable("contaId") Long id, @RequestBody SacarDTO dto){
        dto = transacaoServico.sacar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "transferir/{contaIdOrigem}/{contaIdDestino}")
<<<<<<< HEAD
    @ApiOperation("Transferência entre clientes do banco")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "O saldo informado é insuficiente"),
    })
    public ResponseEntity<TransferirDTO> transferir(@PathVariable("contaIdOrigem") Long idOrigem,
=======
    public ResponseEntity<TransferirDTO> transferir(@PathVariable("contaIdOrigem") Long id,
>>>>>>> 9a9138dbca151b7ea0b75aa5cd3c64d9cd6c64b6
                                                    @PathVariable("contaIdDestino") Long idDestino,
                                                    @Valid @RequestBody TransferirDTO dto){

        dto = transacaoServico.transferir(id, idDestino, dto);
        return ResponseEntity.ok(dto);

    }

    @GetMapping
<<<<<<< HEAD
    @ApiOperation("Lista todas as transações efetuadas")
    public ResponseEntity<List<Transacao>> listarTodasTransacoes(){
        List<Transacao> listaTransacoesDTO = transacaoServico.listarTodos();
=======
    public ResponseEntity<List<Transacao>> listarTodasTransacoes(HttpServletRequest request){
        String token = recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        String tipo = identificaTipo(idUsuario);
        List<Transacao> listaTransacoesDTO = new ArrayList<>();

        if(tipo.equals("ADMIN")) {
            listaTransacoesDTO = transacaoServico.listarTodos();
        }else if(tipo.equals("CLIENTE")){
            Conta conta = localizaConta(idUsuario);
            listaTransacoesDTO = transacaoServico.listarTodosDoUsuario(conta.getContaId());
        }

>>>>>>> 9a9138dbca151b7ea0b75aa5cd3c64d9cd6c64b6
        return ResponseEntity.ok(listaTransacoesDTO);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7, token.length());
    }

    private Conta localizaConta(Long idUsuario){
        return contaServico.filtrarContaPorIdUsuario(idUsuario);
    }

    private String identificaTipo(Long idUsuario) {
        return clienteServico.identificaTipoPorId(idUsuario);
    }

}
