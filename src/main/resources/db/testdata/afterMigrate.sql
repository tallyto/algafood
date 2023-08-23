set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;
delete from produto;
delete from pedido;
delete from item_pedido;
delete from restaurante_usuario_responsavel;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table grupo_permissao auto_increment = 1;
alter table permissao auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table restaurante_forma_pagamento auto_increment = 1;
alter table usuario auto_increment = 1;
alter table usuario_grupo auto_increment = 1;
alter table produto auto_increment = 1;
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;
alter table restaurante_usuario_responsavel auto_increment = 1;


insert into estado (id, nome) values (1, 'Roraima');
insert into estado (id, nome) values (2, 'Parana');
insert into estado (id, nome) values (3, 'São Paulo');
insert into cidade (id,nome, estado_id) values (1,'Boa Vista', 1);
insert into cidade (id,nome, estado_id) values (2,'Curitiba', 2);
insert into cidade (id,nome, estado_id) values (3,'São Paulo', 3);
insert into pessoa (nome, idade) values ('Tállyto', 26);
insert into pessoa (nome, idade) values ('David', 35);
insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Brasileira');
insert into cozinha (id, nome) values (4 , 'Macuxi');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1,'Restaurante Dona Maria', 9.0, 1, utc_timestamp,utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao)values (2,'Lá Casa de Pastel', 10.0, 2,utc_timestamp,utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao)values (3,'Chico do Carneiro', 0, 2,utc_timestamp,utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao)values (4,'Bobs', 0, 1,utc_timestamp,utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id,endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao) values (5,'La Casa do Pastel', 0, 1,1, '00000000', 'Rua do Pastel', 1, 'Casa', 'Centro',utc_timestamp,utc_timestamp);
insert into forma_pagamento (id,descricao) values (1,'cartao');
insert into forma_pagamento (id,descricao) values (2,'dinheiro');
insert into forma_pagamento (id,descricao) values (3,'pix');
insert into permissao (id, descricao, nome) values (1, 'Pode editar uma cozinha', 'EDITAR_COZINHA');
insert into permissao (id, descricao, nome) values (2, 'Pode visualizar estatísticas', 'VISUALIZAR_ESTATISTICAS');
insert into permissao (id, descricao, nome) values (3, 'Pode criar posts', 'CRIAR_POST');
insert into permissao (id, descricao, nome) values (4, 'Pode editar um restaurante', 'EDITAR_RESTAURANTE');
insert into permissao (id, descricao, nome) values (5, 'Pode visualizar relatórios', 'VISUALIZAR_RELATORIOS');
insert into permissao (id, descricao, nome) values (6, 'Pode publicar posts', 'PUBLICAR_POSTS');
insert into permissao (id, descricao, nome) values (7, 'Pode gerenciar usuários', 'GERENCIAR_USUARIOS');
insert into permissao (id, descricao, nome) values (8, 'Pode aprovar comentários', 'APROVAR_COMENTARIOS');
insert into permissao (id, descricao, nome) values (9, 'Pode excluir produtos', 'EXCLUIR_PRODUTOS');
insert into permissao (id, descricao, nome) values (10, 'Pode acessar configurações', 'ACESSAR_CONFIGURACOES');
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (4, 1);
insert into produto (id, ativo, descricao, nome, preco, restaurante_id)  values (1, true, 'carne de sol na chapa, baião de dois, vatapa e macaxeira frita', 'Carne de Sol na Chapa', 12, 1);
insert into produto (id, ativo, descricao, nome, preco, restaurante_id) values (2, true, 'tambaqui assado, acompanha arroz e vinagrete', 'tambaqui assado', 24, 1);
-- Produto 3
INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id)
VALUES (true, 'frango grelhado, batata assada e salada', 'Frango Grelhado', 15, 1);

-- Produto 4
INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id)
VALUES (true, 'feijoada completa com arroz, couve, farofa e laranja', 'Feijoada Completa', 18, 1);

-- Produto 5
INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id)
VALUES (true, 'salada de alface, tomate, cenoura e molho especial', 'Salada Mista', 8, 1);

-- Produto 6
INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id)
VALUES (true, 'sorvete de chocolate com calda de morango', 'Sorvete de Chocolate', 6, 1);

-- Produto 7
INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id)
VALUES (true, 'arroz doce com canela e leite condensado', 'Arroz Doce', 4.5, 1);

