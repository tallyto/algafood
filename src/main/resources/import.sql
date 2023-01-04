insert into estado (id, nome) values (1, 'Roraima');
insert into estado (id, nome) values (2, 'Parana');
insert into estado (id, nome) values (3, 'São Paulo');
insert into cidade (nome, estado_id) values ('Boa Vista', 1);
insert into cidade (nome, estado_id) values ('Curitiba', 2);
insert into cidade (nome, estado_id) values ('São Paulo', 2);
insert into pessoa (nome, idade) values ('Tállyto', 26);
insert into pessoa (nome, idade) values ('David', 35);
insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Brasileira');
insert into cozinha (id, nome) values (4 , 'Macuxi')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1,'Restaurante Dona Maria', 9.0, 1, utc_timestamp,utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao)values (2,'Lá Casa de Pastel', 10.0, 2,utc_timestamp,utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao)values (3,'Chico do Carneiro', 0, 2,utc_timestamp,utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao)values (4,'Bobs', 0, 1,utc_timestamp,utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id,endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao) values (5,'La Casa do Pastel', 0, 1,1, '00000000', 'Rua do Pastel', 1, 'Casa', 'Centro',utc_timestamp,utc_timestamp);
insert into forma_pagamento (id,descricao) values (1,'cartao');
insert into forma_pagamento (id,descricao) values (2,'dinheiro');
insert into forma_pagamento (id,descricao) values (3,'pix');
insert into permissao (nome, descricao) values ('admin', 'dono da porra toda');
insert into permissao (nome, descricao) values ('usuario', 'só pode visualizar');
insert into permissao (nome, descricao) values ('gerente', 'consegue alterar itens da sua loja');
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (4, 1);
insert into produto (id, ativo, descricao, nome, preco, restaurante_id)  values (1, true, 'carne de sol na chapa, baião de dois, vatapa e macaxeira frita', 'Carne de Sol na Chapa', 12, 1)
insert into produto (id, ativo, descricao, nome, preco, restaurante_id) values (2, true, 'tambaqui assado, acompanha arroz e vinagrete', 'tambaqui assado', 24, 1)
insert into produto (id, ativo, descricao, nome, preco, restaurante_id) values (3, true, 'tambaqui assado, acompanha arroz e vinagrete', 'tambaqui assado', 24, 2)
insert into produto (id, ativo, descricao, nome, preco, restaurante_id) values (4, true, 'tambaqui assado, acompanha arroz e vinagrete', 'tambaqui assado', 24, 3)
insert into usuario (id, nome, email, senha, data_cadastro) values (1,'fulano','teste@teste.com', '123456', utc_timestamp);
insert into grupo (id, nome) values (1, 'admin');
insert into grupo (id, nome) values (2, 'usuario');
insert into grupo (id, nome) values (3, 'gerente');
insert into usuario_grupo (usuario_id, grupo_id) values (1, 1);
insert into usuario_grupo (usuario_id, grupo_id) values (1, 2);

