create sequence pessoa_seq;
create table pessoa(
	id bigint primary key  not null DEFAULT nextval('pessoa_seq'),
	nome varchar(100) not null,
	data_nascimento date not null,
	pessoa_genero varchar(15) not null,
    created_at timestamp not null,
    created_by varchar(60) not null,
    updated_at timestamp,
    updated_by varchar(60)
);
ALTER SEQUENCE pessoa_seq
OWNED BY pessoa.id;

create sequence parentesco_seq;
create table parentesco(
	id bigint primary key  not null DEFAULT nextval('parentesco_seq'),
	id_pessoa bigint not null,
	id_parente bigint not null,
	parentesco varchar(50) not null,
	created_at timestamp not null,
    created_by varchar(60) not null,
    updated_at timestamp,
    updated_by varchar(60),
       constraint fk_pessoa
        foreign key (id_pessoa)
            references pessoa(id)
);
ALTER SEQUENCE parentesco_seq
OWNED BY parentesco.id;

create sequence endereco_seq;
create table endereco(
	id bigint primary key  not null DEFAULT nextval('endereco_seq'),
	rua varchar(120),
	numero int,
	cidade varchar(100),
	bairro varchar(100),
	estado varchar(100),
	id_pessoa bigint not null,
	created_at timestamp,
	created_by varchar(60),
	updated_at timestamp,
	updated_by varchar(60),
    constraint fk_pessoa
        foreign key (id_pessoa)
            references pessoa(id)
);
ALTER SEQUENCE endereco_seq
OWNED BY endereco.id;

create sequence residentes_endereco_seq;
create table residentes_endereco(
	id bigint primary key  not null DEFAULT nextval('residentes_endereco_seq'),
	id_pessoa bigint not null,
	id_endereco bigint not null,
	created_at timestamp not null,
    created_by varchar(60) not null,
    updated_at timestamp,
    updated_by varchar(60),
       constraint fk_pessoa
        foreign key (id_pessoa)
            references pessoa(id)
);
ALTER SEQUENCE parentesco_seq
OWNED BY parentesco.id;

create sequence eletrodomestico_seq;
create table eletrodomestico(
	id bigint primary key  not null DEFAULT nextval('eletrodomestico_seq'),
	id_pessoa bigint not null,
	nome varchar(50),
	modelo varchar(50),
	potencia int,
	created_at timestamp,
	created_by varchar(60),
	updated_at timestamp,
	updated_by varchar(60),
    constraint fk_pessoa
        foreign key (id_pessoa)
            references pessoa(id)
);
ALTER SEQUENCE eletrodomestico_seq
OWNED BY eletrodomestico.id;

