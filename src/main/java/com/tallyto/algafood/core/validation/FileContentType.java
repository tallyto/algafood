package com.tallyto.algafood.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação personalizada para validar o tipo de conteúdo de um arquivo MultipartFile.
 * Usada em conjunto com o validador FileContentTypeValidator.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileContentTypeValidator.class})
public @interface FileContentType {

    /**
     * Mensagem de erro padrão para ser exibida quando a validação falhar.
     *
     * @return A mensagem de erro padrão.
     */
    String message() default "Formato de arquivo inválido";

    /**
     * Grupos de validação aos quais essa restrição pode ser associada.
     *
     * @return Os grupos de validação.
     */
    Class<?>[] groups() default {};

    /**
     * Carga útil personalizada que pode ser anexada à restrição.
     *
     * @return A carga útil personalizada.
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Tipos de conteúdo permitidos para o arquivo.
     *
     * @return Os tipos de conteúdo permitidos.
     */
    String[] allowed();
}
