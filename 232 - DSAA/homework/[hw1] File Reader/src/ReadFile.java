/*
 * Alan Tong
 * CSCI 232 Homework1 Part 3 - Reading from a file
 * Summer 2018
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {


	public static void main(String[] args) {
		File file = new File("input.txt");
		Scanner s;
		try {
			s = new Scanner(file);
			while(s.hasNext()) {
				String str = s.next();
				System.out.println(str);
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}
}
