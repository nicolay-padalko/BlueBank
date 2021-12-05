package br.com.panacademy.bluebank.config.security;

import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.modelo.Cliente;
import br.com.panacademy.bluebank.repositorio.ClienteRepositorio;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final ClienteRepositorio clienteRepositorio;

    public AutenticacaoService(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Cliente cliente = clienteRepositorio.findByEmail(s).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: "+ s));;
        return cliente;
    }
}
