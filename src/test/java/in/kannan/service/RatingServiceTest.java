package in.kannan.service;

import java.sql.SQLException;

import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;

public class RatingServiceTest {
	public static void main(String[] args) throws SQLException, ServiceException, DBException {
		RatingService.addUsersRating(1,9,6);
		
	}

}
