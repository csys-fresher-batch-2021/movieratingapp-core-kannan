create table movies(movie_id  serial primary key ,movie_name varchar(255) not null unique,release_date date not null ,
					status boolean default true  );
					
insert into movies(movie_name,release_date) values ('Master','2021-01-13');
insert into movies(movie_name,release_date) values ('Eswaran','2021-01-14');
insert into movies(movie_name,release_date) values ('Sangathalaivan','2021-02-26');
insert into movies(movie_name,release_date) values ('Teddy','2021-03-12');

	
	
create table users(id serial primary key,name varchar(255) not null ,
				 email varchar(255) not null unique, password varchar(255) not null,
				  role varchar(255) not null check(role in('ADMIN','USER')));
				  
	insert into users 
	   (name,email,password,role) 
	       values 
	   ('Kannan','kannanramesh@gmail.com','fre3232SFE2#','ADMIN'),
	   ('Surya','surya1234@gmail.com','wewr234@#AS@ss','USER'),
	   ('Narayanan','naraya@live.com','jefien344@##AAS','ADMIN'),
	   ('Sankar','sankaran12@yahoo.com','LO122nei!@n','USER');