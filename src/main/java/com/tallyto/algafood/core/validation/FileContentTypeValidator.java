package com.tallyto.algafood.core.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * Validador personalizado para verificar se o tipo de conteúdo de um arquivo MultipartFile
 * é permitido conforme as restrições definidas na anotação @FileContentType.
 */
public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {
    private List<String> allowedContentTypes;

    /**
     * Inicializa o validador com os tipos de conteúdo permitidos da anotação @FileContentType.
     *
     * @param constraint Instância da anotação @FileContentType que contém os tipos de conteúdo permitidos.
     */
    @Override
    public void initialize(FileContentType constraint) {
        this.allowedContentTypes = Arrays.asList(constraint.allowed());
    }

    /**
     * Verifica se o arquivo MultipartFile possui um tipo de conteúdo permitido.
     *
     * @param multipartFile O arquivo MultipartFile a ser validado.
     * @param context       O contexto de validação.
     * @return true se o arquivo é nulo ou se seu tipo de conteúdo é permitido; caso contrário, false.
     */
    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        return multipartFile == null
            || this.allowedContentTypes.contains(multipartFile.getContentType());
    }
}
