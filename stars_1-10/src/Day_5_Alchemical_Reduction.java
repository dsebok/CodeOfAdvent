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
	
	private static String delChars(String inputStr, int delStart, int delEnd) {
		return inputStr.substring(0, delStart) + inputStr.substring(delEnd + 1, inputStr.length());
	}
	
	private static int iReduce(int i) {
		if (i == 0) {
			return -1;
		} else {
			return i-2;
		}
	}
	
	private static String unitReaction(String polymerBuild) {
		char firstChar;
		char secondChar;
		int i = 0;
		while (i < polymerBuild.length()-1) {
			firstChar = polymerBuild.charAt(i);
			secondChar = polymerBuild.charAt(i + 1);
			if (areOpposites(firstChar, secondChar)) {
				polymerBuild = delChars(polymerBuild, i, i+1);
				i = iReduce(i);
			}
			++i;
		}
		return polymerBuild;
	}
	
	private static String delAllChar(String polymerBuild, char xChar) {
		int i = -1;
		while (++i < polymerBuild.length()) {
			//System.out.println(i);
			char currentChar = polymerBuild.charAt(i);
			if (currentChar == xChar || currentChar == Character.toUpperCase(xChar)) {
				polymerBuild = delChars(polymerBuild, i, i);
				--i;
			}
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
		
		polymerBuild = unitReaction(polymerBuild);
		System.out.println(polymerBuild.length());
		
		String abc = "abcdefghijklmnopqrstuvwxyz";
		polymerBuild = delAllChar(polymerBuild, abc.charAt(23));
		System.out.println(polymerBuild.length());

	}
}
