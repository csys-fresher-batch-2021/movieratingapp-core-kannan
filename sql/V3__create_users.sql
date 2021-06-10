create table users(id serial primary key,name varchar(255) not null ,
				 email varchar(255) not null unique, password varchar(255) not null,
				  role varchar(255) not null check(role in('ADMIN','USER')));