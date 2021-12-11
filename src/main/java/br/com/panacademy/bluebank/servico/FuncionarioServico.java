package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.dto.usuario.funcionario.AtualizarCredenciaisFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.AtualizarFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.CadastrarFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.FuncionarioDTO;
import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.modelo.Perfil;
import br.com.panacademy.bluebank.modelo.usuario.Funcionario;
import br.com.panacademy.bluebank.repositorio.FuncionarioRepositorio;
import br.com.panacademy.bluebank.repositorio.PerfilRespositorio;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioServico {

    private final FuncionarioRepositorio funcionarioRepositorio;
    private final PerfilRespositorio perfilRespositorio;
    private final AmazonSNSClient snsClient;

    String TOPIC_ARN = "arn:aws:sns:us-east-1:965934840569:SQ02T2";

    public FuncionarioServico(FuncionarioRepositorio funcionarioRepositorio, PerfilRespositorio perfilRespositorio, AmazonSNSClient snsClient) {
        this.funcionarioRepositorio = funcionarioRepositorio;
        this.perfilRespositorio = perfilRespositorio;
        this.snsClient = snsClient;
    }


    @Transactional(readOnly = true)
    public Page<FuncionarioDTO> listarTodos(PageRequest pageRequest) {
        Page<Funcionario> listaFuncionarios = funcionarioRepositorio.findAll(pageRequest);
        return listaFuncionarios.map(FuncionarioDTO::new);
    }

    @Transactional(readOnly = true)
    public FuncionarioDTO filtrarPorId(Long id) {
        Funcionario funcionario = funcionarioRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionario não encontrado: " + id));
        return new FuncionarioDTO(funcionario);
    }

    @Transactional
    public FuncionarioDTO salvarFuncionario(CadastrarFuncionarioDTO funcionario) {

        String senhaEncriptografada = new BCryptPasswordEncoder().encode(funcionario.getSenha());
        funcionario.setSenha(senhaEncriptografada);

        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        BeanUtils.copyProperties(funcionario, funcionarioDTO);

        Funcionario funcionario1 = funcionario.toFuncionario();
        funcionario1.adicionarPefil(perfilRespositorio.findById(2L).get());
        funcionario1.adicionarPefil(perfilRespositorio.findById(1L).get());
        funcionarioRepositorio.save(funcionario1);
        PublishRequest publishRequest =
                new PublishRequest(TOPIC_ARN, buildEmailBody(funcionarioDTO), "Notification: Cadastro de funcionario");
        snsClient.publish(publishRequest);
        return new FuncionarioDTO(funcionario1);
    }

    public void deletarFuncionario(Long id) {
        try {
            funcionarioRepositorio.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontradoException("Funcionario não encontrado " + id);
        }
    }

    @Transactional
    public AtualizarCredenciaisFuncionarioDTO atualizarCredenciaisFuncionario(Long id, AtualizarCredenciaisFuncionarioDTO dto) {
        Funcionario entidade = funcionarioRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionario de id: " + id + " não encontrado"));

        BeanUtils.copyProperties(dto, entidade);

        String senhaCodificada = new BCryptPasswordEncoder().encode(dto.getSenha());

        entidade.setSenha(senhaCodificada);

        entidade = funcionarioRepositorio.save(entidade);
        return new AtualizarCredenciaisFuncionarioDTO(entidade, dto);
    }


    @Transactional
    public AtualizarFuncionarioDTO atualizarFuncionario(Long id, AtualizarFuncionarioDTO dto) {
        Funcionario entidade = funcionarioRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionario não encontrado: " + id));

        BeanUtils.copyProperties(dto, entidade);

        String senhaCodificada = new BCryptPasswordEncoder().encode(dto.getSenha());

        entidade.setSenha(senhaCodificada);

        entidade = funcionarioRepositorio.save(entidade);
        return new AtualizarFuncionarioDTO(entidade, dto);
    }

    private String buildEmailBody(FuncionarioDTO funcionario) {
        return "Novo cadastro: " +
                "\nUsuario: " + funcionario.getEmail() +
                "\nNome: " + funcionario.getNome() +
                "\nSobrenome: " + funcionario.getSobrenome() +
                "\nCPF: " + funcionario.getCpf() +
                "\nTelefone: " + funcionario.getTelefone();

    }


}