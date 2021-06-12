package in.kannan.validator;

import in.kannan.exception.ValidationException;

public class RatingValidator {
	private RatingValidator() {
		// private constructor to hide the existing class
	}

	/**
	 * validates the user Id
	 * 
	 * @param userId
	 * @return boolean data
	 */

	public static boolean isValidId(Integer userId) {
		boolean isValid = false;
		if (userId > 0) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * validates the user rating
	 * 
	 * @param rating
	 * @return boolean data
	 */

	public static boolean isValidRating(Integer rating) {
		boolean isValid = false;
		if (rating > 0 && rating < 11) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * validates user input and throws exception
	 * 
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @throws ValidationException
	 */

	public static void validateRating(Integer userId, Integer movieId, Integer rating) throws ValidationException {
		if (!isValidId(userId)) {
			throw new ValidationException("Invalid user Id");
		} else if (!isValidId(movieId)) {
			throw new ValidationException("Invalid movie Id");
		} else if (!isValidRating(rating)) {
			throw new ValidationException("Rating should be between 1 and 10");
		}
	}

}
