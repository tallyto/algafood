package com.algaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    FotoRecuperada recuperar(String nomeArquivo);

    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    default String gerarNomeArquivo(String nomeOriginal) {
        return String.format("%s_%s", UUID.randomUUID(), nomeOriginal);
    }

    default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
        this.armazenar(novaFoto);
        if (nomeArquivoAntigo != null) {
            remover(nomeArquivoAntigo);
        }
    }

    @Builder
    @Getter
    class NovaFoto {
        private String nomeArquivo;
        private InputStream inputStream;
        private String contentType;
    }

    @Builder
    @Getter
    class FotoRecuperada {
        private InputStream inputStream;
        private String url;

        public boolean temUrl(){
            return url != null;
        }

        public boolean temInputStrem() {
            return inputStream != null;
        }
    }
}
