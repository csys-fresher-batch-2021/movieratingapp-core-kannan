package in.kannan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.kannan.dto.MovieRatingDTO;
import in.kannan.exception.ConnectionException;
import in.kannan.exception.DBException;
import in.kannan.model.MovieRating;
import in.kannan.model.UserRating;
import in.kannan.util.ConnectionUtil;
import in.kannan.util.Logger;
import in.kannan.util.MessageDisplay;

public class UserRatingDAO {
	private UserRatingDAO() {
		// private constructor to hide the implicit class
	}

	/**
	 * This method saves the user rating.
	 * 
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @throws DBException
	 */

	public static void save(Integer userId, Integer movieId, Integer rating) throws DBException {

		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "insert into user_ratings(user_id,movie_id,rating) values (?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, userId);
			pst.setInt(2, movieId);

			pst.setInt(3, rating);

			exist(pst);

		} catch (SQLException e) {

			Logger.trace(e);
			throw new DBException(MessageDisplay.SAVERATINGERROR);
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}

	/**
	 * This method saves the movie_id .
	 * 
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @throws DBException
	 */

	public static void saveMovieId(Integer movieId) throws DBException {

		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "insert into user_ratings(movie_id) values (?)";
			pst = connection.prepareStatement(sql);

			pst.setInt(1, movieId);

			pst.execute();

		} catch (SQLException e) {

			Logger.trace(e);
			throw new DBException(MessageDisplay.SAVEERROR);
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}

	/**
	 * check the proper insertion into table.
	 * 
	 * @param ps
	 * @return
	 * @throws DBException
	 */

	public static void exist(PreparedStatement ps) throws DBException {

		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(MessageDisplay.DATAERROR);
		}

	}

	/**
	 * This method returns the average rating for the particular movie id from
	 * database.
	 * 
	 * @return rating list
	 * @throws DBException
	 */

	public static List<MovieRating> findAverageRating() throws DBException {
		List<MovieRating> ratingList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select movie_id,avg(rating) as average_rating from user_ratings where active = true  group by movie_id";
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("movie_id");
				double rating = rs.getDouble("average_rating");

				MovieRating movieRating = new MovieRating(id, rating);
				ratingList.add(movieRating);

			}
		} catch (SQLException e) {

			Logger.trace(e);
			throw new DBException(MessageDisplay.FINDERROR);
		} finally {
			ConnectionUtil.close(rs, pst, connection);

		}
		return ratingList;

	}

	/**
	 * This method finds the data for the particular user id and movie id from
	 * database.
	 * 
	 * @param userId
	 * @param movieId
	 * @return UserRating object as userRating
	 * @throws DBException
	 */

	public static UserRating findUserIdByUserIdAndMovieId(Integer userId, Integer movieId) throws DBException {
		UserRating userRating = null;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select user_id from user_ratings where user_id = ? and movie_id =?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, userId);
			pst.setInt(2, movieId);

			rs = pst.executeQuery();
			if (rs.next()) {
				Integer id = rs.getInt("user_Id");
				userRating = new UserRating(id);
			}

		} catch (ConnectionException | SQLException e) {

			Logger.trace(e);
			throw new DBException(MessageDisplay.FINDERROR);
		} finally {
			ConnectionUtil.close(pst, connection);

		}
		return userRating;

	}

	/**
	 * This method removes particular data for particular id from database.
	 * 
	 * @param movieId
	 * @throws DBException
	 */

	public static void remove(Integer movieId) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "delete from user_ratings where movie_id =?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, movieId);
			pst.executeUpdate();

		} catch (ConnectionException | SQLException e) {
			Logger.trace(e);
			throw new DBException(MessageDisplay.DELETEERROR);
		} finally {
			ConnectionUtil.close(pst, connection);

		}

	}

	/**
	 * This method removes a particular data for the given movie id.
	 * 
	 * @param userId
	 * @param movieId
	 * @throws DBException
	 */

	public static void removeByUserIdAndMovieId(Integer userId, Integer movieId) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "delete from user_ratings where user_id=? and movie_id =?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, userId);
			pst.setInt(2, movieId);
			pst.executeUpdate();

		} catch (ConnectionException | SQLException e) {
			Logger.trace(e);
			throw new DBException(MessageDisplay.DELETEERROR);
		} finally {
			ConnectionUtil.close(pst, connection);

		}

	}

	/**
	 * This method counts the number of users rated for the particular movie id
	 * 
	 * @param movieId
	 * @return counting of users rated
	 * @throws DBException
	 */

	public static Integer countRatingByMovieId(Integer movieId) throws DBException {
		Integer count = null;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select count(movie_id) as users_rated from user_ratings where movie_id = ? and active = true";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, movieId);
			rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt("users_rated");

			}
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(MessageDisplay.COUNTERROR);
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return count;
	}

	/**
	 * This method counts number of user rated for the particular movie and
	 * particular rating
	 * 
	 * @param rating
	 * @param movieId
	 * @return
	 * @throws DBException
	 */

	public static Integer countRatingByRatingAndMovieId(Integer rating, Integer movieId) throws DBException {
		Integer count = null;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select count(rating) from user_ratings where rating =? and movie_id=?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, rating);
			pst.setInt(2, movieId);
			rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");

			}
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(MessageDisplay.COUNTERROR);
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return count;
	}

	/**
	 * This method counts the number of user rated for particular rating for the
	 * particular movie.Input is the movie name for which each rating and number of
	 * user rated is calculated.
	 * 
	 * @param movieId
	 * @return
	 * @throws DBException
	 */

	public static List<MovieRatingDTO> countRatingByMovieIdOrderByRatingDesc(Integer movieId) throws DBException {
		List<MovieRatingDTO> counting = new ArrayList<>();
		MovieRatingDTO counts = null;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select rating ,count(rating) from user_ratings where movie_id=? and active = true group by rating order by rating desc ";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, movieId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Double rating = rs.getDouble("rating");
				Integer count = rs.getInt("count");
				counts = new MovieRatingDTO(rating, count);
				counting.add(counts);
			}
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(MessageDisplay.COUNTERROR);
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return counting;
	}

	/**
	 * This method updates the active column of user rating to false .
	 * 
	 * @param userId
	 * @throws DBException
	 */

	public static void updateActiveByUserId(Integer userId) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "update user_ratings set active = false where user_id = ?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, userId);
			int r = pst.executeUpdate();

			Logger.message(MessageDisplay.UPDATEMESSAGE + r + " rows");

		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(MessageDisplay.UPDATEERROR);
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}
}
