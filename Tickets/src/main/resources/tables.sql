create table tickets (
id serial primary key,
country varchar(40),
price int
);

create table reservations (
id serial primary key,
name varchar(20),
surname varchar(25),
telephone bigint,
ticket_id int,
foreign key (ticket_id) references tickets(id)
);