package in.kannan.service;

import java.util.List;

import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;
import in.kannan.model.Movie;
import in.kannan.model.MovieRating;

public class MovieServiceTest {
	public MovieServiceTest() {
		super();
	}

	/**
	 * displays movie details
	 * 
	 * @param list contain movie detail
	 * @throws ServiceException
	 */
	public static void displayMovieDetail() throws ServiceException {
		List<Movie> list = MovieService.getAllExceptRatings();
		for (Movie movie : list) {
			System.out.println(movie);

		}
	}
	
	/**
	 * displays movie details
	 * 
	 * @param list contain movie detail
	 * @throws ServiceException
	 */
	public static void displayMovieWithRating() throws ServiceException {
		List<MovieRating> movieRating = MovieService.getMoviesWithRating();
		for (MovieRating movie : movieRating) {
			System.out.println(movie);

		}
	}

	/**
	 * main method to start execution
	 * 
	 * @param args
	 * @throws ServiceException
	 * @throws AdminLoginException
	 * @throws DBException
	 * @throws ValidationException 
	 */

	public static void main(String[] args) throws ServiceException, ValidationException {
		

	//	displayMovieDetail();
	//	String date = "2021-05-03";
	//	MovieService.save("Dola", date, true);
	//	MovieService.removeMovie("Sencholai");
    //    System.out.println(MovieService.getAll());
		MovieService.removeMovie("Kathi");
	//	displayMovieWithRating();
	//	System.out.println(MovieService.findByMovieName("Sangathalaivan"));
	//	System.out.println(MovieService.getMoviesWithRating());
	//	System.out.println(MovieService.getMoviesWithRating());
		
	}

}
