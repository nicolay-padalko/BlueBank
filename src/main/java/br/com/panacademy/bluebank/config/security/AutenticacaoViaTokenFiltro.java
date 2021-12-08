package br.com.panacademy.bluebank.config.security;

import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.modelo.Cliente;
import br.com.panacademy.bluebank.repositorio.ClienteRepositorio;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFiltro extends OncePerRequestFilter {

    private final TokenServico tokenServico;
    private final ClienteRepositorio clienteRepositorio;

    public AutenticacaoViaTokenFiltro(TokenServico tokenServico, ClienteRepositorio clienteRepositorio) {
        this.tokenServico = tokenServico;
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);
        Boolean valido = tokenServico.isTokenValido(token);
        if(valido){
            autenticarCliente(token);
        }
        filterChain.doFilter(request, response);

    }

    private void autenticarCliente(String token) {
        Long idCliente = tokenServico.getIdUsuario(token);

        Cliente cliente = clienteRepositorio.findById(idCliente)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: " + idCliente));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7, token.length());
    }
}
