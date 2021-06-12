create table rating_by_user(user_id int references users(id)  ,
						   movie_id int references movies(movie_id),
							rating int );