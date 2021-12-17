create table typeSignalement (
    id int serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    nom varchar(25) not null
);
create table affectation (
    -- aleo refa anao anle affectation lo ty vao atao ka XD
);

create table signalement(
    id int serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    idUser int not null,
    idType int not null,
    -- imageSignalement binary not null, ho an Sitraka
    imageSignalement bytea not null,
    longitude float not null,
    latitude float not null,
    descriptionSignalement text not null,
    foreign key (idType) references typeSignalement(id)
);

create table imageSignalement (
    id int serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    idSignalement int not null,
    imageSignalement bytea not null,
    -- imageSignalement binary not null, ho an Sitraka ihany
    foreign key (idSignalement) references signalement(id)
);
create table enCours(
    id int serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    idSignalement int not null,
    foreign key(idSignalement) references signalement(id)
);

create table termine(
    id int serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    idSignalement int not null,
    foreign key(idSignalement) references signalement(id)
);







-- create table test(
--     nom varchar(255) not null
-- );

-- insert into test(nom) values('kenny');
-- insert into test(nom) values('sitraka');
-- insert into test(nom) values('bobota');
-- insert into test(nom) values('chantony');