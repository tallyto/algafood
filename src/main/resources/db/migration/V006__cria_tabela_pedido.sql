create table pedido
(
    id                   bigint         not null auto_increment,
    subtotal             decimal(19, 2) not null,
    taxa_frete           decimal(19, 2) not null,
    valor_total          decimal(19, 2) not null,

    usuario_cliente_id   bigint         not null,
    forma_pagamento_id   bigint         not null,
    restaurante_id       bigint         not null,

    endereco_cidade_id   bigint,
    endereco_bairro      varchar(60),
    endereco_cep         varchar(9),
    endereco_complemento varchar(60),
    endereco_logradouro  varchar(100),
    endereco_numero      varchar(20),


    status               varchar(10)    not null,
    data_cancelamento    datetime(6),
    data_confirmacao     datetime(6),
    data_criacao         datetime(6)    not null,
    data_entrega         datetime(6),

    primary key (id),
    constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id),
    constraint fk_pedido_usuario_cliente foreign key (usuario_cliente_id) references usuario (id),
    constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id)
) engine = InnoDB;


create table item_pedido
(
    id             bigint         not null auto_increment,
    quantidade     smallint       not null,
    preco_unitario decimal(10, 2) not null,
    preco_total    decimal(10, 2) not null,
    observacao     varchar(100)   null,
    pedido_id      bigint         not null,
    produto_id     bigint         not null,

    primary key (id),
    unique key uk_item_pedido_produto (pedido_id, produto_id),

    constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido (id),
    constraint fk_item_pedido_produto foreign key (produto_id) references produto (id)
) engine = InnoDB;
