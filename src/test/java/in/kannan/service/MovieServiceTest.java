package in.kannan.service;

import java.io.IOException;
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
		List<Movie> movie = MovieService.getInstance().getAllMovies();

		System.out.println("Sorting BY Date");
		for (Movie m : movie) {
			System.out.println(m);
		}

	}

	/**
	 * displays movie details
	 * 
	 * @param list contain movie detail
	 * @throws ServiceException
	 * @throws IOException
	 */
	public static void displayMovieWithRating() throws ServiceException, IOException {
		List<MovieRating> movieRating = MovieService.getInstance().getMoviesWithRating();
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
	 * @throws IOException
	 */

	public static void main(String[] args) throws ServiceException, ValidationException, IOException {

		// This method prints the only the movie detail without rating.
		// displayMovieDetail();
		// String date = "2021-05-03";

		// This method saves the data
		// MovieService.getInstance().addMovie("V", "2021-01-08", true);

		// This method returns all movie along with their rating without order.
		// System.out.println(MovieService.getInstance().getAllMovieWithRating());

		// This method deletes the particular movie
		// MovieService.getInstance().removeMovie("Keelakadu");

		// It displays all the details of the movie along with their average rating in
		// descending order
		displayMovieWithRating();

		// It prints the details of the particular movie along with their average rating
		// System.out.println(MovieService.getInstance().getMovieWithRatingByMovieName("Bhoomi"));

		// It prints all the movie list according to their average rating descending
		// (high to low)
		// System.out.println(MovieService.getInstance().getMoviesWithRating());

		// counts number of user rated 3 and above 3 for all the movie
//		System.out.println(MovieService.getInstance().getMovieRatingByRating(3));

		// It prints the movie with average rating greater than and equal to 3
		// System.out.println(MovieService.getInstance().getMovieByAverageRating(3));

	}

}
