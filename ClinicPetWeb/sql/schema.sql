-- create table
create table client (
	uid serial primary key,
	name varchar(20)
);

create table pet (
	uid serial primary key,
	client_id int not null references client(uid),
  type varchar(40),
	name varchar(20),
	sex varchar(20),
  age varchar(3)
);

-- add new client
insert into client (name) values ('Влад');

insert into client (name) values ('Анна');

-- add new pet
insert into pet (uid, client_id, type, name, sex, age) values (1, 1, 'собака', 'Тайсон', 'Мужской', '3');

insert into pet (uid, client_id, type, name, sex, age) values (2, 2, 'кошка', 'Лакки', 'Женский', '1');

-- select pet with client
select pet.name, client.name from pet as pet
inner join client as client on client.uid = pet.client_id;

-- select client by name
select * from client as client where client.name = 'Влад';

-- update pet
update pet as pet set name = 'Алекс' where pet.name = 'Тайсон';

-- delete pet by nick
delete from pet as pet where pet.name = 'Алекс'