package in.kannan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.kannan.exception.ConnectionException;
import in.kannan.exception.DBException;
import in.kannan.util.ConnectionUtil;
import in.kannan.util.Logger;

public class MovieRatingDAO {
	private MovieRatingDAO() {
		// private constructor to hide implicit class
	}

	public static void save(Integer movieId) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "insert into movie_rating (movie_id) values (?)";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, movieId);
			pst.executeUpdate();

		} catch (ConnectionException | SQLException e) {
			Logger.trace(e);
			throw new DBException("Failed to insert into the table ");
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
	 * This table removes the particular movie in movie_rating table in database
	 * 
	 * @param movieId this id gets removed
	 * @throws DBException
	 */

	public static void remove(Integer movieId) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "delete from movie_rating where movie_id =?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, movieId);
			pst.executeUpdate();

		} catch (ConnectionException | SQLException e) {
			Logger.trace(e);
			throw new DBException("Failed to delete the movie in movie_rating table ");
		} finally {
			ConnectionUtil.close(pst, connection);

		}

	}

}
