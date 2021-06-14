package in.kannan.dao;

import in.kannan.exception.DBException;

public class MovieDAOTest {
	public static void main(String[] args) throws DBException {
		MovieRatingDAO.remove(10);
	}

}
