import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Day_5_Alchemical_Reduction {

	private static String stockUp_String(Scanner sc) {
		String inputStr = "";
		while (sc.hasNextLine()) {
			inputStr += sc.nextLine();
		}
		return inputStr;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_5.txt");
		Scanner sc = new Scanner(file);
		sc.useDelimiter("\\Z");
		String polymerBuild = stockUp_String(sc);
		System.out.println(polymerBuild.length());
		System.out.println("Unit1: " + polymerBuild.charAt(11111));
		

	}

}
