package in.kannan.service;

import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;

public class RatingServiceTest {
	public static void main(String[] args) throws ServiceException, DBException, ValidationException {
		// RatingService.addUsersRating(2, 1, 9);
		// RatingService.addUsersRating(4, 1, 8);
		// RatingService.addUsersRating(6, 2, 7);

		// This method adds user of Id 8 ,movie Id 4 a rating of 5
		// RatingService.addUsersRating(8, 4, 5);

		// This method counts number of user rated for the particular movie
		// System.out.println(RatingService.countUsersRated("Master"));

		// This method undo the rating of userId =2 to movieId =1
		// RatingService.undoRating(2, 1);// for user Id =2 , movie Id =1

		// This method counts the number of user rated 10 for master movie.
		// System.out.println(RatingService.countRatingByRatingAndMovieName(10,
		// "Master"));

		// This method returns the list of rating along with the number of user rated
		// for the particular rating for the given movie.
		System.out.println(RatingService.getRatingCountByMovieName("Master"));

	}

}
