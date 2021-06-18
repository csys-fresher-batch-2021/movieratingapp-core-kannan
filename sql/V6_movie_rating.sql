create table movie_rating(movie_id int references movies(movie_id),
                     rating numeric(4,2) check(rating>0 and rating <=10) );