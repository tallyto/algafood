CREATE TABLE cidade
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    nome      VARCHAR(20)           NOT NULL,
    estado_id BIGINT                NOT NULL,
    CONSTRAINT pk_cidade PRIMARY KEY (id)
);

ALTER TABLE cidade
    ADD CONSTRAINT FK_CIDADE_ON_ESTADO FOREIGN KEY (estado_id) REFERENCES estado (id);
