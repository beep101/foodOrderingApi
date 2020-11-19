drop table if exists stavka;
drop table if exists jelo;
drop table if exists narudzba;
drop table if exists restoran;
drop table if exists userTbl;
drop table if exists kategorija;

create table userTbl(
	id SERIAL primary key,
   uname varchar(50) not null unique,
   passwd char(64) not null,
   admin BOOLEAN NOT NULL 
);

create table restoran(
	id SERIAL primary key,
   ime varchar(100),
   adresa varchar(200),
   tel varchar(20),
   email varchar(100),
   opis text,
   usr int not null,
   foreign key (usr) references userTbl(id)
);

create table kategorija(
	id SERIAL primary key,
   ime varchar(50),
   opis text
);

create table jelo(
	id SERIAL primary key,
   restoran int not null,
   kategorija int not null,
   ime varchar(100),
   opis text,
   sastav text,
   cijena decimal,
   foreign key (restoran) references restoran(id),
   foreign key (kategorija) references kategorija(id)
);

create table narudzba(
	id SERIAL primary key,
   ime varchar(100),
   adresa varchar(200),
   tel varchar(20),
   email varchar(100),
   napomena text,
   restoran int not null,
   foreign key (restoran) references restoran(id)
);

create table stavka(
   id SERIAL PRIMARY KEY,
	narudzba int not null,
   jelo int not null,
   kolicina int not null check (kolicina > 0),
   foreign key (narudzba) references narudzba(id),
   foreign key (jelo) references jelo(id)
)