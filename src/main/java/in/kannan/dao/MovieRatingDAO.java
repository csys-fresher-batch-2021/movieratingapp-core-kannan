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

	/**
	 * This method saves the data into the database
	 * 
	 * @param movieId
	 * @throws DBException
	 */

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
			throw new DBException("Failed to save the data ");
		} finally {
			ConnectionUtil.close(pst, connection);

		}

	}

	/**
	 * This method updates the data into the database for the given id.
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
	 * This table removes the particular movie in the database for the given id.
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
			throw new DBException("Failed to delete the movie  ");
		} finally {
			ConnectionUtil.close(pst, connection);

		}

	}

}
