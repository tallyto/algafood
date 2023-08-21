CREATE TABLE restaurante_usuario_responsavel (
                                             restaurante_id BIGINT NOT NULL,
                                             usuario_id BIGINT NOT NULL,
                                             PRIMARY KEY (restaurante_id, usuario_id),
                                             CONSTRAINT uk_restaurante_usuario_responsavel UNIQUE (restaurante_id, usuario_id)
);

alter table restaurante_usuario_responsavel add constraint fk_restaurante_usuario_responsavel_on_restaurante foreign key (restaurante_id) references restaurante (id);
alter table restaurante_usuario_responsavel add constraint fk_restaurante_usuario_responsavel_on_usuario foreign key (usuario_id) references usuario (id);
