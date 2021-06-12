package in.kannan.validator;

import in.kannan.exception.ValidationException;

public class RatingValidatorTest {
	public static void main(String[] args) throws ValidationException {
		RatingValidator.validateRating(-1,2,3);
	}

}
