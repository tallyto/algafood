set foreign_key_checks = 0;

delete
from cidade;
delete
from cozinha;
delete
from estado;
delete
from forma_pagamento;
delete
from grupo;
delete
from grupo_permissao;
delete
from permissao;
delete
from restaurante;
delete
from restaurante_forma_pagamento;
delete
from usuario;
delete
from usuario_grupo;
delete
from produto;
delete
from pedido;
delete
from item_pedido;
delete
from restaurante_usuario_responsavel;
delete
from foto_produto;

set foreign_key_checks = 1;

alter table cidade
    auto_increment = 1;
alter table cozinha
    auto_increment = 1;
alter table estado
    auto_increment = 1;
alter table forma_pagamento
    auto_increment = 1;
alter table grupo
    auto_increment = 1;
alter table grupo_permissao
    auto_increment = 1;
alter table permissao
    auto_increment = 1;
alter table restaurante
    auto_increment = 1;
alter table restaurante_forma_pagamento
    auto_increment = 1;
alter table usuario
    auto_increment = 1;
alter table usuario_grupo
    auto_increment = 1;
alter table produto
    auto_increment = 1;
alter table pedido
    auto_increment = 1;
alter table item_pedido
    auto_increment = 1;
alter table restaurante_usuario_responsavel
    auto_increment = 1;

insert into estado (id, nome)
values (1, 'Roraima');
insert into estado (id, nome)
values (2, 'Parana');
insert into estado (id, nome)
values (3, 'São Paulo');
insert into cidade (id, nome, estado_id)
values (1, 'Boa Vista', 1);
insert into cidade (id, nome, estado_id)
values (2, 'Curitiba', 2);
insert into cidade (id, nome, estado_id)
values (3, 'São Paulo', 3);

insert into cozinha (id, nome)
values (1, 'Tailandesa');
insert into cozinha (id, nome)
values (2, 'Indiana');
insert into cozinha (id, nome)
values (3, 'Brasileira');
insert into cozinha (id, nome)
values (4, 'Macuxi');

-- Inserir o endereço para todos os restaurantes
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero,
                         endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao)
values (1, 'Restaurante Dona Maria', 9.0, 1, 1, '00000000', 'Rua Principal', 123, 'Sala 101', 'Centro', utc_timestamp, utc_timestamp);

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero,
                         endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao)
values (2, 'Lá Casa de Pastel', 10.0, 2, 2, '11111111', 'Avenida da Praça', 456, NULL, 'Bairro Alegre', utc_timestamp, utc_timestamp);

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero,
                         endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao)
values (3, 'Chico do Carneiro', 0, 2, 2, '22222222', 'Rua da Serra', 789, 'Casa 3', 'Montanha', utc_timestamp, utc_timestamp);

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero,
                         endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao)
values (4, 'Bobs', 0, 1, 1, '33333333', 'Avenida da Liberdade', 321, NULL, 'Centro', utc_timestamp, utc_timestamp);

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero,
                         endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao)
values (5, 'Restaurante Dona Chica', 0, 1, 1, '44444444', 'Rua do Pastel', 1, 'Casa', 'Centro', utc_timestamp, utc_timestamp);

insert into forma_pagamento (id, descricao, data_atualizacao)
values (1, 'cartao', utc_timestamp);
insert into forma_pagamento (id, descricao, data_atualizacao)
values (2, 'dinheiro', utc_timestamp);
insert into forma_pagamento (id, descricao, data_atualizacao)
values (3, 'pix', utc_timestamp);

insert into grupo (id, nome)
values (1, 'Gerente'),
       (2, 'Vendedor'),
       (3, 'Secretária'),
       (4, 'Cadastrador');

