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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
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
    public ResponseEntity<DepositarDTO> depositar(@PathVariable("contaId") Long id, @Valid @RequestBody DepositarDTO dto, BindingResult result){
        dto = transacaoServico.depositar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "sacar/{contaId}")
    public ResponseEntity<SacarDTO> sacar(@PathVariable("contaId") Long id, @RequestBody SacarDTO dto){
        dto = transacaoServico.sacar(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "transferir/{contaIdOrigem}/{contaIdDestino}")
    public ResponseEntity<TransferirDTO> transferir(@PathVariable("contaIdOrigem") Long id,
                                                    @PathVariable("contaIdDestino") Long idDestino,
                                                    @Valid @RequestBody TransferirDTO dto){

        dto = transacaoServico.transferir(id, idDestino, dto);
        return ResponseEntity.ok(dto);

    }

    @GetMapping
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
