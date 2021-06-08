create table movies(movie_id  serial primary key ,movie_name varchar(255) not null unique,release_date date not null ,
					status boolean default true ,end_date date );
					
	insert into movies(movie_name,release_date,end_date) values ('Master','2020-12-01','2021-04-01');
    insert into movies(movie_name,release_date) values ('Karnan','2021-02-01');
	insert into movies(movie_name,release_date) values ('Eswaran','2020-12-01');
	
	
create table users(id serial primary key,name varchar(255) not null ,
				 email varchar(255) not null unique, password varchar(255) not null,
				  role varchar(255) not null check(role in('Admin','User')));
				  
	insert into users 
	   (name,email,password,role) 
	       values 
	   ('Kannan','kannanramesh@gmail.com','fre3232SFE2#','Admin'),
	   ('Surya','surya1234@gmail.com','wewr234@#AS@ss','User'),
	   ('Narayanan','naraya@live.com','jefien344@##AAS','Admin'),
	   ('Sankar','sankaran12@yahoo.com','LO122nei!@n','User');