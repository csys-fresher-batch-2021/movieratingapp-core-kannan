package in.kannan.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserRatingReader {
	public static void main(String[] args) throws IOException {
		BufferedReader buf = new BufferedReader(new FileReader("User Rating.text"));
		String line = buf.readLine();
		while (line != null) {
			System.out.println(line);
			line = buf.readLine();
		}
		buf.close();

	}

}
