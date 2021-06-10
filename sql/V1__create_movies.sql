create table movies(movie_id  serial primary key ,movie_name varchar(255) not null unique,release_date date not null ,
					status boolean default true  );
					

