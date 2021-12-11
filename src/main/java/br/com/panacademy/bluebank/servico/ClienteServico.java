package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.config.security.TokenServico;
import br.com.panacademy.bluebank.dto.usuario.cliente.AtualizarClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.AtualizarCredenciaisClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.CadastrarClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.ClienteDTO;
import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.modelo.Conta;
import br.com.panacademy.bluebank.modelo.Perfil;
import br.com.panacademy.bluebank.modelo.usuario.Cliente;
import br.com.panacademy.bluebank.modelo.usuario.Usuario;
import br.com.panacademy.bluebank.repositorio.ClienteRepositorio;
import br.com.panacademy.bluebank.repositorio.ContaRepositorio;
import br.com.panacademy.bluebank.repositorio.PerfilRespositorio;
import br.com.panacademy.bluebank.repositorio.UsuarioRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServico {

    private final ClienteRepositorio clienteRepositorio;
    private final ContaRepositorio contaRepositorio;
    private final PerfilRespositorio perfilRespositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final TokenServico tokenServico;

    public ClienteServico(ClienteRepositorio clienteRepositorio, ContaRepositorio contaRepositorio, PerfilRespositorio perfilRespositorio, UsuarioRepositorio usuarioRepositorio, TokenServico tokenServico) {
        this.clienteRepositorio = clienteRepositorio;
        this.contaRepositorio = contaRepositorio;
        this.perfilRespositorio = perfilRespositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.tokenServico = tokenServico;
    }

    @Transactional(readOnly = true)
    public Page<ClienteDTO> listarTodos(HttpServletRequest request, PageRequest pageRequest) {
        String token = tokenServico.recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        String tipo = identificaTipoPorId(idUsuario);
        List<Cliente> usuario = new ArrayList<>();
        if(tipo.equals("ADMIN")) {
            Page<Cliente> listaUsuariosPaginados =  clienteRepositorio.findAll(pageRequest);
            return listaUsuariosPaginados.map(ClienteDTO::new);
        }else if(tipo.equals("CLIENTE")){
            usuario = List.of(clienteRepositorio.findById(idUsuario)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: " + idUsuario)));
        }
        Page<Cliente> cliente = new PageImpl<>(usuario);
        return cliente.map(ClienteDTO::new);
    }

    @Transactional(readOnly = true)
    public ClienteDTO filtrarPorId(Long id) {
        Cliente cliente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: " + id));
        return new ClienteDTO(cliente);
    }

    @Transactional(readOnly = true)
    Cliente filtrarClientePorContaId(Long contaId) {
        return clienteRepositorio.findByClienteByContaId(contaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente de conta id: " + contaId + " não encontrado."));
    }

    @Transactional
    public ClienteDTO salvarCliente(Cliente cliente) {
        if (cliente.getConta() == null) {
            Conta conta = new Conta();
            conta.setSaldo(0.0);
            Conta contaSalva = contaRepositorio.save(conta);
            cliente.setConta(contaSalva);
        }

        ClienteDTO clienteDTO = new ClienteDTO();
        BeanUtils.copyProperties(cliente, clienteDTO);

        clienteRepositorio.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO salvarCliente(CadastrarClienteDTO cliente) {

        Conta conta = new Conta();
        conta.setSaldo(0.0);
        Conta contaSalva = contaRepositorio.save(conta);
        cliente.setConta(contaSalva);

        String senhaEncriptografada = new BCryptPasswordEncoder().encode(cliente.getSenha());
        cliente.setSenha(senhaEncriptografada);

        ClienteDTO clienteDTO = new ClienteDTO();
        BeanUtils.copyProperties(cliente, clienteDTO);

        Cliente cliente1 = cliente.toCliente();
        cliente1.adicionarPefil(perfilRespositorio.findById(2L).get());
        clienteRepositorio.save(cliente1);
        return new ClienteDTO(cliente1);
    }

    public void deletarCliente(Long id) {
        try {
            clienteRepositorio.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado " + id);
        }
    }

    @Transactional()
    public AtualizarCredenciaisClienteDTO atualizarCredenciaisCliente(Long id, AtualizarCredenciaisClienteDTO dto) {
        Cliente entidade = clienteRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente de id: " + id + " não encontrado"));

        BeanUtils.copyProperties(dto, entidade);

        String senhaCodificada = new BCryptPasswordEncoder().encode(dto.getSenha());

        entidade.setSenha(senhaCodificada);

        entidade = clienteRepositorio.save(entidade);

        return new AtualizarCredenciaisClienteDTO(entidade, dto);
    }

    @Transactional
    public AtualizarClienteDTO atualizarCliente(Long id, AtualizarClienteDTO dto) {
        Cliente entidade = clienteRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: " + id));

        BeanUtils.copyProperties(dto, entidade);

        String senhaCodificada = new BCryptPasswordEncoder().encode(dto.getSenha());

        entidade.setSenha(senhaCodificada);

        clienteRepositorio.save(entidade);

        return new AtualizarClienteDTO(entidade, dto);
    }

    public String identificaTipoPorId(Long idUsuario) {
        Usuario entidade = usuarioRepositorio.findById(idUsuario)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario não encontrado: " + idUsuario));

        for (Perfil t : entidade.getPerfis()) {
            if (t.getNome().equals("ROLE_ADMIN")) {
                return "ADMIN";
            }
        }
        for (Perfil t : entidade.getPerfis()) {
            if (t.getNome().equals("ROLE_CLIENTE")) {
                return "CLIENTE";
            }
        }
        if (entidade.getPerfis() == null) {
            throw new RecursoNaoEncontradoException("PERFIL INVALIDO");
        }
        return null;
    }
}