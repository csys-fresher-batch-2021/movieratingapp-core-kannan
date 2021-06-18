package in.kannan.validator;

import in.kannan.exception.ValidationException;

public class MovieValidator {
	private MovieValidator() {

	}

	/**
	 * This method validates the movie name avoids special characters, numbers,null
	 * value,white space and length of the movie name must not exceed 30 characters.
	 * 
	 * @param movieName
	 * @return boolean value
	 */

	public static boolean isValidMovieName(String movieName) {
		boolean isValid = false;

		if (movieName != null) {
			isValid = true;

			if (movieName.trim().equals("") || movieName.length() > 30) {
				isValid = false;
			}

			String exceptions = "!@#$%^&*()_+},=-`~{:1234567890?/><";

			for (int i = 0; i < movieName.trim().length(); i++) {
				char c = movieName.trim().charAt(i);
				for (int j = 0; j < exceptions.length(); j++) {
					char d = exceptions.charAt(j);
					if (c == d) {
						isValid = false;
					}
				}
			}

		}
		return isValid;

	}

	/**
	 * This method validates date whether it is not null.
	 * 
	 * @param date
	 * @return boolean value
	 */

	public static boolean isValidDate(String date) {
		boolean isValid = false;
		if (date != null && !date.equals("")) {
			isValid = true;

		}
		return isValid;

	}

	/**
	 * This method validates both the movie name and the date.Throws exception if
	 * the data's are incorrect.
	 * 
	 * @param movieName
	 * @param date
	 * @throws ValidationException
	 */

	public static void validateMovie(String movieName, String date) throws ValidationException {

		if (!isValidMovieName(movieName)) {
			throw new ValidationException("Invalid Movie Name ");
		}
		if (!isValidDate(date)) {
			throw new ValidationException("Please fill the date");
		}
	}

	/**
	 * This method validates the movie name .Throws exception if the given data is
	 * incorrect.
	 * 
	 * @param name
	 * @throws ValidationException
	 */

	public static void validateMovieName(String name) throws ValidationException {

		if (!isValidMovieName(name)) {
			throw new ValidationException("Invalid Movie Name ");
		}

	}

}
