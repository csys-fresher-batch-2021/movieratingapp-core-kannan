package in.kannan.dao;

import java.sql.SQLException;

import in.kannan.exception.DBException;

public class RatingDAOTest {
	public static void main(String[] args) throws SQLException, DBException {
		//System.out.println(MovieRatingDAO.checkIsRated(1, 23));
		//System.out.println(MovieRatingDAO.findRating());
		MovieRatingDAO.save(3, 3, 5);
	}

}
