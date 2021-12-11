package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ClienteServicoTeste {


    @Autowired
    private ClienteServico clienteServico;
    private Object ClienteDTO;

//    @Test
//    public void listarTodosTest(){
//        var listaDeTodosOsClientes = clienteServico.listarTodos();
//
//        var idsDosClientesReal = listaDeTodosOsClientes.stream()
//                .map(cliente -> cliente.getId())// Aqui eu peguei os ids
//                .collect(Collectors.toList());
//
//        /**
//         * Isso aqui da muito trampo, não vale a pena!
//         * Além disso, precisariamos configurar o método Boolean equals(ClienteDTO) da
//         * classe ClienteDTO, para que ele funciona-se direito.
//         * Por isso optamos por uma abordagem mais pragmática, vamos validar só os ids.
//         */
//        /*
//        var esperado = Arrays.asList(
//                new ClienteDTO(1L, "nome", "sobrenome", "telefone", "cpf", "email", 1L, 0.0)
//        );
//         */
//
//        var listaDeIdsEsperados = Arrays.asList(1L,2L,3L);
//
//        Assert.assertEquals(listaDeIdsEsperados, idsDosClientesReal);
//    }

    @Test
    public void filtrarPorIdTest() {
        Assert.assertEquals(1L, clienteServico.filtrarPorId(1L).getId().longValue());
        Assert.assertEquals(2L, clienteServico.filtrarPorId(2L).getId().longValue());
        Assert.assertThrows(RecursoNaoEncontradoException.class, () -> { clienteServico.filtrarPorId(4L); });
        //() -> envelopa a execução do codigo pra não gerar exceção antes
        //Precisou chamar a classe do erro
    }
       // var filtroPorIdQuandoIdInvalido = ;

//     @Test
//     public void filtrarContaPorIdTest(){
//        var filCliContaIdTest = clienteServico.filtrarClientePorContaId(50001L);
//        var filtroEmFuncPorContaId =
//        Assert.assertEquals(filCliContaIdTest,filtroEmFuncPorContaId);
//    }

//    @Test
//    public void metodoDoObjetoTest(){
//        var resultadoReal= clienteServico.metodoDoObjeto(  "Gustavo");
//        var resultadoEsperado = "Ola Gustavo, sou o objeto";
//        Assert.assertEquals(resultadoEsperado,resultadoReal);
//    }
//
//    @Test
//    public void metodoDaClasseTest(){
//        var resultadoReal = ClienteServico.metodoDaClasse();
//        var resultadoEsperado = "Ola Gi, sou a classe";
//        Assert.assertEquals(resultadoEsperado, resultadoReal);
//    }
}
