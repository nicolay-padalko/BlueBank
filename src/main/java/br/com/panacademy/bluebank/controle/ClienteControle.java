package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.dto.ClienteDTO;
import br.com.panacademy.bluebank.modelo.Cliente;
import br.com.panacademy.bluebank.servico.ClienteServico;
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.BeanUtils;
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
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        dto = clienteServico.editar(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
