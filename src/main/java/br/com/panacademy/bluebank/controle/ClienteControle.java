package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.dto.cliente.AtualizarClienteDTO;
import br.com.panacademy.bluebank.dto.cliente.AtualizarCredenciaisClienteDTO;
import br.com.panacademy.bluebank.dto.cliente.ClienteDTO;
import br.com.panacademy.bluebank.modelo.Cliente;
import br.com.panacademy.bluebank.servico.ClienteServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteControle {


    private final ClienteServico clienteServico;

    public ClienteControle(ClienteServico clienteServico) {
        this.clienteServico = clienteServico;
    }


    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodosClientes(){
        List<ClienteDTO> listaClientesDTO = clienteServico.listarTodos();
        return ResponseEntity.ok(listaClientesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> filtrarPorId(@PathVariable Long id){
        ClienteDTO clienteDTO = clienteServico.filtrarPorId(id);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> salvarCliente(@RequestBody Cliente cliente){
        ClienteDTO clienteDTO = clienteServico.salvarCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(clienteDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteDTO);
    }

    @DeleteMapping(value = "/{id}") //mapear a url
    public ResponseEntity<Void> deletar(@PathVariable Long id) { //recebendo os dados para deletar
        clienteServico.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody AtualizarClienteDTO dto) {
        dto = clienteServico.atualizarCliente(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/credenciais/{id}")
    public ResponseEntity<AtualizarCredenciaisClienteDTO> atualizarCredenciais(@PathVariable Long id, @RequestBody AtualizarCredenciaisClienteDTO dto) {
        dto = clienteServico.atualizarCredenciaisCliente(id, dto);
        return ResponseEntity.ok().body(dto);
    }

}
