create table typeSignalement (
    id serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    nom varchar(25) not null
);
create table affectation (
    -- aleo refa anao anle affectation lo ty vao atao ka XD
);

create table region(
    id serial primary key,
    nomRegion varchar(255)
);

create table signalement(
    id serial primary key,
    -- id int identity primary key, ho an Sitraka ihany
    idUser int not null,
    idType int not null,
    --idRegion int not null,
    -- imageSignalement binary not null, ho an Sitraka
    imageSignalement varchar(100),
    longitude float not null,
    latitude float not null,
    descriptionSignalement text not null,
    foreign key (idType) references typeSignalement(id),
    --foreign key( idRegion) references region(id)
);

insert into typeSignalement values('1','Lalana');
insert into typeSignalement values('2','Fanolanana');
insert into typeSignalement values('3','Andrebabe');


insert into signalement values('1','1','1','image1.jpg','24.3421','21.343223','this is a test');
insert into signalement values('2','1','2','image2.jpg','24.3421','21.343223','this is a test 2');

insert into signalement values('3','1','1','image3.jpg','24.3421','21.343223','this is a test 3');
insert into signalement values('4','1','3','image4.jpg','24.3421','21.343223','this is a test 4');
insert into signalement values('5','1','3','image5.jpg','24.3421','21.343223','this is a test 5');



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








-- create table test(
--     nom varchar(255) not null
-- );

-- insert into test(nom) values('kenny');
-- insert into test(nom) values('sitraka');
-- insert into test(nom) values('bobota');
-- insert into test(nom) values('chantony');