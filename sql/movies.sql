create table movies(movie_id  serial ,movie_name varchar(255) not null unique,release_date date not null ,
					status boolean default true ,end_date date );
					
	insert into movies(movie_name,release_date,end_date) values ('Master','2020-12-01','2021-04-01');
    insert into movies(movie_name,release_date) values ('Karnan','2021-02-01');
	insert into movies(movie_name,release_date) values ('Eswaran','2020-12-01');