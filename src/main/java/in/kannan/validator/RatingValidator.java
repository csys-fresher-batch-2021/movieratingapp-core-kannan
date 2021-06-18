package in.kannan.validator;

import in.kannan.exception.ValidationException;

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
		if (rating > 0 && rating <= 10) {
			isValid = true;
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
			throw new ValidationException("Invalid user Id");
		} else if (!isValidId(movieId)) {
			throw new ValidationException("Invalid movie Id");
		} else if (!isValidRating(rating)) {
			throw new ValidationException("Rating should be between 1 and 10");
		}
	}

}
