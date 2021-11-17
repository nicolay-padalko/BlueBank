package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.dto.ClienteDTO;
import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.modelo.Cliente;
import br.com.panacademy.bluebank.repositorio.ClienteRepositorio;
import br.com.panacademy.bluebank.repositorio.ContaRepositorio;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServico {

    private final ClienteRepositorio clienteRepositorio;
    private final ContaRepositorio contaRepositorio;

    public ClienteServico(ClienteRepositorio clienteRepositorio, ContaRepositorio contaRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
        this.contaRepositorio = contaRepositorio;
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> listarTodos(){
        List<Cliente> listaClientes = clienteRepositorio.findAll();
        return listaClientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    public void deletarCliente(Long id) {
        try{
            clienteRepositorio.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontradoException("Recurso não encontrado " + id);
        }
    }

}
