package in.kannan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.kannan.exception.ConnectionException;
import in.kannan.exception.DBException;
import in.kannan.model.MovieRating;
import in.kannan.model.UserRating;
import in.kannan.util.ConnectionUtil;
import in.kannan.util.Logger;

public class UserRatingDAO {
	private UserRatingDAO() {
		// private constructor to hide the implicit class
	}

	/**
	 * This method saves the data into the database.
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
			String sql = "insert into rating_by_user(user_id,movie_id,rating) values (?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, userId);
			pst.setInt(2, movieId);

			pst.setInt(3, rating);

			exist(pst);

		} catch (SQLException e) {

			Logger.trace(e);
			throw new DBException("Failed to save the data");
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}

	/**
	 * This method saves the data into the database.
	 * 
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @throws DBException
	 */

	public static void addMovieId(Integer movieId) throws DBException {

		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "insert into rating_by_user(movie_id) values (?)";
			pst = connection.prepareStatement(sql);

			pst.setInt(1, movieId);

			pst.execute();

		} catch (SQLException e) {

			Logger.trace(e);
			throw new DBException("Failed to save the data");
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
			throw new DBException("Data's not found");
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
			String sql = "select movie_id,avg(rating) as average_rating from rating_by_user group by movie_id";
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
			throw new DBException(e, "Failed to fetch the data");
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

	public static UserRating exist(Integer userId, Integer movieId) throws DBException {
		UserRating userRating = null;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select user_id from rating_by_user where user_id = ? and movie_id =?";
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
			throw new DBException("Unable to fetch the details");
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
			String sql = "delete from rating_by_user where movie_id =?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, movieId);
			pst.executeUpdate();

		} catch (ConnectionException | SQLException e) {
			Logger.trace(e);
			throw new DBException("Failed to delete the id ");
		} finally {
			ConnectionUtil.close(pst, connection);

		}

	}

	/**
	 * This method removes a particular data under the given movie id and user id
	 * 
	 * @param userId
	 * @param movieId
	 * @throws DBException
	 */

	public static void remove(Integer userId, Integer movieId) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "delete from rating_by_user where user_id=? and movie_id =?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, userId);
			pst.setInt(2, movieId);
			pst.executeUpdate();

		} catch (ConnectionException | SQLException e) {
			Logger.trace(e);
			throw new DBException("Failed to delete the data ");
		} finally {
			ConnectionUtil.close(pst, connection);

		}

	}

}
