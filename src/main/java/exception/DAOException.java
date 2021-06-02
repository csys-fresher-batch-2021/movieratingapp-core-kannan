package exception;

public class DAOException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6988309934978050586L;

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Exception e, String message) {
		super(message, e);

	}

}
