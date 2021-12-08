package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.dto.usuario.cliente.AtualizarClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.AtualizarCredenciaisClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.CadastrarClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.ClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.AtualizarCredenciaisFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.AtualizarFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.CadastrarFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.FuncionarioDTO;
import br.com.panacademy.bluebank.servico.FuncionarioServico;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioControle {

    private final FuncionarioServico funcionarioServico;
    private final AmazonSNSClient snsClient;

    String TOPIC_ARN = "arn:aws:sns:us-east-1:965934840569:PanCodersSNSTopic";

    public FuncionarioControle(FuncionarioServico funcionarioServico, AmazonSNSClient snsClient) {
        this.funcionarioServico = funcionarioServico;
        this.snsClient = snsClient;
    }


    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listarTodosFuncionarios(){
        List<FuncionarioDTO> listaFuncionariosDTO = funcionarioServico.listarTodos();
        return ResponseEntity.ok(listaFuncionariosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> filtrarPorId(@PathVariable Long id){
        FuncionarioDTO funcionarioDTO = funcionarioServico.filtrarPorId(id);
        return ResponseEntity.ok(funcionarioDTO);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> salvarFuncionario(@Valid @RequestBody CadastrarFuncionarioDTO funcionario){
        FuncionarioDTO funcionarioDTO = funcionarioServico.salvarFuncionario(funcionario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(funcionarioDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(funcionarioDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioServico.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarFuncionarioDTO> atualizarFuncionario(@PathVariable Long id, @RequestBody AtualizarFuncionarioDTO dto) {
        dto = funcionarioServico.atualizarFuncionario(id, dto);
            return ResponseEntity.ok().body(dto);
        }

    @PutMapping("/credenciais/{id}")
    public ResponseEntity<AtualizarCredenciaisFuncionarioDTO> atualizarCredenciais(@PathVariable Long id, @RequestBody AtualizarCredenciaisFuncionarioDTO dto) {
        dto = funcionarioServico.atualizarCredenciaisFuncionario(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/cadastrarEmail/{email}")
    public void subscreverFuncionario(@PathVariable("email") String email){
        SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, "email", email);
        snsClient.subscribe(request);
    }

}
