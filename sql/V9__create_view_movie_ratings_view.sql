create view movie_ratings_view as 
select m.movie_id,m.movie_name,m.release_date,m.status,(case when a.round is null then 0 else a.round end) 
as average_rating from movies m  left join (select movie_id , round(avg(rating),2) from user_ratings 
											where active = true group by movie_id) as a 
on m.movie_id = a.movie_id order by m.release_date desc;