package in.kannan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.kannan.dao.UserRatingDAO;
import in.kannan.dto.MovieRatingDTO;
import in.kannan.exception.ConnectionException;
import in.kannan.exception.DBException;
import in.kannan.model.MovieRating;
import in.kannan.model.UserRating;
import in.kannan.util.ConnectionUtil;
import in.kannan.util.Logger;
import in.kannan.util.MessageConstant;

public class UserRatingDAOImpl implements UserRatingDAO {

	private static final String MOVIE_ID = "movie_id";
	private static final String AVERAGE_RATING = "average_rating";
	private static final String USER_ID = "user_Id";
	private static final String USER_RATED = "users_rated";
	private static final String COUNT = "count";
	private static final String RATINGS = "rating";

	/**
	 * This method saves the user rating.
	 * 
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @throws DBException
	 */
	@Override
	public void save(Integer userId, Integer movieId, Integer rating) throws DBException {

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
			throw new DBException(e, MessageConstant.UNABLE_SAVE_USER_RATINGS);
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
	@Override
	public void saveMovieId(Integer movieId) throws DBException {

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
			throw new DBException(e, MessageConstant.UNABLE_SAVE_USER_RATINGS);
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
	@Override
	public void exist(PreparedStatement ps) throws DBException {

		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(MessageConstant.DATAERROR);
		}

	}

	/**
	 * This method returns the average rating for the particular movie id from
	 * database.
	 * 
	 * @return rating list
	 * @throws DBException
	 */
	@Override
	public List<MovieRating> findAverageRating() throws DBException {
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
				Integer id = rs.getInt(MOVIE_ID);
				double rating = rs.getDouble(AVERAGE_RATING);

				MovieRating movieRating = new MovieRating(id, rating);
				ratingList.add(movieRating);

			}
		} catch (SQLException e) {

			Logger.trace(e);
			throw new DBException(e, MessageConstant.UNABLE_FIND_USER_ID);
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
	@Override
	public UserRating findUserIdByUserIdAndMovieId(Integer userId, Integer movieId) throws DBException {
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
				Integer id = rs.getInt(USER_ID);
				userRating = new UserRating(id);
			}

		} catch (ConnectionException | SQLException e) {

			Logger.trace(e);
			throw new DBException(e, MessageConstant.UNABLE_FIND_USER_ID);
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
	@Override
	public void remove(Integer movieId) throws DBException {
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
			throw new DBException(e, MessageConstant.UNABLE_DELETE_MOVIE);
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
	@Override
	public void removeByUserIdAndMovieId(Integer userId, Integer movieId) throws DBException {
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
			throw new DBException(e, MessageConstant.UNABLE_DELETE_USER_RATINGS);
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
	@Override
	public Integer countRatingByMovieId(Integer movieId) throws DBException {
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
				count = rs.getInt(USER_RATED);

			}
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(e, MessageConstant.UNABLE_COUNT_MOVIE);
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
	@Override
	public Integer countRatingByRatingAndMovieId(Integer rating, Integer movieId) throws DBException {
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
				count = rs.getInt(COUNT);

			}
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(e, MessageConstant.UNABLE_COUNT_MOVIE);
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
	@Override
	public List<MovieRatingDTO> countRatingByMovieIdOrderByRatingDesc(Integer movieId) throws DBException {
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
				Double rating = rs.getDouble(RATINGS);
				Integer count = rs.getInt(COUNT);
				counts = new MovieRatingDTO(rating, count);
				counting.add(counts);
			}
		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(e, MessageConstant.UNABLE_COUNT_MOVIE);
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
	@Override
	public void update(Integer userId) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "update user_ratings set active = false where user_id = ?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, userId);
			int rows = pst.executeUpdate();

			Logger.message(MessageConstant.UPDATEMESSAGE + rows + " rows");

		} catch (SQLException e) {
			Logger.trace(e);
			throw new DBException(e, MessageConstant.UNABLE_UPDATE_USER_RATINGS);
		} finally {
			ConnectionUtil.close(pst, connection);
		}

	}
}
