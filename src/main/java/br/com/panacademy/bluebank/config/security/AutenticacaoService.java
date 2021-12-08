package br.com.panacademy.bluebank.config.security;

import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.modelo.usuario.Usuario;
import br.com.panacademy.bluebank.repositorio.UsuarioRepositorio;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;

    public AutenticacaoService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(s).orElseThrow(() -> new RecursoNaoEncontradoException("Dados invalidos"));;
        return usuario;
    }
}
