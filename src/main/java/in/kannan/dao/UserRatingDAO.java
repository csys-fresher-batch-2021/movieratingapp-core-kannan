package in.kannan.dao;

import java.sql.PreparedStatement;
import java.util.List;

import in.kannan.dto.MovieRatingDTO;
import in.kannan.exception.DBException;
import in.kannan.model.MovieRating;
import in.kannan.model.UserRating;

public interface UserRatingDAO {
	/**
	 * This method saves the user rating.
	 * 
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @throws DBException
	 */

	void save(Integer userId, Integer movieId, Integer rating) throws DBException;

	/**
	 * This method saves the movie_id .
	 * 
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @throws DBException
	 */

	void saveMovieId(Integer movieId) throws DBException;

	/**
	 * check the proper insertion into table.
	 * 
	 * @param ps
	 * @return
	 * @throws DBException
	 */

	void exist(PreparedStatement ps) throws DBException;

	/**
	 * This method returns the average rating for the particular movie id from
	 * database.
	 * 
	 * @return rating list
	 * @throws DBException
	 */

	List<MovieRating> findAverageRating() throws DBException;

	/**
	 * This method finds the data for the particular user id and movie id from
	 * database.
	 * 
	 * @param userId
	 * @param movieId
	 * @return UserRating object as userRating
	 * @throws DBException
	 */

	UserRating findUserIdByUserIdAndMovieId(Integer userId, Integer movieId) throws DBException;

	/**
	 * This method removes particular data for particular id from database.
	 * 
	 * @param movieId
	 * @throws DBException
	 */

	void remove(Integer movieId) throws DBException;

	/**
	 * This method removes a particular data for the given movie id.
	 * 
	 * @param userId
	 * @param movieId
	 * @throws DBException
	 */

	void removeByUserIdAndMovieId(Integer userId, Integer movieId) throws DBException;

	/**
	 * This method counts the number of users rated for the particular movie id
	 * 
	 * @param movieId
	 * @return counting of users rated
	 * @throws DBException
	 */

	Integer countRatingByMovieId(Integer movieId) throws DBException;

	/**
	 * This method counts number of user rated for the particular movie and
	 * particular rating
	 * 
	 * @param rating
	 * @param movieId
	 * @return
	 * @throws DBException
	 */

	Integer countRatingByRatingAndMovieId(Integer rating, Integer movieId) throws DBException;

	/**
	 * This method counts the number of user rated for particular rating for the
	 * particular movie.Input is the movie name for which each rating and number of
	 * user rated is calculated.
	 * 
	 * @param movieId
	 * @return
	 * @throws DBException
	 */

	List<MovieRatingDTO> countRatingByMovieIdOrderByRatingDesc(Integer movieId) throws DBException;

	/**
	 * This method updates the active column of user rating to false .
	 * 
	 * @param userId
	 * @throws DBException
	 */

	void update(Integer userId) throws DBException;

}