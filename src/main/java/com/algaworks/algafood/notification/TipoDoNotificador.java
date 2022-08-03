package com.algaworks.algafood.notification;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // lida em tempo de execucao
@Qualifier
public @interface TipoDoNotificador {
    NivelUrgencia value();
}
