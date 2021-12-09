package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.config.security.TokenServico;
import br.com.panacademy.bluebank.dto.ContaDTO;
import br.com.panacademy.bluebank.modelo.Conta;
import br.com.panacademy.bluebank.servico.ClienteServico;
import br.com.panacademy.bluebank.servico.ContaServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/contas")
public class ContaControle {

    private final ContaServico contaServico;
    private final TokenServico tokenServico;
    private final ClienteServico clienteServico;

    public ContaControle(ContaServico contaServico, TokenServico tokenServico, ClienteServico clienteServico) {
        this.contaServico = contaServico;
        this.tokenServico = tokenServico;
        this.clienteServico = clienteServico;
    }

    @GetMapping
    public ResponseEntity<List<ContaDTO>> listarTodasAsContas(HttpServletRequest request) {
        String token = recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        String tipo = identificaTipo(idUsuario);
        List<ContaDTO> listaContaDTO = new ArrayList<>();

        if (tipo.equals("ADMIN")) {
            listaContaDTO = contaServico.listarTodas();
        } else if (tipo.equals("CLIENTE")) {
            Conta conta = contaServico.filtrarContaPorIdUsuario(idUsuario);
            listaContaDTO.add(new ContaDTO(conta));
        }
        return ResponseEntity.ok(listaContaDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContaDTO> filtrarContaPorId(@PathVariable Long id) {
        ContaDTO conta = contaServico.filtrarContaPorId(id);
        return ResponseEntity.ok(conta);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }

    private String identificaTipo(Long idUsuario) {
        return clienteServico.identificaTipoPorId(idUsuario);
    }


}

