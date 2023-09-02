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
	created_at timestamp,
	created_by varchar(60),
	updated_at timestamp,
	updated_by varchar(60)
);
ALTER SEQUENCE endereco_seq
OWNED BY endereco.id;

create sequence residente_endereco_seq;
create table residente_endereco(
	id bigint primary key  not null DEFAULT nextval('residente_endereco_seq'),
	id_pessoa bigint not null,
	id_endereco bigint not null,
	created_at timestamp not null,
    created_by varchar(60) not null,
    updated_at timestamp,
    updated_by varchar(60),
   constraint fk_pessoa
        foreign key (id_pessoa)
            references pessoa(id),
    constraint fk_endereco
        foreign key (id_endereco)
            references endereco(id)
);
ALTER SEQUENCE parentesco_seq
OWNED BY parentesco.id;

create sequence eletrodomestico_seq;
create table eletrodomestico(
	id bigint primary key  not null DEFAULT nextval('eletrodomestico_seq'),
	id_pessoa bigint not null,
	nome varchar(50) not null,
	modelo varchar(50) not null,
	potencia int not null,
    horas_uso_dia int not null,
	created_at timestamp not null,
	created_by varchar(60) not null,
	updated_at timestamp,
	updated_by varchar(60),
    constraint fk_pessoa
        foreign key (id_pessoa)
            references pessoa(id)
);
ALTER SEQUENCE eletrodomestico_seq
OWNED BY eletrodomestico.id;

