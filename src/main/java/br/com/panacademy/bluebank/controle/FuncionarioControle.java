package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.config.security.TokenServico;
import br.com.panacademy.bluebank.config.swagger.RespostaDelecaoAPI;
import br.com.panacademy.bluebank.config.swagger.RespostasCriacaoRecursoAPI;
import br.com.panacademy.bluebank.config.swagger.RespostasPadraoAPI;
import br.com.panacademy.bluebank.dto.usuario.funcionario.AtualizarCredenciaisFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.AtualizarFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.CadastrarFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.FuncionarioDTO;
import br.com.panacademy.bluebank.servico.FuncionarioServico;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.SubscribeRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioControle {

    private final FuncionarioServico funcionarioServico;
    private final AmazonSNSClient snsClient;
    private final TokenServico tokenServico;

    String TOPIC_ARN = "arn:aws:sns:us-east-1:965934840569:PanCodersSNSTopic";

    public FuncionarioControle(FuncionarioServico funcionarioServico, AmazonSNSClient snsClient, TokenServico tokenServico) {
        this.funcionarioServico = funcionarioServico;
        this.snsClient = snsClient;
        this.tokenServico = tokenServico;
    }


    @GetMapping
    @ApiOperation("Lista todos os funcionários")
    @RespostasPadraoAPI
    public ResponseEntity<Page<FuncionarioDTO>> listarTodosFuncionarios(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                        @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
                                                                        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                                        @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<FuncionarioDTO> listaFuncionariosDTO = funcionarioServico.listarTodos(pageRequest);
        return ResponseEntity.ok(listaFuncionariosDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation("Lista um funcionário, filtrando pelo ID")
    @RespostasPadraoAPI
    public ResponseEntity<FuncionarioDTO> filtrarPorId(@PathVariable Long id) {
        FuncionarioDTO funcionarioDTO = funcionarioServico.filtrarPorId(id);
        return ResponseEntity.ok(funcionarioDTO);
    }

    @PostMapping
    @ApiOperation("Cadastra um funcionário")
    @RespostasCriacaoRecursoAPI
    public ResponseEntity<FuncionarioDTO> salvarFuncionario(@Valid @RequestBody CadastrarFuncionarioDTO funcionario) {
        FuncionarioDTO funcionarioDTO = funcionarioServico.salvarFuncionario(funcionario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(funcionarioDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(funcionarioDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Deleta o cadastro de um funcionário")
    @RespostaDelecaoAPI
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioServico.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    @ApiOperation("Atualiza os dados de um funcionário")
    @RespostasCriacaoRecursoAPI
    public ResponseEntity<AtualizarFuncionarioDTO> atualizarFuncionario(HttpServletRequest request, @RequestBody AtualizarFuncionarioDTO dto) {
        String token = tokenServico.recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        dto = funcionarioServico.atualizarFuncionario(idUsuario, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza os dados de um funcionário, filtrando pelo ID")
    @RespostasCriacaoRecursoAPI
    public ResponseEntity<AtualizarFuncionarioDTO> atualizarFuncionario(@PathVariable Long id, @RequestBody AtualizarFuncionarioDTO dto) {
        dto = funcionarioServico.atualizarFuncionario(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/credenciais/{id}")
    @ApiOperation("Atualiza as credenciais de um funcionário")
    @RespostasCriacaoRecursoAPI
    public ResponseEntity<AtualizarCredenciaisFuncionarioDTO> atualizarCredenciais(@PathVariable Long id, @RequestBody AtualizarCredenciaisFuncionarioDTO dto) {
        dto = funcionarioServico.atualizarCredenciaisFuncionario(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/cadastrarEmail/{email}")
    @ApiOperation("Confirma o cadastro de um funcionário, com aviso por e-mail")
    @RespostasCriacaoRecursoAPI
    public void subscreverFuncionario(@PathVariable("email") String email) {
        SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, "email", email);
        snsClient.subscribe(request);
    }

}
