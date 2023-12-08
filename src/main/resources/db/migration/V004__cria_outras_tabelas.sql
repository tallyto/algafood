create table forma_pagamento
(
    id        bigint      not null auto_increment,
    descricao varchar(60) not null,
    primary key (id)
);
create table grupo
(
    id   bigint       not null auto_increment,
    nome varchar(255) not null,
    primary key (id)
);
create table grupo_permissao
(
    grupo_id     bigint not null,
    permissao_id bigint not null,
    primary key (grupo_id, permissao_id)
);
create table permissao
(
    id        bigint       not null auto_increment,
    descricao varchar(60)  not null,
    nome      varchar(100) not null,
    primary key (id)
);

create table produto
(
    id             bigint         not null auto_increment,
    ativo          bit            not null,
    descricao      text           not null,
    nome           varchar(80)    not null,
    preco          decimal(19, 2) not null,
    restaurante_id bigint,
    primary key (id)
);
create table restaurante
(
    id                   bigint         not null auto_increment,
    data_atualizacao     datetime       not null,
    data_cadastro        datetime       not null,

    nome                 varchar(80)    not null,
    taxa_frete           decimal(19, 2) not null,
    endereco_bairro      varchar(60),
    endereco_cep         varchar(9),
    endereco_complemento varchar(60),
    endereco_logradouro  varchar(100),
    endereco_numero      varchar(20),
    cozinha_id           bigint         not null,
    endereco_cidade_id   bigint,
    primary key (id)
);
CREATE TABLE restaurante_forma_pagamento
(
    restaurante_id     BIGINT NOT NULL,
    forma_pagamento_id BIGINT NOT NULL,
    PRIMARY KEY (restaurante_id, forma_pagamento_id),
    CONSTRAINT uk_restaurante_forma_pagamento UNIQUE (restaurante_id, forma_pagamento_id)
);

create table usuario
(
    id            bigint       not null auto_increment,
    data_cadastro datetime     not null,
    email         varchar(100) not null,
    nome          varchar(80)  not null,
    senha         varchar(100) not null,
    primary key (id)
);
create table usuario_grupo
(
    usuario_id bigint not null,
    grupo_id   bigint not null,
    primary key (usuario_id, grupo_id)
);


alter table grupo_permissao
    add constraint fk_grupo_pemissao_on_permissao foreign key (permissao_id) references permissao (id);
alter table grupo_permissao
    add constraint fk_grupo_permissao_on_grupo foreign key (grupo_id) references grupo (id);
alter table produto
    add constraint fk_produto_on_restaurante foreign key (restaurante_id) references restaurante (id);
alter table restaurante
    add constraint fk_restaurante_on_cozinha foreign key (cozinha_id) references cozinha (id);
alter table restaurante
    add constraint fk_restaurante_on_cidade foreign key (endereco_cidade_id) references cidade (id);
alter table restaurante_forma_pagamento
    add constraint fk_restaurante_forma_pagamento_on_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id);
alter table restaurante_forma_pagamento
    add constraint fk_restaurante_forma_pagamento_on_restaurante foreign key (restaurante_id) references restaurante (id);
alter table usuario_grupo
    add constraint fk_usuario_grupo_on_grupo foreign key (grupo_id) references grupo (id);
alter table usuario_grupo
    add constraint fk_usuario_grupo_on_usuario foreign key (usuario_id) references usuario (id);
