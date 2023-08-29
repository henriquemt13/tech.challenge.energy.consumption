create sequence energy_consumption.endereco_seq;
create table energy_consumption.endereco(
	id bigint primary key  not null DEFAULT nextval('energy_consumption.endereco_seq'),
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
ALTER SEQUENCE energy_consumption.endereco_seq
OWNED BY energy_consumption.endereco.id;

create sequence energy_consumption.eletrodomestico_seq;
create table energy_consumption.eletrodomestico(
	id bigint primary key  not null DEFAULT nextval('energy_consumption.eletrodomestico_seq'),
	id_endereco bigint not null,
	nome varchar(50),
	modelo varchar(50),
	potencia int,
	created_at timestamp,
	created_by varchar(60),
	updated_at timestamp,
	updated_by varchar(60),
    constraint fk_endereco
        foreign key (id_endereco)
            references endereco(id)
);
ALTER SEQUENCE energy_consumption.eletrodomestico_seq
OWNED BY energy_consumption.eletrodomestico.id;

create sequence energy_consumption.pessoa_seq;
create type energy_consumption.pessoa_genero as ENUM('MASCULINO', 'FEMININO', 'OUTRO');
create table energy_consumption.pessoa(
	id bigint primary key  not null DEFAULT nextval('energy_consumption.pessoa_seq'),
	nome varchar(100) not null,
	data_nascimento date not null,
	pessoa_genero pessoa_genero not null,
	id_endereco bigint,
    created_at timestamp not null,
    created_by varchar(60) not null,
    updated_at timestamp,
    updated_by varchar(60),
    constraint fk_endereco
     foreign key (id_endereco)
        references endereco(id)
);
ALTER SEQUENCE energy_consumption.pessoa_seq
OWNED BY energy_consumption.pessoa.id;

create sequence energy_consumption.parentesco_seq;
create table energy_consumption.parentesco(
	id bigint primary key  not null DEFAULT nextval('energy_consumption.pessoa_seq'),
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
ALTER SEQUENCE energy_consumption.parentesco_seq
OWNED BY energy_consumption.parentesco.id;
