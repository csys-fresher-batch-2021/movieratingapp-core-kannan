package in.kannan.validator;

import in.kannan.exception.ValidationException;
import in.kannan.util.MessageConstant;

public class RatingValidator {
	private RatingValidator() {
		// private constructor to hide the existing class
	}

	/**
	 * Validates the user Id,it should be greater than 0.
	 * 
	 * @param id
	 * @return boolean data
	 */

	public static boolean isValidId(Integer id) {
		boolean isValid = false;
		if (id > 0) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * This method validates the user ratings, validates whether rating is in above
	 * 0 and below or equal to 10.
	 * 
	 * @param rating
	 * @return boolean data
	 */

	public static boolean isValidRating(Integer rating) {
		boolean isValid = false;
		if (rating > 0 && rating <= 5) {
			isValid = true;
		}
		return isValid;
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
	 * Validates user input.If invalid throws exception
	 * 
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @throws ValidationException
	 */

	public static void validateRating(Integer userId, Integer movieId, Integer rating) throws ValidationException {
		if (!isValidId(userId)) {
			throw new ValidationException(MessageConstant.INVALIDUSERID);
		} else if (!isValidId(movieId)) {
			throw new ValidationException(MessageConstant.INVALIDMOVIEID);
		} else if (!isValidRating(rating)) {
			throw new ValidationException(MessageConstant.INVALIDRATING);
		}
	}

	/**
	 * Validates user input.If invalid throws exception
	 * 
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @throws ValidationException
	 */

	public static void validateRating(Integer rating, String movieName) throws ValidationException {
		if (!isValidRating(rating)) {
			throw new ValidationException(MessageConstant.INVALIDRATING);
		} else if (!isValidMovieName(movieName)) {
			throw new ValidationException(MessageConstant.INVALIDMOVIENAME);
		}
	}

	/**
	 * Validates user input.If invalid throws exception
	 * 
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @throws ValidationException
	 */

	public static void validateRating(Integer rating) throws ValidationException {
		if (!isValidRating(rating)) {
			throw new ValidationException(MessageConstant.INVALIDRATING);
		}
	}

	/**
	 * This method validates the userId
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void validateId(Integer id) throws ValidationException {
		if (!isValidId(id)) {
			throw new ValidationException(MessageConstant.INVALIDUSERID);
		}
	}

}
