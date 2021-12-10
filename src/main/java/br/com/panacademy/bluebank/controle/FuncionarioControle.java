package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.config.security.TokenServico;
import br.com.panacademy.bluebank.dto.usuario.funcionario.AtualizarCredenciaisFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.AtualizarFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.CadastrarFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.FuncionarioDTO;
import br.com.panacademy.bluebank.servico.FuncionarioServico;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.SubscribeRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
    public ResponseEntity<List<FuncionarioDTO>> listarTodosFuncionarios() {
        List<FuncionarioDTO> listaFuncionariosDTO = funcionarioServico.listarTodos();
        return ResponseEntity.ok(listaFuncionariosDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation("Lista um funcionário, filtrando pelo ID")
    public ResponseEntity<FuncionarioDTO> filtrarPorId(@PathVariable Long id) {
        FuncionarioDTO funcionarioDTO = funcionarioServico.filtrarPorId(id);
        return ResponseEntity.ok(funcionarioDTO);
    }

    @PostMapping
    @ApiOperation("Cadastra um funcionário")
    public ResponseEntity<FuncionarioDTO> salvarFuncionario(@Valid @RequestBody CadastrarFuncionarioDTO funcionario) {
        FuncionarioDTO funcionarioDTO = funcionarioServico.salvarFuncionario(funcionario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(funcionarioDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(funcionarioDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Deleta o cadastro de um funcionário")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioServico.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    @ApiOperation("Atualiza os dados de um funcionário")
    public ResponseEntity<AtualizarFuncionarioDTO> atualizarFuncionario(HttpServletRequest request, @RequestBody AtualizarFuncionarioDTO dto) {
        String token = recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        dto = funcionarioServico.atualizarFuncionario(idUsuario, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza os dados de um funcionário, filtrando pelo ID")
    public ResponseEntity<AtualizarFuncionarioDTO> atualizarFuncionario(@PathVariable Long id, @RequestBody AtualizarFuncionarioDTO dto) {
        dto = funcionarioServico.atualizarFuncionario(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/credenciais/{id}")
    @ApiOperation("Atualiza as credenciais de um funcionário")
    public ResponseEntity<AtualizarCredenciaisFuncionarioDTO> atualizarCredenciais(@PathVariable Long id, @RequestBody AtualizarCredenciaisFuncionarioDTO dto) {
        dto = funcionarioServico.atualizarCredenciaisFuncionario(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/cadastrarEmail/{email}")
    @ApiOperation("Confirma o cadastro de um funcionário, com aviso por e-mail")
    public void subscreverFuncionario(@PathVariable("email") String email) {
        SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, "email", email);
        snsClient.subscribe(request);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7, token.length());
    }




}
