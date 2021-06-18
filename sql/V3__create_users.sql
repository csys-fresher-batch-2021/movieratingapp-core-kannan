create table users(id serial primary key,name varchar(30) not null, 
          email varchar(30) not null unique,password varchar(30) not null,
          role varchar(10) not null check(role in('ADMIN','USER')));