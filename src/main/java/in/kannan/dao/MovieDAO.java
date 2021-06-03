package in.kannan.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import in.kannan.exception.DBException;
import in.kannan.model.Movie;
import in.kannan.util.ConnectionUtil;

public class MovieDAO {
	private MovieDAO() {

	}

	/**
	 * returns the movie detail
	 * 
	 * @return movie details as list
	 * @throws DBException due to failure in DAO
	 */

	public static List<Movie> findAll() throws DBException {
		List<Movie> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select movie_id,movie_name,release_date,status,end_date from movies order by movie_id";
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("movie_id");
				String name = rs.getString("movie_name");
				Date releaseDate = rs.getDate("release_date");
				LocalDate getStartDate = releaseDate.toLocalDate();
				boolean active = rs.getBoolean("status");
				Date endDate = rs.getDate("end_date");
				if (endDate != null) {
					LocalDate getEndDate = endDate.toLocalDate();
					Movie mov = new Movie(id, name, getStartDate, active, getEndDate);
					list.add(mov);

				} else {
					Movie movi = new Movie(id, name, getStartDate);
					list.add(movi);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DBException(e, "Unable to display movies");
		} finally {
			ConnectionUtil.close(rs, pst, connection);

		}
		return list;

	}

}
