create table movies(movie_id int not null , 
				   movie_name varchar(255) not null unique,
				   release_date date not null,status boolean not null,
					end_date date not null, primary key(movie_id)
				   );