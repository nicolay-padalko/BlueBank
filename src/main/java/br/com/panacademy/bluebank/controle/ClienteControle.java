package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.config.aws.AWSSNSConfig;
import br.com.panacademy.bluebank.dto.cliente.AtualizarClienteDTO;
import br.com.panacademy.bluebank.dto.cliente.AtualizarCredenciaisClienteDTO;
import br.com.panacademy.bluebank.dto.cliente.CadastrarClienteDTO;
import br.com.panacademy.bluebank.dto.cliente.ClienteDTO;
import br.com.panacademy.bluebank.servico.ClienteServico;
import io.swagger.annotations.ApiOperation;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
public class ClienteControle {

    private final ClienteServico clienteServico;
    private final AmazonSNSClient snsClient;

    String TOPIC_ARN = "CRIAR E COLOCAR";

    public ClienteControle(ClienteServico clienteServico, AWSSNSConfig snsClient, AmazonSNSClient snsClient1) {
        this.clienteServico = clienteServico;
        this.snsClient = snsClient1;
    }


    @GetMapping
    @ApiOperation(("Lista todos os clientes"))
    public ResponseEntity<List<ClienteDTO>> listarTodosClientes(){
        List<ClienteDTO> listaClientesDTO = clienteServico.listarTodos();
        return ResponseEntity.ok(listaClientesDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca e retorna um cliente, filtrando pelo ID")
    public ResponseEntity<ClienteDTO> filtrarPorId(@PathVariable Long id){
        ClienteDTO clienteDTO = clienteServico.filtrarPorId(id);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping
    @ApiOperation("Cadastra um cliente, com atribuição dinâmica de ID")
    public ResponseEntity<ClienteDTO> salvarCliente(@Valid @RequestBody CadastrarClienteDTO cliente){
        ClienteDTO clienteDTO = clienteServico.salvarCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(clienteDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Busca e deleta um cliente, filtrando pelo ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteServico.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualização de telefone, email e senha do cliente, filtrando pelo ID")
    public ResponseEntity<AtualizarClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody AtualizarClienteDTO dto) {
        dto = clienteServico.atualizarCliente(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/credenciais/{id}")
    @ApiOperation("Atualiza as credenciais do cliente, filtrando pelo ID")
    public ResponseEntity<AtualizarCredenciaisClienteDTO> atualizarCredenciais(@PathVariable Long id, @RequestBody AtualizarCredenciaisClienteDTO dto) {
        dto = clienteServico.atualizarCredenciaisCliente(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/cadastrarEmail/{email}")
    public void subscreverCliente(@PathVariable("email") String email){
        SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, "email", email);
        snsClient.subscribe(request);
    }

}
