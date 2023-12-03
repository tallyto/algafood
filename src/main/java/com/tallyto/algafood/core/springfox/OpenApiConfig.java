package com.tallyto.algafood.core.springfox;

import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tallyto.algafood.api.exceptionhandler.Problem;
import com.tallyto.algafood.api.v1.model.*;
import com.tallyto.algafood.api.v1.openapi.model.*;
import com.tallyto.algafood.api.v2.model.CidadeModelV2;
import com.tallyto.algafood.api.v2.model.CozinhaModelV2;
import com.tallyto.algafood.api.v2.openapi.model.CidadesModelV2OpenApi;
import com.tallyto.algafood.api.v2.openapi.model.CozinhasModelV2OpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
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
    public Docket apiDocketV1() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("V1")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.tallyto.algafood.api"))
            .paths(PathSelectors.ant("/v1/**"))
            .build()
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
            .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
            .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
            .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
            .additionalModels(typeResolver.resolve(Problem.class))
            .ignoredParameterTypes(ServletWebRequest.class,
                URL.class, URI.class, URLStreamHandler.class, Resource.class,
                File.class, InputStream.class)
            .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
            .directModelSubstitute(Links.class, LinksModelOpenApi.class)
            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(PagedModel.class, CozinhaModel.class),
                CozinhasModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(Page.class, PedidoResumoModel.class),
                PedidosResumoModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, CidadeModel.class),
                CidadesModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, EstadoModel.class),
                EstadosModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, FormaPagamentoModel.class),
                FormasPagamentoModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, GrupoModel.class),
                GruposModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, PermissaoModel.class),
                PermissoesModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, ProdutoModel.class),
                ProdutosModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, RestauranteBasicoModel.class),
                RestaurantesBasicoModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, UsuarioModel.class),
                UsuariosModelOpenApi.class))
            .apiInfo(apiInfoV1())
            .tags(new Tag("Cidades", "Gerencia as cidades"),
                new Tag("Grupos", "Gerencia os grupos de usuários"),
                new Tag("Cozinhas", "Gerencia as cozinhas"),
                new Tag("Formas de pagamento", "Gerencia as formas de pagamento"),
                new Tag("Pedidos", "Gerencia os pedidos"),
                new Tag("Restaurantes", "Gerencia os restaurantes"),
                new Tag("Estados", "Gerencia os estados"),
                new Tag("Produtos", "Gerencia os produtos de restaurantes"),
                new Tag("Usuários", "Gerencia os usuários"),
                new Tag("Estatísticas", "Estatísticas da AlgaFood"),
                new Tag("Permissões", "Gerencia as permissões"));
    }

    @Bean
    public Docket apiDocketV2() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("V2")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.tallyto.algafood.api"))
            .paths(PathSelectors.ant("/v2/**"))
            .build()
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
            .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
            .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
            .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
            .additionalModels(typeResolver.resolve(Problem.class))
            .ignoredParameterTypes(ServletWebRequest.class,
                URL.class, URI.class, URLStreamHandler.class, Resource.class,
                File.class, InputStream.class)
            .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
            .directModelSubstitute(Links.class, LinksModelOpenApi.class)
            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(PagedModel.class, CozinhaModelV2.class),
                CozinhasModelV2OpenApi.class))

            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, CidadeModelV2.class),
                CidadesModelV2OpenApi.class))
            .apiInfo(apiInfoV2());

    }

    @Bean
    public JacksonModuleRegistrar springFoxJacksonConfig() {
        return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
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


    public ApiInfo apiInfoV1() {
        return new ApiInfoBuilder()
            .title("AlgaFood API (Depreciada)")
            .description("API aberta para clientes e restaurantes.<br>" +
                "<strong>Essa versão da API está depreciada e deixará de existir a partir de 01/12/2024." +
                "Use a versão mais atual da API.</strong>")
            .version("1.0")
            .build();
    }

    public ApiInfo apiInfoV2() {
        return new ApiInfoBuilder()
            .title("AlgaFood API")
            .description("API do projeto AlgaFood")
            .version("2.0")
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
