create table users(user_id serial primary key,name varchar(100) not null, 
          email varchar(60) not null unique,password varchar(60) not null,
          role varchar(10) not null check(role in('ADMIN','USER')));