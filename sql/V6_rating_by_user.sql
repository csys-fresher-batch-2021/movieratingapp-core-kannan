create table rating_by_user(user_id int references users(id)  ,
						 movie_id int references movies(movie_id),
						 rating numeric(4,2) check(rating>=0 and rating <=10) default 0);