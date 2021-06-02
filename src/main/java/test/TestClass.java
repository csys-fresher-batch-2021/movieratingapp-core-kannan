package test;

import java.util.List;
import exception.ServiceException;
import model.Movie;
import service.MovieManager;

public class TestClass {
	public static void displayMovieDetails(List<Movie> list) {
		for (Movie movie : list) {
			System.out.println(movie);
		}
	}

	public static void main(String[] args) throws ServiceException {
		MovieManager.displayMovieDetails();
	}

}
