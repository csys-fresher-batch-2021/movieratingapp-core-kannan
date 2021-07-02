package in.kannan.dao;

import java.util.List;

import in.kannan.dao.impl.MovieDAOImpl;
import in.kannan.exception.DBException;
import in.kannan.model.Movie;

public class MovieDAOTest {
	public static void main(String[] args) throws DBException {

		MovieDAO movieDAO = new MovieDAOImpl();

		movieDAO.remove(10);
		movieDAO.findAllMovieRatingOrderByReleaseDate();
		List<Movie> movieList = movieDAO.findAll();
		System.out.println(movieList);
		for (Movie movie : movieList) {
			System.out.println(movie);
		}
		System.out.println(movieDAO.findAll());
		System.out.println(movieDAO.findAllByMovieId(3));
		System.out.println(movieDAO.findAllMovieRatingOrderByReleaseDate());
		System.out.println(movieDAO.findAllOrderByAverageRatingDesc());
		System.out.println(movieDAO.findMovieIdByMovieName("Master"));

	}

}
