create table item_pedido (id bigint not null auto_increment, observacao varchar(255), preco_total decimal(19,2) not null, preco_unitario decimal(19,2) not null, quantidade integer not null, pedido_id bigint not null, produto_id bigint not null, primary key (id)) engine=InnoDB;
create table pedido (id bigint not null auto_increment, data_cancelamento datetime(6), data_confirmacao datetime(6), data_criacao datetime(6) not null, data_entrega datetime(6), status integer, subtotal decimal(19,2) not null, taxa_frete decimal(19,2) not null, valor_total decimal(19,2) not null, usuario_client_id bigint not null, forma_pagamento_id bigint not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB;
alter table item_pedido add constraint fk_item_pedido_on_pedido foreign key (pedido_id) references pedido (id);
alter table item_pedido add constraint fk_item_pedido_on_produto foreign key (produto_id) references produto (id);
alter table pedido add constraint fk_pedido_on_usuario foreign key (usuario_client_id) references usuario (id);
alter table pedido add constraint fk_pedido_on_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id);
alter table pedido add constraint fk_pedido_on_restaurante foreign key (restaurante_id) references restaurante (id);
