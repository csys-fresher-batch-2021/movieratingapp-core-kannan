package in.kannan.dao;

import java.sql.SQLException;

import in.kannan.dao.impl.UserRatingDAOImpl;
import in.kannan.exception.DBException;

public class RatingDAOTest {
	public static void main(String[] args) throws SQLException, DBException {
		UserRatingDAO userRatingDAO = new UserRatingDAOImpl();
		// System.out.println(MovieRatingDAO.checkIsRated(1, 23));
		// System.out.println(MovieRatingDAO.findRating());
		userRatingDAO.save(8, 2, 5);
		// System.out.println(ConnectionUtil.getConnection());
	}

}
