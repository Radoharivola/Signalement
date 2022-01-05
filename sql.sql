create table typeSignalement (
    id serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    nom varchar(25) not null
);

create table region(
    id serial primary key,
    nom varchar(255)
);


create table signalement(
    id serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    idUser int not null,
    idType int not null,
    idRegion int,

    --idRegion int not null,
    -- imageSignalement binary not null, ho an Sitraka
    longitude float not null,
    latitude float not null,
    descriptionSignalement text not null,
    dateSignalement timestamp not null,
    foreign key (idType) references typeSignalement(id),
    foreign key(idRegion) references region(id);
    --foreign key( idRegion) references region(id)
);

create table 

insert into typeSignalement values('1','Lalana');
insert into typeSignalement values('2','Fanolanana');
insert into typeSignalement values('3','Andrebabe');


insert into signalement values('1','1','1','image1.jpg','24.3421','21.343223','this is a test','2000-12-12 00:00:00');
insert into signalement values('2','1','2','image2.jpg','24.3421','21.343223','this is a test 2','2000-12-12 00:00:00');

insert into signalement values('3','1','1','image3.jpg','24.3421','21.343223','this is a test 3','2000-12-12 00:00:00');
insert into signalement values('4','1','3','image4.jpg','24.3421','21.343223','this is a test 4','2000-12-12 00:00:00');
insert into signalement values('5','1','3','image5.jpg','24.3421','21.343223','this is a test 5','2000-12-12 00:00:00');


insert into typeSignalement(nom) values('Infrastructure');
insert into typeSignalement(nom) values('Mpanolana');




insert into region(nomRegion) values('Analamanga');
insert into region(nomRegion) values('Ampafandrana');
insert into region(nomRegion) values('Apindray');
insert into region(nomRegion) values('Ampofy');
insert into region(nomRegion) values('Ambolomanga');
insert into region(nomRegion) values('Ambohibonga');
insert into region(nomRegion) values('ForangaBory');


create table Test2(
    id serial primary key,
    nom varchar(100),
    money float
);

insert into Test2 values('1','Jab','10033.23');
insert into Test2 values('2','Joob','324321.12243');
insert into Test2 values('3','Jebe','343548964');


create table imageSignalement (
    id serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    idSignalement int not null,
    imageSignalement varchar(255) not null,
    -- imageSignalement binary not null, ho an Sitraka ihany
    foreign key (idSignalement) references signalement(id)
);

insert into imageSignalement(idSignalement,imageSignalement) values('2','image1.jpg');
insert into imageSignalement(idSignalement,imageSignalement) values('2','image2.jpg');


create table enCours(
    id serial primary key,
    -- id int identity primary key, ho an katsa ihany
    idSignalement int not null,
    foreign key(idSignalement) references signalement(id)
);

insert into enCours values('1','2');
insert into enCours values('2','3');



create table termine(
    id serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    idSignalement int not null,
    foreign key(idSignalement) references signalement(id)
);

insert into termine values('1','2');
insert into termine values('2','5');


create table admin(
    id serial primary key,
    adminName varchar(255),
    email varchar(255),
    idRegion int not null,
    password varchar(255)
);



create table superAdmin(
    id serial primary key,
    SuName varchar(255),
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

create table userNotification(
    id serial primary key,
    dateNotification timestamp not null,
    idAppUser int not null,
    idSignalement int not null,
    notificationDetail text not null,
    foreign key(idAppUser) references appUser(id),
    foreign key(idSignalement) references signalement(id)
);







-- create table test(
--     nom varchar(255) not null
-- );

-- insert into test(nom) values('kenny');
-- insert into test(nom) values('sitraka');
-- insert into test(nom) values('bobota');
-- insert into test(nom) values('chantony');