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
		List<Movie> list = MovieService.getAllMovies();
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

		// This method prints the only the movie detail without rating.
		// displayMovieDetail();
		// String date = "2021-05-03";

		// This method saves the data
		// MovieService.addMovie("Dola", null, true);

		// This method returns all movie along with their rating without order.
		// System.out.println(MovieService.getAllMovieWithRating());

		// This method deletes the particular movie
		// MovieService.removeMovie("Kathi");

		// It displays all the details of the movie along with their average rating in
		// descending order
		// displayMovieWithRating();

		// It prints the details of the particular movie along with their average rating
		// System.out.println(MovieService.getMovieWithRatingByMovieName("Bhoomi"));

		// It prints all the movie list according to their average rating descending
		// (high to low)
		// System.out.println(MovieService.getMoviesWithRating());

		// counts number of user rated 8 and above 8 for all the movie
		// System.out.println(MovieService.getMovieAndRatingByRating(3));

		// It prints the movie with average rating greater than and equal to 6
		System.out.println(MovieService.getMovieByAverageRating(3));

	}

}
