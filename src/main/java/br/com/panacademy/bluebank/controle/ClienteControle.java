package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.dto.ClienteDTO;
import br.com.panacademy.bluebank.repositorio.ClienteRepositorio;
import br.com.panacademy.bluebank.servico.ClienteServico;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
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

    @DeleteMapping(value = "/delete") //mapear a url
    @ResponseBody //descrição da resposta
    public ResponseEntity<ClienteDTO> delete (@PathVariable Long Id) { //recebendo os dados para deletar
      //  ClienteRepositorio<ClienteDTO> ("Cliente Deletado com sucesso", HttpStatus.OK);
    }
}
