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
	
	private static String unitReaction(String polymerBuild) {
		return " ";
	}
	
	private static Boolean areOpposites(char ch1, char ch2) {
		Boolean secondIsLower = Character.toLowerCase(ch1) == ch2;
		Boolean secondIsUpper = Character.toUpperCase(ch1) == ch2;
		
		Boolean firstIsLower = Character.toLowerCase(ch2) == ch1;
		Boolean firstIsUpper = Character.toUpperCase(ch2) == ch1;
		if (firstIsLower && secondIsUpper) {
			return true;
		} else if (firstIsUpper && secondIsLower) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_5.txt");
		Scanner sc = new Scanner(file);
		sc.useDelimiter("\\Z");
		String polymerBuild = stockUp_String(sc);
		System.out.println(polymerBuild.length());
		System.out.println("Unit1: " + areOpposites('c', 'C'));
		System.out.println("Unit1: " + areOpposites('C', 'c'));
		System.out.println("Unit1: " + areOpposites('c', 'c'));
		System.out.println("Unit1: " + areOpposites('C', 'C'));
		

	}

}
