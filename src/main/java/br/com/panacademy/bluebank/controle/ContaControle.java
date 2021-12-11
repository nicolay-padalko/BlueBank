package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.config.swagger.RespostasPadraoAPI;
import br.com.panacademy.bluebank.dto.ContaDTO;
import br.com.panacademy.bluebank.servico.ContaServico;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/contas")
public class ContaControle {

    private final ContaServico contaServico;


    public ContaControle(ContaServico contaServico) {
        this.contaServico = contaServico;
    }

    @GetMapping
    @ApiOperation("Lista todas as contas")
    @RespostasPadraoAPI
    public ResponseEntity<Page<ContaDTO>> listarTodasAsContas(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
                                                              @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                              @RequestParam(value = "orderBy", defaultValue = "contaId") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ContaDTO> listaContaDTO = contaServico.listarTodas(request, pageRequest);

        return ResponseEntity.ok(listaContaDTO);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Lista uma conta, filtrando pelo ID")
    @RespostasPadraoAPI
    public ResponseEntity<ContaDTO> filtrarContaPorId(@PathVariable Long id) {
        ContaDTO conta = contaServico.filtrarContaPorId(id);
        return ResponseEntity.ok(conta);
    }

}

