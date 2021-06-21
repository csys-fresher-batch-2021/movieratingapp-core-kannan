package in.kannan.dao;

import java.util.List;

import in.kannan.exception.DBException;
import in.kannan.model.Movie;

public class MovieDAOTest {
	public static void main(String[] args) throws DBException {
		// MovieRatingDAO.remove(10);
		// MovieDAO.findAllWithRating();
		List<Movie> movieList = MovieDAO.findAllExceptRatings();
	//	System.out.println(movieList);
		for (Movie movie : movieList) {
			System.out.println(movie);
		}
		System.out.println(MovieDAO.findAllExceptRatings());

		//System.out.println(MovieDAO.findByMovieId("Karnan"));
	}

}
