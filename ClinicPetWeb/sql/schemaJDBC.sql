-- create table
create table client (
	uid serial primary key,
	name varchar(20)
);

create table pet (
	uid serial primary key,
	client_id int not null references client(uid),
  type varchar(40),
	petName varchar(20),
	sex varchar(20),
  age varchar(3)
);

-- add new client
insert into client (name) values ('Влад');

insert into client (name) values ('Анна');

-- add new pet
insert into pet (client_id, type, petName, sex, age) values (1, 'собака', 'Тайсон', 'Мужской', '3');

insert into pet (client_id, type, petName, sex, age) values (2, 'кошка', 'Лакки', 'Женский', '1');

-- select pet with client
select client.name, pet.type, pet.petName, pet.sex, pet.age from pet
inner join client on client.uid = pet.client_id;

-- select client by name
select * from client where client.name = 'Влад';

-- update pet
update pet set petName = 'Алекс' where pet.petName = 'Тайсон';

-- delete pet by nick
delete from pet where pet.petName = 'Алекс'