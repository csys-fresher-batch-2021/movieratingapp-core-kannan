package in.kannan.validator;

import in.kannan.exception.ValidationException;

public class MovieValidatorTest {
	public static void main(String[] args) throws ValidationException {
	//	System.out.print(MovieValidator.isValidMovieName("R.Kannan"));
		String date ="2000-05-12";
		String dateType2 = "12-04-23";
		String movieName="Msater";
		String movieName2="#3nn";

	//	System.out.println(MovieValidator.isValidDate(validDate));
	//	MovieValidator.validateMovie(movieName, validDate);
		MovieValidator.validateMovie(movieName, "");

		
	}

}
