package in.kannan.exception;

public class ConnectionException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1926003270993726678L;

	/**
	 * exception thrown during connection
	 * 
	 * @param message the message to be thrown
	 */

	public ConnectionException(String message) {
		super(message);
	}

	/**
	 * exception to be thrown during connection
	 * 
	 * @param e       cached exception type
	 * @param message the message to be thrown
	 */

	public ConnectionException(Throwable e, String message) {
		super(message, e);
	}

}
