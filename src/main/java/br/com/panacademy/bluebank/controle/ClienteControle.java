package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.dto.ClienteDTO;
import br.com.panacademy.bluebank.servico.ClienteServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(value = "/{id}") //mapear a url
    public ResponseEntity<Void> deletar(@PathVariable Long id) { //recebendo os dados para deletar
        clienteServico.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        dto = clienteServico.editar(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
