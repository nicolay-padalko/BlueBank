package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.dto.ClienteDTO;
import br.com.panacademy.bluebank.dto.ContaDTO;
import br.com.panacademy.bluebank.servico.ClienteServico;
import br.com.panacademy.bluebank.servico.ContaServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/contas")
public class ContaControle {

 private final ContaServico contaServico;

    public ContaControle(ContaServico contaServico) {
        this.contaServico = contaServico;
    }

    @GetMapping
    public ResponseEntity<List<ContaDTO>> listarTodasAsContas(){
        List<ContaDTO> listaContaDTO = contaServico.listarTodas();
        return ResponseEntity.ok(listaContaDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        contaServico.deletarConta(id);
        return ResponseEntity.noContent().build();
    }

}
