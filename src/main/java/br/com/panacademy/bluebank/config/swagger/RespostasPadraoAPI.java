package br.com.panacademy.bluebank.config.swagger;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, TYPE})
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "A solicitação foi atendida com sucesso"),
        @ApiResponse(code = 401, message = "Você deve se autenticar para acessar o recurso."),
        @ApiResponse(code = 403, message = "Você não está autorizado a acessar este recurso."),
        @ApiResponse(code = 404, message = "Recurso não encontrado"),
})
public @interface RespostasPadraoAPI {
}
