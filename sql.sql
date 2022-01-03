create table typeSignalement (
    id serial primary key,
    nom varchar(25) not null
);
create table affectation (
    id serial primary key,
    dateAffectation timestamp not null,
    idRegion int not null,
    idSignalement int not null,
    foreign key(idSignalement) references signalement(id),
    foreign key(idRegion) references region(id)
);

create table region(
    id serial primary key,
    nomRegion varchar(255)
);



create table signalement(
    id serial primary key,
    idUser int not null,
    idtype int not null,
    longitude float not null,
    latitude float not null,
    descriptionsignalement text not null,
    dateSignalement timestamp not null,
    foreign key(idtype) references typesignalement(id)
);

insert into typeSignalement values('1','Lalana');
insert into typeSignalement values('2','Fanolanana');
insert into typeSignalement values('3','Andrebabe');


insert into signalement values('1','1','1','24.3421','21.343223','this is a test','2000-01-01 00:00:00');
insert into signalement values('2','1','2','24.3421','21.343223','this is a test 2','2000-01-01 00:00:00');

insert into signalement values('3','1','1','24.3421','21.343223','this is a test 3','2000-01-01 00:00:00');
insert into signalement values('4','1','3','24.3421','21.343223','this is a test 4','2000-01-01 00:00:00');
insert into signalement values('5','1','3','24.3421','21.343223','this is a test 5','2000-01-01 00:00:00');

insert into region values('1','Analamanga');
insert into region values('2','vatovavy');

create table imageSignalement (
    id serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    idSignalement int not null,
    imageSignalement bytea not null,
    -- imageSignalement binary not null, ho an Sitraka ihany
    foreign key (idSignalement) references signalement(id)
);
create table enCours(
    id serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    idSignalement int not null,
    foreign key(idSignalement) references signalement(id)
);

create table termine(
    id serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    idSignalement int not null,
    foreign key(idSignalement) references signalement(id)
);


-- ilaina am login
create table appUser(
    id int serial primary key,
    nom varchar(25) not null,
    prenom varchar(25) not null,
    email varchar(25) not null,
    mdp varchar(255) not null
);
create table appUserToken(
    idUser int not null,
    token varchar(255) not null,
    foreign key(idUser) references user(id)
);

