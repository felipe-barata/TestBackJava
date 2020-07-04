create table if not exists gastos (
    id bigint not null AUTO_INCREMENT,
    descricao varchar(500) not null,
    valor double not null,
    codigousuario integer not null,
    data timestamp not null,
    categoria integer,
    primary key (id)
)ENGINE = innodb;


create table if not exists usuario (
    id integer not null AUTO_INCREMENT,
    nome varchar(100),
primary key (id)
)ENGINE = innodb;


create table if not exists categoria (
    id integer not null AUTO_INCREMENT,
    nome varchar(100),
primary key (id)
)ENGINE = innodb;