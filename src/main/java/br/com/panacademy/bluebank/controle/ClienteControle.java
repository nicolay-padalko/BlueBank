package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.config.security.TokenServico;
import br.com.panacademy.bluebank.dto.usuario.cliente.AtualizarClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.AtualizarCredenciaisClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.CadastrarClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.ClienteDTO;
import br.com.panacademy.bluebank.servico.ClienteServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteControle {

    private final ClienteServico clienteServico;
    private final TokenServico tokenServico;


    public ClienteControle(ClienteServico clienteServico, TokenServico tokenServico) {
        this.clienteServico = clienteServico;
        this.tokenServico = tokenServico;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodosClientes() {
        List<ClienteDTO> listaClientesDTO = clienteServico.listarTodos();
        return ResponseEntity.ok(listaClientesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> filtrarPorId(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteServico.filtrarPorId(id);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> salvarCliente(@Valid @RequestBody CadastrarClienteDTO cliente) {
        ClienteDTO clienteDTO = clienteServico.salvarCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(clienteDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteServico.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<AtualizarClienteDTO> atualizarCliente(HttpServletRequest request, @Valid @RequestBody AtualizarClienteDTO dto) {
        String token = recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        dto = clienteServico.atualizarCliente(idUsuario, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarClienteDTO> atualizarCliente(@PathVariable Long id, @Valid @RequestBody AtualizarClienteDTO dto) {
        dto = clienteServico.atualizarCliente(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/credenciais/{id}")
    public ResponseEntity<AtualizarCredenciaisClienteDTO> atualizarCredenciais(@PathVariable Long id, @Valid @RequestBody AtualizarCredenciaisClienteDTO dto) {
        dto = clienteServico.atualizarCredenciaisCliente(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }

}