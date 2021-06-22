package in.kannan.service;

import java.sql.SQLException;

import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;

public class RatingServiceTest {
	public static void main(String[] args) throws  ServiceException, DBException, ValidationException {
	//	RatingService.addUsersRating(2,1,9);
	//	RatingService.addUsersRating(4,1,8);
	//	RatingService.addUsersRating(6,2,7);
	//	RatingService.addUsersRating(8,4,5);
	//	RatingService.undoRating(6, 2);
		System.out.println(RatingService.countUsersRated("Sangath#alaivan"));
		

		
	}

}
