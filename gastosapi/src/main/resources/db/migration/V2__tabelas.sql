create table if not EXISTS sistemas(
    id int not null AUTO_INCREMENT,
    nome varchar(100),
    token varchar (200),
    primary key (id)
)ENGINE = innodb;

alter table usuario add senha varchar(120) not null;
alter table gastos add codigosistema int not null;