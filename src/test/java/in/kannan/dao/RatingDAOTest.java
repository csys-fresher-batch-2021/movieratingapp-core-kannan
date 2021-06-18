package in.kannan.dao;

import java.sql.SQLException;

import in.kannan.exception.DBException;
import in.kannan.util.ConnectionUtil;

public class RatingDAOTest {
	public static void main(String[] args) throws SQLException, DBException {
		//System.out.println(MovieRatingDAO.checkIsRated(1, 23));
		//System.out.println(MovieRatingDAO.findRating());
		UserRatingDAO.save(8, 2, 5);
		//System.out.println(ConnectionUtil.getConnection());
	}

}
