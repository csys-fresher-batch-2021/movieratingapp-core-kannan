package exception;

public class ServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7341317642603196756L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e, String message) {
		super(message, e);
	}

}
