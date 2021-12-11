package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.config.security.TokenServico;
import br.com.panacademy.bluebank.dto.ContaDTO;
import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.modelo.Conta;
import br.com.panacademy.bluebank.repositorio.ContaRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaServico {

    private final ContaRepositorio contaRepositorio;
    private final TokenServico tokenServico;
    private final ClienteServico clienteServico;

    public ContaServico(ContaRepositorio contaRepositorio, TokenServico tokenServico, ClienteServico clienteServico) {
        this.contaRepositorio = contaRepositorio;
        this.tokenServico = tokenServico;
        this.clienteServico = clienteServico;
    }

    @Transactional(readOnly = true)
    public Page<ContaDTO> listarTodas(HttpServletRequest request, PageRequest pageRequest) {
        String token = tokenServico.recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        String tipo = clienteServico.identificaTipoPorId(idUsuario);

        List<Conta> listaContaDTO = new ArrayList<>();

        if (tipo.equals("ADMIN")) {
            Page<Conta> ListaContasPaginadas = contaRepositorio.findAll(pageRequest);
            return ListaContasPaginadas.map(ContaDTO::new);
        } else if (tipo.equals("CLIENTE")) {
            listaContaDTO = List.of(contaRepositorio.findByIdUsuario(idUsuario).
                    orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada")));
        }

        Page<Conta> contaUnica = new PageImpl<>(listaContaDTO);
        return contaUnica.map(ContaDTO::new);
    }


    public ContaDTO filtrarContaPorId(Long id) {
        Conta conta = contaRepositorio.findById(id).
                orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrado: "+id));
        return new ContaDTO(conta);
    }

    public Conta filtrarContaPorIdUsuario(Long id) {
        return contaRepositorio.findByIdUsuario(id).
                orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada"));
    }

}
