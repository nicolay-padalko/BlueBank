package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.dto.ContaDTO;
import br.com.panacademy.bluebank.servico.ContaServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/contas")
@Api(value="API REST BlueBank")
@CrossOrigin(origins = "*")
public class ContaControle {

 private final ContaServico contaServico;

    public ContaControle(ContaServico contaServico) {
        this.contaServico = contaServico;
    }

    @GetMapping
    @ApiOperation("Lista todas as contas")
    public ResponseEntity<List<ContaDTO>> listarTodasAsContas(){
        List<ContaDTO> listaContaDTO = contaServico.listarTodas();
        return ResponseEntity.ok(listaContaDTO);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Busca uma conta pelo ID")
    public ResponseEntity<ContaDTO> filtrarContaPorId(@PathVariable Long id) {
        ContaDTO conta = contaServico.filtrarContaPorId(id);
        return ResponseEntity.ok(conta);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Deleta uma conta")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        contaServico.deletarConta(id);
        return ResponseEntity.noContent().build();
    }

}