insert into usuario (id, nome, email, senha, data_cadastro) values (1,'fulano','teste@teste.com', '123456', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (2, 'beltrano', 'beltrano@teste.com', 'abcdef', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (3, 'ciclano', 'ciclano@teste.com', 'ghijkl', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (4, 'fulana', 'fulana@teste.com', 'mnopqr', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (5, 'beltrana', 'beltrana@teste.com', 'stuvwx', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (6, 'ciclana', 'ciclana@teste.com', 'yzabcd', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (7, 'fulano2', 'fulano2@teste.com', '123456', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (8, 'beltrano2', 'beltrano2@teste.com', 'abcdef', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (9, 'ciclano2', 'ciclano2@teste.com', 'ghijkl', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (10, 'fulana2', 'fulana2@teste.com', 'mnopqr', utc_timestamp);
insert into grupo (id, nome) values (1, 'admin');
insert into grupo (id, nome) values (2, 'usuario');
insert into grupo (id, nome) values (3, 'gerente');
insert into grupo (id, nome) values (4, 'atendente');
insert into grupo (id, nome) values (5, 'financeiro');
insert into grupo (id, nome) values (6, 'moderador');
insert into grupo (id, nome) values (7, 'suporte');
insert into grupo (id, nome) values (8, 'analista');
insert into usuario_grupo (usuario_id, grupo_id) values (1, 1);
insert into usuario_grupo (usuario_id, grupo_id) values (2, 2);
insert into usuario_grupo (usuario_id, grupo_id) values (3, 3);
insert into usuario_grupo (usuario_id, grupo_id) values (4, 3);
insert into usuario_grupo (usuario_id, grupo_id) values (5, 1);
insert into usuario_grupo (usuario_id, grupo_id) values (6, 3);
insert into grupo_permissao (grupo_id, permissao_id) values (1, 1);
insert into grupo_permissao (grupo_id, permissao_id) values (2, 1);
insert into grupo_permissao (grupo_id, permissao_id) values (3, 1);
insert into grupo_permissao (grupo_id, permissao_id) values (1, 2);
insert into grupo_permissao (grupo_id, permissao_id) values (2, 3);
insert into grupo_permissao (grupo_id, permissao_id) values (3, 3);
insert into grupo_permissao (grupo_id, permissao_id) values (2, 2);
insert into grupo_permissao (grupo_id, permissao_id) values (3, 3);
insert into restaurante_usuario_responsavel(restaurante_id, usuario_id) values (1,1);
insert into restaurante_usuario_responsavel(restaurante_id, usuario_id) values (2,1);
insert into restaurante_usuario_responsavel(restaurante_id, usuario_id) values (3,1);
insert into restaurante_usuario_responsavel(restaurante_id, usuario_id) values (1,2);
insert into restaurante_usuario_responsavel(restaurante_id, usuario_id) values (2,2);
insert into restaurante_usuario_responsavel(restaurante_id, usuario_id) values (3,2);




# Inserir pedidos
-- Inserção Básica
INSERT INTO pedido (data_criacao, subtotal, taxa_frete, valor_total, usuario_client_id, forma_pagamento_id, restaurante_id)
VALUES ('2023-08-22 10:00:00', 50.00, 5.00, 55.00, 1, 1, 1);

-- Inserção com Status e Data de Entrega
INSERT INTO pedido (data_criacao, data_entrega, status, subtotal, taxa_frete, valor_total, usuario_client_id, forma_pagamento_id, restaurante_id)
VALUES ('2023-08-22 11:30:00', '2023-08-23 18:00:00', 0, 75.00, 8.00, 83.00, 2, 2, 2);

-- Inserção com Cancelamento
INSERT INTO pedido (data_criacao, data_cancelamento, status, subtotal, taxa_frete, valor_total, usuario_client_id, forma_pagamento_id, restaurante_id)
VALUES ('2023-08-22 15:45:00', '2023-08-22 16:30:00', 0, 30.00, 3.50, 33.50, 3, 1, 3);

-- Inserção com Confirmação
INSERT INTO pedido (data_criacao, data_confirmacao, status, subtotal, taxa_frete, valor_total, usuario_client_id, forma_pagamento_id, restaurante_id)
VALUES ('2023-08-22 09:15:00', '2023-08-22 10:00:00', 0, 60.00, 6.00, 66.00, 4, 3, 4);

-- Inserção Completa
INSERT INTO pedido (data_criacao, data_confirmacao, data_entrega, data_cancelamento, status, subtotal, taxa_frete, valor_total, usuario_client_id, forma_pagamento_id, restaurante_id)
VALUES ('2023-08-22 14:20:00', '2023-08-22 15:00:00', '2023-08-23 12:30:00', NULL, 0, 90.00, 10.00, 100.00, 5, 3, 4);

# Inserir items de pedido
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
