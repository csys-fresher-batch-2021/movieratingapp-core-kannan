# core Features:
# 1-Display all movies with average rating order by average rating desc.

System.out.println(MovieService.getMoviesWithRating());

Output:

[MovieRating [movie=Movie [id=2, name=Bhoomi, releaseDate=2021-01-14, status=true], rating=4.4],

 MovieRating [movie=Movie [id=1, name=Master, releaseDate=2021-01-13, status=true], rating=4.0],
 
 MovieRating [movie=Movie [id=8, name=Centha, releaseDate=2021-02-26, status=true], rating=4.0],
 MovieRating [movie=Movie [id=5, name=Kutty Story, releaseDate=2021-02-12, status=true], rating=3.5], 
 
MovieRating [movie=Movie [id=4, name=Keelakadu, releaseDate=2021-01-29, status=true], rating=3.25],

 MovieRating [movie=Movie [id=3, name=Pulikkuthi Pandi, releaseDate=2021-01-15, status=true], rating=3.25], 
 
MovieRating [movie=Movie [id=6, name=Sangathalaivan, releaseDate=2021-02-26, status=true], rating=0.0],

MovieRating [movie=Movie [id=7, name=Chakra, releaseDate=2021-02-19, status=true], rating=0.0]]
 

# 2-Rating by user

RatingService.addUserRating(2, 1, 5);

userId =2,

movieId=1,

rating =5;


# 3-Display Total number of user rated for the particular movie:

System.out.println(RatingService.countUsersRated("Master"));

output:5

# 4-for particular movie Rating List as number of user rated 5 ,4 etc:

System.out.println(RatingService.getRatingCountByMovieName("Bhoomi"));

Output:


[UserCount [rating=5.0, count=3],   UserCount [rating=4.0, count=1],   UserCount [rating=3.0, count=1]]


# 5- This feature is to add rating by user here movie list with average rating is displayed without order

System.out.println(MovieService.getAllMovieWithRating());

[MovieRating [movie=Movie [id=2, name=Bhoomi, releaseDate=2021-01-14, status=true], rating=4.5],

MovieRating [movie=Movie [id=3, name=Pulikkuthi Pandi, releaseDate=2021-01-15, status=true], rating=2.0], 

MovieRating [movie=Movie [id=4, name=Keelakadu, releaseDate=2021-01-29, status=true], rating=3.0], 

MovieRating [movie=Movie [id=1, name=Master, releaseDate=2021-01-13, status=true], rating=0.0], 

MovieRating [movie=Movie [id=5, name=Kutty Story, releaseDate=2021-02-12, status=true], rating=0.0],

MovieRating [movie=Movie [id=6, name=Sangathalaivan, releaseDate=2021-02-26, status=true], rating=0.0]]


# 6-When the user unknowingly rated and he wants to remove his rating


RatingService.undoRating(2, 1);  userId = 2 ,movieId = 1;


# 7- Number of user rated a particular rating for a particular movie

System.out.println(RatingService.countRatingByRatingAndMovieName(5, "Master"));

output: 1

# 8-Display Movie Details:

displayMovieDetail();

output:

Movie [id=1, name=Master, releaseDate=2021-01-13, status=true]

Movie [id=2, name=Bhoomi, releaseDate=2021-01-14, status=true]

Movie [id=3, name=Pulikkuthi Pandi, releaseDate=2021-01-15, status=true]

Movie [id=4, name=Kutty Story, releaseDate=2021-02-12, status=true]

Movie [id=5, name=Parris Jeyaraj, releaseDate=2021-02-12, status=true]




# 9-This prints the total number of users rated for each movie for the given rating.

System.out.println(MovieService.getMovieRatingByRating(3));

output:[CountRating [movie=Movie [id=1, name=Master, releaseDate=2021-01-13, status=true], count=2], 

CountRating [movie=Movie [id=2, name=Bhoomi, releaseDate=2021-01-14, status=true], count=2], 

CountRating [movie=Movie [id=3, name=Pulikkuthi Pandi, releaseDate=2021-01-15, status=true], count=1], 

CountRating [movie=Movie [id=4, name=Kutty Story, releaseDate=2021-02-12, status=true], count=1]

# 10-This print the list of movies having average rating equal and greater than 3.

System.out.println(MovieService.getMovieByAverageRating(3));

Output:[MovieRating [movie=Movie [id=1, name=Master, releaseDate=2021-01-13, status=true], rating=4.0], 

MovieRating [movie=Movie [id=2, name=Bhoomi, releaseDate=2021-01-14, status=true], rating=3.5], 

MovieRating [movie=Movie [id=3, name=Pulikkuthi Pandi, releaseDate=2021-01-15, status=true], rating=3.0], 

MovieRating [movie=Movie [id=4, name=Kutty Story, releaseDate=2021-02-12, status=true], rating=3.5]]


# 11-Add movie by Admin

MovieService.addMovie("Dola", "2021-02-12",true);

# 12-Remove movie by Admin

MovieService.removeMovie("Bhoomi");

# 13- UserRegistration

UserService.userRegistration("Ashwin", "ashwin1234@gmail.com", "as12AS@#Dwe", "ADMIN");

# 14-UserLogin

UserService.userLogin("naraya@live.com", "jefien344@##AAS");

# 15-AdminLogin

UserService.adminLogin("kannanraemes@hgmail.com", "fre3232SFE2#");

# 16-Find movie detail with average rating for movie

System.out.println(MovieService.getMovieWithRatingByMovieName("Master"));

Output:

MovieRating [movie=Movie [id=1, name=Master, releaseDate=2021-01-13, status=true], rating=4.0]





















 
