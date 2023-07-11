/* Alan Tong
 * 
 */

package part3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Part3 {

	static PrintWriter writer = null;
	
	public static void main(String[] args) {
		
		try { 
			writer = new PrintWriter("diff.txt");
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		String input1String = "";
		String input2String = "";
		
		
		File file2 = new File("input2.txt");
		File file1 = new File("input1.txt");
		Scanner s2;
		Scanner s1;
		
		try {
			s2 = new Scanner(file2);
			s1 = new Scanner(file1);
			while(s2.hasNextLine() && s1.hasNextLine() ) {
				input2String = s2.nextLine();
				input1String = s1.nextLine();
				if(input1String.equals(input2String)) {
					writer.write("Equals" + System.getProperty( "line.separator" ));
				} else {
					writer.write("Not Equals" + System.getProperty( "line.separator" ));
				}
			}
			s2.close();
			s1.close();
			writer.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
	}

}
