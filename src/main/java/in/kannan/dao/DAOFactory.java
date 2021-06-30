package in.kannan.dao;

import in.kannan.dao.impl.MovieDAOImpl;
import in.kannan.dao.impl.UserDAOImpl;
import in.kannan.dao.impl.UserRatingDAOImpl;

public class DAOFactory {
	/**
	 * This method creates the instance to MovieDAO class.
	 * 
	 * @return
	 */

	public static MovieDAO getMovieDAOInstance() {
		MovieDAO movieDAO = new MovieDAOImpl();

		return movieDAO;
	}

	/**
	 * This method creates the instance to UserDAO class.
	 * 
	 * @return
	 */

	public static UserDAO getUserDAOInstance() {
		UserDAO userDAO = new UserDAOImpl();
		return userDAO;
	}

	/**
	 * This method creates the instance to UserRatingDAO Class.
	 * 
	 * @return
	 */

	public static UserRatingDAO getUserRatingDAOInstance() {
		UserRatingDAO userRatingDAO = new UserRatingDAOImpl();
		return userRatingDAO;
	}
}
