create table chefe_geral (
                             id bigserial not null primary key,
                             cpf varchar(255),
                             data_nascimento varchar(255),
                             nome varchar(255),
                             status_trabalho varchar(255),
                             telefone varchar(255)
);

create table contador (
                          id bigserial not null primary key,
                          cpf varchar(255),
                          data_nascimento varchar(255),
                          nome varchar(255),
                          status_trabalho varchar(255),
                          telefone varchar(255)
);

create table copiador (
                          id bigserial not null primary key,
                          cpf varchar(255),
                          data_nascimento varchar(255),
                          nome varchar(255),
                          status_trabalho varchar(255),
                          telefone varchar(255)
);

create table gerente_financeiro (
                                    id bigserial not null primary key,
                                    cpf varchar(255),
                                    data_nascimento varchar(255),
                                    nome varchar(255),
                                    status_trabalho varchar(255),
                                    telefone varchar(255)
);

create table gerente_producao (
                                  id bigserial not null primary key,
                                  cpf varchar(255),
                                  data_nascimento varchar(255),
                                  nome varchar(255),
                                  status_trabalho varchar(255),
                                  telefone varchar(255)
);

create table gerente_vendas (
                                id bigserial not null primary key,
                                cpf varchar(255),
                                data_nascimento varchar(255),
                                nome varchar(255),
                                status_trabalho varchar(255),
                                telefone varchar(255)
);

create table pedido (
                        id bigserial not null primary key,
                        data_emissao timestamp(6),
                        data_finalizacao timestamp(6),
                        nome_cliente varchar(255),
                        quantidade float(53) not null,
                        status_pedido varchar(255),
                        troco numeric(38,2),
                        valor_pago numeric(38,2),
                        valor_total_servico numeric(38,2),
                        copiador_id bigint,
                        produto_id bigint,
                        vendedor_id bigint
);

create table produto (
                         id bigserial not null primary key,
                         ativo boolean,
                         codigo varchar(255),
                         nome varchar(255),
                         valor_produto numeric(38,2)
);

create table vendedor (
                          id bigserial not null primary key,
                          cpf varchar(255),
                          data_nascimento varchar(255),
                          nome varchar(255),
                          status_trabalho varchar(255),
                          telefone varchar(255)
);

alter table if exists pedido
    add constraint FKa43tyqhysq1nooi11ou4816g5
    foreign key (copiador_id)
    references copiador;

alter table if exists pedido
    add constraint FK73bw249kqpeedj11lmig00nj4
    foreign key (produto_id)
    references produto;

alter table if exists pedido
    add constraint FKi6y72r3lhf410eb1mqbr41bwv
    foreign key (vendedor_id)
    references vendedor;