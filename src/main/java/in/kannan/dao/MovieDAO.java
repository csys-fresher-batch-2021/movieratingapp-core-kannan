package in.kannan.dao;

import java.util.List;

import in.kannan.dto.MovieRatingCountDTO;
import in.kannan.exception.DBException;
import in.kannan.model.Movie;
import in.kannan.model.MovieRating;

public interface MovieDAO {

	/**
	 * This method returns the movie details as list.
	 * 
	 * @return movie details as list
	 * @throws DBException
	 */

	List<Movie> findAll() throws DBException;

	/**
	 * This method checks the presence of movie.
	 * 
	 * @param movieName
	 * @return
	 * @throws DBException
	 */

	Movie findMovieNameByExactMovieName(String movieName) throws DBException;

	/**
	 * This method save the movie detail .
	 * 
	 * @param movieName
	 * @param releaseDate
	 * @param status
	 * @param id
	 * @throws DBException
	 */

	void save(Movie movie) throws DBException;

	/**
	 * This method returns the movie id for the particular movie name.
	 * 
	 * @param movieName
	 * @return
	 * @throws DBException
	 */

	Integer findMovieIdByMovieName(String movieName) throws DBException;

	/**
	 * This method removes particular movie details from database by using their Id.
	 * 
	 * @param movieName
	 * @throws DBException
	 */

	void remove(Integer movieId) throws DBException;

	/**
	 * This method displays the entire details of the movie along with ratings
	 * 
	 * @return list which holds all the details
	 * @throws DBException
	 */

	List<MovieRating> findAllOrderByAverageRatingDesc() throws DBException;

	/**
	 * This method returns the movie detail along rating for the input of movie Id
	 * 
	 * @param movieId
	 * @return
	 * @throws DBException
	 */

	MovieRating findAllByMovieId(Integer movieId) throws DBException;

	/**
	 * This method returns all the details of the movie including average rating.
	 * 
	 * @return
	 * @throws DBException
	 */

	List<MovieRating> findAllMovieRating() throws DBException;

	/**
	 * This method returns the number of users rated along with the list of movies
	 * above the given rating value
	 * 
	 * @param rating
	 * @return list containing movie's rated
	 * @throws DBException
	 */

	List<MovieRatingCountDTO> findMovieRatingByRating(Integer rating) throws DBException;

	/**
	 * This method sorts according to average rating and returns the movie details
	 * along with the average rating to user
	 * 
	 * @param rating
	 * @return sorted list of movies
	 * @throws DBException
	 */

	List<MovieRating> findMovieByAverageRating(Integer averageRating) throws DBException;

}