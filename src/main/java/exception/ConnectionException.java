package exception;

public class ConnectionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1926003270993726678L;

	public ConnectionException(String message) {
		super(message);
	}

	public ConnectionException(Exception e, String message) {
		super(message, e);
	}

}
