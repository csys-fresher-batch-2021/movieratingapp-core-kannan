package in.kannan.util;

public class Logger {
	private Logger() {
		// private constructor to hide the implicit class
	}

	/**
	 * prints the object toString fuction in the console
	 * 
	 * @param obj
	 */
	public static void log(Object obj) {
		System.out.println(obj);
	}

}
