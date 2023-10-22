package com.algaworks.algafood.core.springfox;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.openapi.model.CozinhasModelOpenApi;
import com.algaworks.algafood.api.openapi.model.PageableModelOpenApi;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class OpenApiConfig implements WebMvcConfigurer {
    TypeResolver typeResolver = new TypeResolver();

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
            .build()
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
            .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
            .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
            .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
            .additionalModels(typeResolver.resolve(Problem.class))
            .ignoredParameterTypes(ServletWebRequest.class, URL.class, URI.class, URLStreamHandler.class,
                Resource.class, File.class, InputStream.class)
            .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(Page.class, CozinhaModel.class), CozinhasModelOpenApi.class
            ))
            .apiInfo(apiInfo())
            .tags(new Tag("Cidades", "Gerencia as cidades"));
    }

    private List<ResponseMessage> globalGetResponseMessages() {
        return Arrays.asList(
            new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Erro interno do servidor")
                .responseModel(new ModelRef("Problem"))
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.NOT_ACCEPTABLE.value())
                .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.NOT_FOUND.value())
                .message("Recurso não encontrado")
                .responseModel(new ModelRef("Problem"))
                .build()
        );
    }

    private List<ResponseMessage> globalPostPutResponseMessages() {
        return Arrays.asList(
            new ResponseMessageBuilder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Requisição inválida (erro do cliente)")
                .responseModel(new ModelRef("Problem"))
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Erro interno do servidor")
                .responseModel(new ModelRef("Problem"))
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.NOT_ACCEPTABLE.value())
                .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message("Tipo de conteúdo não suportado pelo consumidor")
                .build()

        );
    }

    private List<ResponseMessage> globalDeleteResponseMessages() {

        return Arrays.asList(
            new ResponseMessageBuilder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Recurso não encontrado")
                .responseModel(new ModelRef("Problem"))
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Erro interno do servidor")
                .responseModel(new ModelRef("Problem"))
                .build()

        );

    }


    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("AlgaFood API")
            .description("API do projeto AlgaFood")
            .version("1.0")
            .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}