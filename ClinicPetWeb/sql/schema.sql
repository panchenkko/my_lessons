create table client (
	uid serial primary key,
	name varchar(20),
	pet_id int not null references pet(uid)
);

create table pet (
	uid serial primary key,
	type varchar(40),
	petName varchar(20),
	sex varchar(20),
	age varchar(3)
);