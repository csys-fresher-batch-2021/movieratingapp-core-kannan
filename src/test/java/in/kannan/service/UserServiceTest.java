package in.kannan.service;

import in.kannan.exception.ServiceException;
import in.kannan.exception.ValidationException;

public class UserServiceTest {
	public static void main(String[] args) throws ServiceException, ValidationException {
		// System.out.println(UserService.adminLogin("kannanramesh@gmail.com",
		// "fre3232SFE2#"));

		// Checks the admin Login
		// System.out.println(UserService.adminLogin("kannanraemes@hgmail.com",
		// "fre3232SFE2#"));
		System.out.println(UserService.adminLogin("subra245@gmail.com", "@#34asDEw"));
		// System.out.println(UserService.adminLogin("naraya@live.com",
		// "jefien344@##AAS"));

		// This method checks the user Login
		// System.out.println(UserService.userLogin("ganesh34@yahoo.com",
		// "67awVEsf!@f"));

		// This method registers the particular user.
		// UserService.userRegistration("Kumerasan", "kumar1234@gmail.com",
		// "as12AS@#Dwe", "USER");

		// UserService.userRegistration("Vinoth", "vino123@gmail.com", "ASaf12@#jK!2",
		// "ADMIN");

		// UserService.blockUser(5);

	}

}
