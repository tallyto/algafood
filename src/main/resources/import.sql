insert into cozinha (id, nome) values (1,'Tailandesa');
insert into cozinha (id, nome) values (2,'Indiana');
insert into cozinha (id, nome) values (3,'Brasileira');
insert into restaurante (nome, taxa_frete, cozinha_id ) values ("Brabas", 9.0, 1)
insert into restaurante (nome, taxa_frete, cozinha_id) values ("Shinobis", 10.0, 2)
insert into forma_pagamento (descricao) values ("cartao")
insert into forma_pagamento (descricao) values ("dinheiro")
insert into forma_pagamento (descricao) values ("pix")
insert into permissao (nome,descricao) values ("admin", "dono da porra toda")
insert into permissao (nome, descricao) values ("usuario", "só pode visualizar")
insert into permissao (nome, descricao) values ("gerente", "consegue alterar itens da sua loja")
insert into estado (id,nome) values (1,"Roraima")
insert into estado (id,nome) values (2,"Parana")
insert into estado (id,nome) values (3,"São Paulo")
insert into cidade (nome,estado_id) values ("Boa Vista",1)
insert into cidade (nome,estado_id) values ("Curitiba",2)
insert into cidade (nome,estado_id) values ("São Paulo",2)
insert into pessoa (nome, idade) values ("Tállyto", 26)
insert into pessoa (nome, idade) values ("David", 35)