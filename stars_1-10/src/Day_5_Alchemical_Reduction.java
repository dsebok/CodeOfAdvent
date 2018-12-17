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
	
	private static String unitReaction(String polymerBuild) {
		char firstChar;
		char secondChar;
		int i = 0;
		while (i < polymerBuild.length()-1) {
			firstChar = polymerBuild.charAt(i);
			secondChar = polymerBuild.charAt(i + 1);
			if (areOpposites(firstChar, secondChar)) {
				polymerBuild = polymerBuild.substring(0, i) + polymerBuild.substring(i+2, polymerBuild.length());
				if (i == 0) {
					i = i-1;
				} else {
					i = i-2;
				}
			}
			++i;
		}
		return polymerBuild;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_5.txt");
		Scanner sc = new Scanner(file);
		sc.useDelimiter("\\Z");
		String polymerBuild = stockUp_String(sc);
		
		String inputStr = "dabAcCaCBAcCcaDA";
		String unit1 = "dabCBAcaDA";
		inputStr = unitReaction(inputStr);
		System.out.println("Unit test 1: " + inputStr.equals(unit1));

	}
}
