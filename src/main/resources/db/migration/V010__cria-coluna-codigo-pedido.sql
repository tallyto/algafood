alter table pedido
    add codigo varchar(50) not null after id;
update pedido
set pedido.codigo = uuid();
alter table pedido
    add constraint uk_pedido_codigo unique (codigo);