insert into permissao (id, nome, descricao)
values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao)
values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissao (id, nome, descricao)
values (3, 'CONSULTAR_FORMAS_PAGAMENTO', 'Permite consultar formas de pagamento');
insert into permissao (id, nome, descricao)
values (4, 'EDITAR_FORMAS_PAGAMENTO', 'Permite criar ou editar formas de pagamento');
insert into permissao (id, nome, descricao)
values (5, 'CONSULTAR_CIDADES', 'Permite consultar cidades');
insert into permissao (id, nome, descricao)
values (6, 'EDITAR_CIDADES', 'Permite criar ou editar cidades');
insert into permissao (id, nome, descricao)
values (7, 'CONSULTAR_ESTADOS', 'Permite consultar estados');
insert into permissao (id, nome, descricao)
values (8, 'EDITAR_ESTADOS', 'Permite criar ou editar estados');
insert into permissao (id, nome, descricao)
values (9, 'CONSULTAR_USUARIOS', 'Permite consultar usuários');
insert into permissao (id, nome, descricao)
values (10, 'EDITAR_USUARIOS', 'Permite criar ou editar usuários');
insert into permissao (id, nome, descricao)
values (11, 'CONSULTAR_RESTAURANTES', 'Permite consultar restaurantes');
insert into permissao (id, nome, descricao)
values (12, 'EDITAR_RESTAURANTES', 'Permite criar, editar ou gerenciar restaurantes');
insert into permissao (id, nome, descricao)
values (13, 'CONSULTAR_PRODUTOS', 'Permite consultar produtos');
insert into permissao (id, nome, descricao)
values (14, 'EDITAR_PRODUTOS', 'Permite criar ou editar produtos');
insert into permissao (id, nome, descricao)
values (15, 'CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
insert into permissao (id, nome, descricao)
values (16, 'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');
insert into permissao (id, nome, descricao)
values (17, 'GERAR_RELATORIOS', 'Permite gerar relatórios');

--  Adiciona todas as permissoes no grupo do gerente
insert into grupo_permissao (grupo_id, permissao_id)
select 1, id
from permissao;

--  Adiciona permissoes no grupo do vendedor
insert into grupo_permissao (grupo_id, permissao_id)
select 2, id
from permissao
where nome like 'CONSULTAR_%';

insert into grupo_permissao (grupo_id, permissao_id)
values (2, 14);

--  Adiciona permissoes no grupo do auxiliar
insert into grupo_permissao (grupo_id, permissao_id)
select 3, id
from permissao
where nome like 'CONSULTAR_%';

--  Adiciona permissoes no grupo cadastrador
insert into grupo_permissao (grupo_id, permissao_id)
select 4, id
from permissao
where nome like '%_RESTAURANTES'
   or nome like '%_PRODUTOS';

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
values (1, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
values (1, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
values (2, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
values (2, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
values (2, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
values (3, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
values (3, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
values (4, 1);
insert into produto (id, ativo, descricao, nome, preco, restaurante_id)
values (1, true, 'carne de sol na chapa, baião de dois, vatapa e macaxeira frita', 'Carne de Sol na Chapa', 12, 1);
insert into produto (id, ativo, descricao, nome, preco, restaurante_id)
values (2, true, 'tambaqui assado, acompanha arroz e vinagrete', 'tambaqui assado', 24, 1);
-- Produto 3
INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id)
VALUES (true, 'frango grelhado, batata assada e salada', 'Frango Grelhado', 15, 1);

-- Produto 4
INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id)
VALUES (true, 'feijoada completa com arroz, couve, farofa e laranja', 'Feijoada Completa', 18, 1);

-- Produto 5
INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id)
VALUES (false, 'salada de alface, tomate, cenoura e molho especial', 'Salada Mista', 8, 1);

-- Produto 6
INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id)
VALUES (false, 'sorvete de chocolate com calda de morango', 'Sorvete de Chocolate', 6, 1);

-- Produto 7
INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id)
VALUES (false, 'arroz doce com canela e leite condensado', 'Arroz Doce', 4.5, 1);

insert into usuario (id, nome, email, senha, data_cadastro)
values (1, 'fulano', 'rodrigues.tallyto@gmail.com', '$2a$12$N4vn6FFL/7Upq7NBAVBDAedVlQYLuiI8nLB5ta365Fb.QccbzZu0e', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro)
values (2, 'beltrano', 'beltrano@teste.com', '$2a$12$N4vn6FFL/7Upq7NBAVBDAedVlQYLuiI8nLB5ta365Fb.QccbzZu0e', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro)
values (3, 'ciclano', 'ciclano@teste.com', '$2a$12$N4vn6FFL/7Upq7NBAVBDAedVlQYLuiI8nLB5ta365Fb.QccbzZu0e', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro)
values (4, 'fulana', 'fulana@teste.com', '$2a$12$N4vn6FFL/7Upq7NBAVBDAedVlQYLuiI8nLB5ta365Fb.QccbzZu0e', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro)
values (5, 'beltrana', 'beltrana@teste.com', '$2a$12$N4vn6FFL/7Upq7NBAVBDAedVlQYLuiI8nLB5ta365Fb.QccbzZu0e', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro)
values (6, 'ciclana', 'ciclana@teste.com', '$2a$12$N4vn6FFL/7Upq7NBAVBDAedVlQYLuiI8nLB5ta365Fb.QccbzZu0e', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro)
values (7, 'fulano2', 'fulano2@teste.com', '$2a$12$N4vn6FFL/7Upq7NBAVBDAedVlQYLuiI8nLB5ta365Fb.QccbzZu0e', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro)
values (8, 'beltrano2', 'beltrano2@teste.com', '$2a$12$N4vn6FFL/7Upq7NBAVBDAedVlQYLuiI8nLB5ta365Fb.QccbzZu0e', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro)
values (9, 'ciclano2', 'ciclano2@teste.com', '$2a$12$N4vn6FFL/7Upq7NBAVBDAedVlQYLuiI8nLB5ta365Fb.QccbzZu0e', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro)
values (10, 'fulana2', 'fulana2@teste.com', '$2a$12$N4vn6FFL/7Upq7NBAVBDAedVlQYLuiI8nLB5ta365Fb.QccbzZu0e', utc_timestamp);

insert into usuario_grupo (usuario_id, grupo_id)
values (1, 1),
       (1, 2),
       (2, 2),
       (3, 4),
       (4, 4);

insert into restaurante_usuario_responsavel(restaurante_id, usuario_id)
values (1, 1);
insert into restaurante_usuario_responsavel(restaurante_id, usuario_id)
values (2, 1);
insert into restaurante_usuario_responsavel(restaurante_id, usuario_id)
values (3, 1);
insert into restaurante_usuario_responsavel(restaurante_id, usuario_id)
values (1, 2);
insert into restaurante_usuario_responsavel(restaurante_id, usuario_id)
values (2, 2);
insert into restaurante_usuario_responsavel(restaurante_id, usuario_id)
values (3, 2);

-- Inserção Básica
INSERT INTO pedido (codigo, data_criacao, subtotal, taxa_frete, valor_total, usuario_cliente_id, forma_pagamento_id,
                    restaurante_id, status, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro,
                    endereco_numero)
VALUES ('77103e21-49ef-11ee-a210-0242ac120003', '2023-08-22 10:00:00', 50.00, 5.00, 55.00, 1, 1, 1, 'CRIADO', 1, 'Bairro 1', '12345-678',
        'Complemento 1', 'Rua 1', '123');

-- Inserção com Status e Data de Entrega
INSERT INTO pedido (codigo, data_criacao, data_entrega, status, subtotal, taxa_frete, valor_total, usuario_cliente_id,
                    forma_pagamento_id, restaurante_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_complemento,
                    endereco_logradouro, endereco_numero)
VALUES ('1e0bcff5-7081-11ee-82b5-0242ac120002', '2023-08-03 11:30:00', '2023-08-23 18:00:00', 'CRIADO', 75.00, 8.00, 83.00, 2, 2, 2, 2,
        'Bairro 2', '54321-876', 'Complemento 2', 'Rua 2', '456');

-- Inserção com Cancelamento
INSERT INTO pedido (codigo, data_criacao, data_cancelamento, status, subtotal, taxa_frete, valor_total,
                    usuario_cliente_id, forma_pagamento_id, restaurante_id, endereco_cidade_id, endereco_bairro, endereco_cep,
                    endereco_complemento, endereco_logradouro, endereco_numero)
VALUES ('1e0bfb78-7081-11ee-82b5-0242ac120002', '2023-08-12 15:45:00', '2023-08-22 16:30:00', 'CRIADO', 30.00, 3.50, 33.50, 3, 1, 3, 3,
        'Bairro 3', '98765-432', 'Complemento 3', 'Rua 3', '789');

-- Inserção com Confirmação
INSERT INTO pedido (codigo, data_criacao, data_confirmacao, status, subtotal, taxa_frete, valor_total,
                    usuario_cliente_id, forma_pagamento_id, restaurante_id, endereco_cidade_id, endereco_bairro, endereco_cep,
                    endereco_complemento, endereco_logradouro, endereco_numero)
VALUES ('1e0c2538-7081-11ee-82b5-0242ac120002', '2023-08-26 09:15:00', '2023-08-22 10:00:00', 'CRIADO', 60.00, 6.00, 66.00, 4, 3, 4, 2,
        'Bairro 4', '54321-987', 'Complemento 4', 'Rua 4', '987');

-- Inserção Completa
INSERT INTO pedido (codigo, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status, subtotal,
                    taxa_frete, valor_total, usuario_cliente_id, forma_pagamento_id, restaurante_id, endereco_cidade_id, endereco_bairro,
                    endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero)
VALUES ('1e0c5337-7081-11ee-82b5-0242ac120002', '2023-08-18 14:20:00', '2023-08-22 15:00:00', '2023-08-23 12:30:00', NULL, 'CRIADO', 90.00,
        10.00,
        100.00, 5, 3, 4, 3, 'Bairro 5', '12345-789', 'Complemento 5', 'Rua 5', '555');

-- Pedido 1
INSERT INTO item_pedido (observacao, preco_total, preco_unitario, quantidade, pedido_id, produto_id)
VALUES ('Sem cebola', 12.50, 2.50, 5, 1, 1);

-- Pedido 2
INSERT INTO item_pedido (observacao, preco_total, preco_unitario, quantidade, pedido_id, produto_id)
VALUES ('Molho extra', 9.75, 3.25, 3, 2, 2);

-- Pedido 3
INSERT INTO item_pedido (observacao, preco_total, preco_unitario, quantidade, pedido_id, produto_id)
VALUES ('Bem passado', 18.00, 6.00, 2, 3, 3);

-- Pedido 4
INSERT INTO item_pedido (observacao, preco_total, preco_unitario, quantidade, pedido_id, produto_id)
VALUES (NULL, 6.40, 1.60, 4, 4, 4);

-- Pedido 5
INSERT INTO item_pedido (observacao, preco_total, preco_unitario, quantidade, pedido_id, produto_id)
VALUES ('Adicionar queijo', 7.20, 2.40, 3, 5, 5);
