/**
 * Sistema para gestão de OS
 * @author Nicolas 
 */
 use dbsistema;
 drop table usuarios;
 
 create database dbsistema;
 use dbsistema;
 show tables;
 
 create table usuarios (
	id int primary key auto_increment,
    Nome varchar(50) not null,
	login varchar(15) not null,
	senha varchar(250) not null,
	perfil varchar(10) not null
);

select * from usuarios where login = 'admin' and senha = md5('123456');



describe usuarios;

-- uso do md5() para criptografar uma senha

insert into usuarios( nome,login,senha,perfil)
values('Administrador','admin' ,md5( '123456'),'admin');

insert into usuarios( nome,login,senha,perfil)
values('Nicolas','nicolas' ,md5( '123456'),'Usuario');

insert into usuarios( nome,login,senha)
values('Leandro Ramos','admin',md5('123456'));

insert into usuarios( nome,login,senha)
values('nicolas','admin',md5('123456'));

insert into usuarios( nome,login,senha)
values('Joao','admin',md5('123456'));

insert into usuarios( nome,login,senha)
values('gustavo','sss',md5('123456'));

insert into usuarios( nome,login,senha)
values('Manoel G','admin',md5('123456'));

select * from usuarios;

select * from usuarios order by nome;
select * from usuarios order by nome desc;
select * from usuarios where login = 'admin' and senha = md5('123456');

drop table contatos;

 show tables;
 
 create table contatos (
	id int primary key auto_increment,
    Nome varchar(50) not null,
	Fone varchar(15) not null,
	Email varchar(250)
);

describe contatos;

delete from contatos where id = 2;

select * from contatos;

select * from contatos order by nome;
select * from contatos order by nome desc;

use dbsistema;
drop table clientes;
 
 create database dbsistema;
 use dbsistema;
 show tables;
 
 create table clientes (
	idcli int primary key auto_increment,
    nome varchar(50) not null,
	fone varchar(15)not null,
    cep varchar (10),
	endereco varchar(50) not null,
	numero varchar(10) not null,
    complemento varchar(20),
    bairro varchar(30) not null,
    cidade varchar(30) not null,
    uf char(2) not null
);

insert into clientes( nome,fone,cep,endereco,numero,complemento,bairro,cidade)
values('nicolasss m','12365456398','986099399','rua' ,'1','agriajass','asdas''sp');

describe clientes;

select * from clientes;


select * from clientes order by nome;
select * from clientes order by nome desc;
select * from clientes where login = 'admin' and senha = md5('123456');

/* Relacionamento de tabelas 1- N */

 

-- timestamp default current_timestamp (data e hora automática)
-- decimal (números não inteiros) 10,2 (dígitos, casas decimais)
-- 1 (FK) --------- N (PK)

 drop table servicos;

create table servicos (
     os int primary key auto_increment,
     dataOS timestamp default current_timestamp,
     motor varchar(200) not null,
     servico varchar(200) not null,
     valor decimal(0000000000.00),
     idcli int not null,
     foreign key (idcli) references clientes(idcli)
);
drop table servicos;

insert into servicos (motor,servico,valor,idcli)
values ('ssssss','refazer motor','1002','2');

select *from  servicos;
select *from  servicos
inner join clientes
on servicos.idcli = clientes.idcli;

/** RELATÓRIOS **/
-- clientes
select nome,fone from clientes order by nome;

-- servicos
SELECT 
    servicos.os,
    servicos.dataOS,
    servicos.servico,
    servicos.valor
FROM
    servicos
        INNER JOIN
    clientes ON servicos.idcli = clientes.idcli;
    use dbsistema;

create table fornecedor (
	idfor int primary key auto_increment,
    nome varchar(50) not null,
    cnpj decimal(14) not null,
	fone varchar(15)not null,
    cep varchar (10),
	endereco varchar(50) not null,
	numero varchar(10) not null,
    complemento varchar (20),
    bairro varchar(30) not null,
    cidade varchar(30) not null,
    uf char(2) not null
);
select * from  fornecedor;

drop table fornecedor;
drop table produtos;

create table produtos (
codigoproduto int primary key auto_increment,
nome varchar(30)not null,
codigodebarras decimal (14)not null,
descricao varchar (500) not null,
foto longblob not null,
estoque int not null,
estoquemin int not null,
valor decimal (6,2) not null,
unidadedemedida varchar (2) not null,
localdearmazenamento varchar (30) not null
);
select * from produtos;
describe produtos;
    
    






