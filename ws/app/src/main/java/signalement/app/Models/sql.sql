create table typeSignalement (
    id serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    nom varchar(25) not null
);


insert into typeSignalement values('1','Lalana');
insert into typeSignalement values('2','Fanolanana');
insert into typeSignalement values('3','Andrebabe');

create table region(
    id serial primary key,
    nom varchar(255)
);


insert into region(nom) values('Diana');
insert into region(nom) values('Sava');
insert into region(nom) values('Sofia');
insert into region(nom) values('Analanjirofo');
insert into region(nom) values('Boeny');
insert into region(nom) values('Betsiboka');
insert into region(nom) values('Melaky');
insert into region(nom) values('Bongolava');
insert into region(nom) values('Analamanga');
insert into region(nom) values('Itasy');
insert into region(nom) values('Alaotra-mangoro');
insert into region(nom) values('Vakinakaratra');
insert into region(nom) values('Atsinanana');
insert into region(nom) values('Menabe');
insert into region(nom) values('Amorony Mania');
insert into region(nom) values('Matsiatra Ambony');
insert into region(nom) values('Vatovavy-Fitovinany');
insert into region(nom) values('Ihorombe');
insert into region(nom) values('Atsimo-Andrefana');
insert into region(nom) values('Anosy');
insert into region(nom) values('Atsimo-Atsinanana');
insert into region(nom) values('Androy');

create table signalement(
    id serial primary key,
    idUser int not null,
    idType int not null,
    idRegion int,
    longitude float not null,
    latitude float not null,
    descriptionSignalement text not null,
    dateSignalement timestamp not null,
    foreign key(idType) references typeSignalement(id),
    foreign key(idRegion) references region(id)
);



insert into signalement values('1','1','1','1','24.3421','21.343223','this is a test','2000-12-12 00:00:00');
insert into signalement values('2','1','2','1','24.3421','21.343223','this is a test 2','2000-12-12 00:00:00');

insert into signalement values('3','1','1','1','24.3421','21.343223','this is a test 3','2000-12-12 00:00:00');
insert into signalement values('4','1','3','1','24.3421','21.343223','this is a test 4','2000-12-12 00:00:00');
insert into signalement values('5','1','3','1','24.3421','21.343223','this is a test 5','2000-12-12 00:00:00');








create table imageSignalement (
    id serial primary key,
    idSignalement int not null,
    imageSignalement varchar(255) not null,
    foreign key (idSignalement) references signalement(id)
);

insert into imageSignalement(idSignalement,imageSignalement) values('2','image1.jpg');
insert into imageSignalement(idSignalement,imageSignalement) values('2','image2.jpg');


create table enCours(
    id serial primary key,
    idSignalement int not null,
    foreign key(idSignalement) references signalement(id)
);

insert into enCours values('1','2');
insert into enCours values('2','3');



create table termine(
    id serial primary key,
    idSignalement int not null,
    foreign key(idSignalement) references signalement(id)
);

insert into termine values('1','2');
insert into termine values('2','5');


create table Admin(
    id Serial primary key,
    nom varchar(100),
    email varchar(255),
    idRegion int,
    mdp varchar(255),
    etat int,
    foreign key(idRegion) references region(id)

);

alter table region add column etat int;

alter table typesignalement add column etat int;

update region set etat=1;
update typesignalement set etat=1;



create table superAdmin(
    id serial primary key,
    mdp varchar(255),
    email varchar(255),
    password varchar(255)
);

create table appUser(
    id serial primary key,
    nom varchar(255),
    prenom varchar(255),
    email varchar(255),
    mdp varchar(255)
);

create table userToken(
    idUser int not null,
    token varchar(255) not null
);


create table appUser(
    id serial primary key,
    email varchar(255) not null,
    mdp varchar(255) not null
);


create table userNotification(
    id serial primary key,
    dateNotification timestamp not null,
    idAppUser int not null,
    idSignalement int not null,
    notificationDetail text not null,
    notificationTitle varchar(25) not null,
    foreign key(idAppUser) references appUser(id),
    foreign key(idSignalement) references signalement(id)
);



