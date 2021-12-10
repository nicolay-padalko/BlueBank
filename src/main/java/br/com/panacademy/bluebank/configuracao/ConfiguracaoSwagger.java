package br.com.panacademy.bluebank.configuracao;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class ConfiguracaoSwagger {

    @Bean
    public Docket BlueBankApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("br.com.panacademy.bluebank"))
                    .paths(PathSelectors.any())
                    .build()
                    .useDefaultResponseMessages(false)
                    .globalResponseMessage(RequestMethod.GET, responseMessage())
                    .globalResponseMessage(RequestMethod.POST, responseMessage())
                    .globalResponseMessage(RequestMethod.PUT, responseMessage())
                    .globalResponseMessage(RequestMethod.DELETE, responseMessage())
                    .apiInfo(apiInfo())
                    .securitySchemes(List.of(new ApiKey("Token Acces", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
                    .securityContexts(List.of(securityContext()));
    }

    private List<ResponseMessage> responseMessage()
    {
        return new ArrayList<ResponseMessage>() {{
                    add(new ResponseMessageBuilder()
                    .code(404)
                    .message("O recurso não foi encontrado")
                    .build());
        }};
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Aplicação Bluebank")
                .description("Projeto construido em Java utilizando Spring Framework, de uma aplicação bancária digital," +
                        "  para conclusão de treinamento da Gama Academy em parceria com o Banco Pan." +
                        " A aplicação permite aos usuários realizarem operações de saques, depósitos, e transferências," +
                        " como também acesso ao extrato bancário. Todas estas" +
                        " requisições são realizadas diretamente ao backend. ")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.ant("/auth/**"))
            .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("ADMIN", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(
                new SecurityReference("Token Access", authorizationScopes));
    }

}
