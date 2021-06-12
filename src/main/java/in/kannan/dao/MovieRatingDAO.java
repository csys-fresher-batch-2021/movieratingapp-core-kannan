package in.kannan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.kannan.exception.ConnectionException;
import in.kannan.exception.DBException;
import in.kannan.exception.ServiceException;
import in.kannan.model.MovieRating;
import in.kannan.model.UserRating;
import in.kannan.service.RatingService;
import in.kannan.util.ConnectionUtil;
import in.kannan.util.Logger;

public class MovieRatingDAO {
	private MovieRatingDAO() {
		// private constructor to hide implicit class
	}

	/**
	 * returns the object with data if rated else null
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
	 * inserts the details into rating_by_user table
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
			String sql = "insert into rating_by_user values (?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, userId);
			pst.setInt(2, movieId);

			pst.setInt(3, rating);

			int r = exist(pst);

			if (r == 1) {
				RatingService.updateRating(movieId);
			}

		} catch (SQLException | ServiceException e) {

			Logger.trace(e);
			throw new DBException("Failed to insert in table");
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}

	/**
	 * updates the movie_rating table
	 * 
	 * @param rating
	 * @param movieId
	 * @throws DBException
	 */

	public static void update(Double rating, Integer movieId) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "update movie_rating set rating = ? where movie_id =?";
			pst = connection.prepareStatement(sql);
			pst.setDouble(1, rating);
			pst.setInt(2, movieId);
			pst.executeUpdate();
		} catch (ConnectionException | SQLException e) {

			Logger.trace(e);
			throw new DBException("Failed to update in table");
		} finally {
			ConnectionUtil.close(pst, connection);

		}

	}

	/**
	 * check the proper insertion into table else throws exception
	 * 
	 * @param ps
	 * @return
	 * @throws DBException
	 */

	public static int exist(PreparedStatement ps) throws DBException {
		int r = 0;

		try {
			r = ps.executeUpdate();
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException("Data's not found");
		}
		return r;

	}

	/**
	 * returns the average rating along with movie id
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
			throw new DBException(e, "Failed to select the details");
		} finally {
			ConnectionUtil.close(rs, pst, connection);

		}
		return ratingList;

	}

}
