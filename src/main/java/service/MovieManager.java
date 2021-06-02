package service;

import java.util.List;

import dao.DisplayMovieDetailsDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Movie;
import test.TestClass;

public class MovieManager {
	private MovieManager() {
		super();
	}
	public static void displayMovieDetails() throws ServiceException {
		try {
			List<Movie> list=DisplayMovieDetailsDAO.display();
			TestClass.displayMovieDetails(list);
		} catch (DAOException e) {
			
			e.printStackTrace();
			throw new ServiceException(e,"Unable to Display Movie");
		}
	}

}
