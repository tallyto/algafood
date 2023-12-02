package com.tallyto.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problem")
@JsonInclude(JsonInclude.Include.NON_NULL) // não vai incluir campos nulos no JSON
@Getter
@Builder
public class Problem {
    @ApiModelProperty(example = "400")
    private Integer status;
    @ApiModelProperty(example = "https://algafood.com.br/dados-invalidos")
    private String type;
    @ApiModelProperty(example = "Dados inválidos")
    private String title;
    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String detail;
    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String userMessage;
    @ApiModelProperty(example = "2021-01-01T10:00:00Z")
    private OffsetDateTime timestamp;
    @ApiModelProperty(value = "Lista de objetos com os erros")
    private List<Object> objects;

    @ApiModel(value = "ProblemObject")
    @Getter
    @Builder
    public static class Object {
        @ApiModelProperty(example = "nome")
        private String name;
        @ApiModelProperty(example = "O nome deve ser informado")
        private String userMessage;
    }
}
