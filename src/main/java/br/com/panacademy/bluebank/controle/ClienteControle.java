package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.config.security.TokenServico;
import br.com.panacademy.bluebank.config.swagger.RespostaDelecaoAPI;
import br.com.panacademy.bluebank.config.swagger.RespostasCriacaoRecursoAPI;
import br.com.panacademy.bluebank.config.swagger.RespostasPadraoAPI;
import br.com.panacademy.bluebank.dto.usuario.cliente.AtualizarClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.AtualizarCredenciaisClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.CadastrarClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.ClienteDTO;
import br.com.panacademy.bluebank.servico.ClienteServico;
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
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
public class ClienteControle {

    private final ClienteServico clienteServico;
    private final TokenServico tokenServico;


    public ClienteControle(ClienteServico clienteServico, TokenServico tokenServico) {
        this.clienteServico = clienteServico;
        this.tokenServico = tokenServico;
    }

    @GetMapping
    @ApiOperation(("Lista todos os clientes"))
    @RespostasPadraoAPI
    public ResponseEntity<Page<ClienteDTO>> listarTodosClientes(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
                                                                @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                                @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ClienteDTO> listClienteDTO = clienteServico.listarTodos(request, pageRequest);
        return ResponseEntity.ok(listClienteDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation("Lista um cliente, filtrando pelo ID")
    @RespostasPadraoAPI
    public ResponseEntity<ClienteDTO> filtrarPorId(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteServico.filtrarPorId(id);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping
    @ApiOperation("Cadastra um cliente, com atribuição dinâmica de ID")
    @RespostasCriacaoRecursoAPI
    public ResponseEntity<ClienteDTO> salvarCliente(@Valid @RequestBody CadastrarClienteDTO cliente) {
        ClienteDTO clienteDTO = clienteServico.salvarCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(clienteDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Deleta um cliente, filtrando pelo ID")
    @RespostaDelecaoAPI
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteServico.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    @ApiOperation("Atualiza um cliente")
    @RespostasCriacaoRecursoAPI
    public ResponseEntity<AtualizarClienteDTO> atualizarCliente(HttpServletRequest request, @RequestBody AtualizarClienteDTO dto) {
        String token = tokenServico.recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        dto = clienteServico.atualizarCliente(idUsuario, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualização de telefone, email e senha do cliente, filtrando pelo ID")
    @RespostasCriacaoRecursoAPI
    public ResponseEntity<AtualizarClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody AtualizarClienteDTO dto) {
        dto = clienteServico.atualizarCliente(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/credenciais/{id}")
    @ApiOperation("Atualiza as credenciais do cliente, filtrando pelo ID")
    @RespostasCriacaoRecursoAPI
    public ResponseEntity<AtualizarCredenciaisClienteDTO> atualizarCredenciais(@PathVariable Long id, @RequestBody AtualizarCredenciaisClienteDTO dto) {
        dto = clienteServico.atualizarCredenciaisCliente(id, dto);
        return ResponseEntity.ok().body(dto);
    }

}

