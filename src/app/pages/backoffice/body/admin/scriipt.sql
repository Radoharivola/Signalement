
-- insert into Admin(nom,email,idRegion,mdp) values('Thomas Hardy','thomashardy@mail.com','1','sha1(admin1)');


-- insert into Admin(nom,email,idRegion,mdp) values('Dominique Perrier','dominiqueperrier@mail.com','2','sha1(admin2)');


-- insert into Admin(nom,email,idRegion,mdp) values('Maria Anders','mariaanders@mail.com','3','sha1(admin3)');


-- insert into Admin(nom,email,idRegion,mdp) values('Fran Wilson','franwilson@mail.com','4','sha1(admin4)');


-- insert into Admin(nom,email,idRegion,mdp) values('	Martin Blank','martinblank@mail.com','5','sha1(admin5)');

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

alter table typeproblem add column etat int;

update region set etat=1;
update typeproblem set etat=1;


									
						
						
						
											
				