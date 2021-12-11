package br.com.panacademy.bluebank.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
public class ConfiguracaoSwagger {

    @Bean
    public Docket BlueBankApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(new ApiKey("JWT", "Authorization", "header")))
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.panacademy.bluebank"))
                .paths(PathSelectors.any())
                .build();

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
            .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(
                new SecurityReference("JWT", authorizationScopes));
    }

}
