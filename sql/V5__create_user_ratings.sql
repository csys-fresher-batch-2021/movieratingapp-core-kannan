create table user_ratings(rating_id serial primary key,user_id int references users(user_id)  ,
						 movie_id int references movies(movie_id),
						 rating int check(rating>=0 and rating <=5));