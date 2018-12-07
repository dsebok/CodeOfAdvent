import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_2_InventoryManagementSys {

	private static void populate_list(Scanner sc, ArrayList<String> aList) {
		while (sc.hasNextLine()) {
			aList.add(sc.nextLine());
		}
	}
	
	private static int[] charCounter(ArrayList<String> aList) {
		int[] counter = {0,0};
		for (String ID_nr: aList) {
			boolean doubleEvent = false;
			boolean tripleEvent = false;
			while (ID_nr.length() > 1) {
				int charCounter = 1;
				for (int j = 1; j < ID_nr.length(); ++j) {
					if (ID_nr.charAt(0) == ID_nr.charAt(j)) {
						++charCounter;
					}
				}
				String currentChar = String.valueOf(ID_nr.charAt(0));
				ID_nr = ID_nr.replaceAll(currentChar, "");
				if (charCounter == 2 && doubleEvent == false) {
					++counter[0];
					doubleEvent = true;
				} else if (charCounter == 3 && tripleEvent == false) {
					++counter[1];
					tripleEvent = true;
				}
				if (doubleEvent == true && tripleEvent == true) {
					break;
				}
			}
		}
		return counter;
	}
	
	private static String getMatchingChars(String str1, String str2) {
		String matchingChars = "";
		int iChar = 0;
		while (iChar < str1.length()) {
			if (str1.charAt(iChar) == str2.charAt(iChar)) {
				matchingChars += str1.charAt(iChar);
			}
			++iChar;
		}
		return matchingChars;
	}
	
	private static String correctIDchars(ArrayList<String> aList) {
		String matchingChars = "";
		boolean IDsAreFound = false;
		for (int i = 0; i<aList.size()-1; ++i) {
			String examineID = aList.get(i);
			for (int j = i+1; j<aList.size(); ++j) {
				String currentID = aList.get(j);
				int diff = 0;
				int iChar = 0;
				while (diff < 2 && iChar < examineID.length()) {
					if (examineID.charAt(iChar) != currentID.charAt(iChar)) {
						++diff;
					}
					++iChar;
				}
				if (diff == 1) {
					matchingChars = getMatchingChars(examineID, currentID); 
					IDsAreFound = true;
					break;
				}
			}
		if (IDsAreFound) {break;}
		}
		return matchingChars;
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_2.txt");
		Scanner sc = new Scanner(file);
		ArrayList<String> IDlist = new ArrayList<>();
		sc.useDelimiter("\\Z");
		populate_list(sc, IDlist);
		int[] outputArray = charCounter(IDlist);
		System.out.println("The checksum for my list of box IDs is: " + outputArray[0]*outputArray[1]);
		ArrayList<String> testList = new ArrayList<String>();
		testList.add("abcde");
		testList.add("fghij");
		testList.add("klmno");
		testList.add("pqrst");
		testList.add("fguij");
		testList.add("axcye");
		testList.add("wvxyz");
		String testStr = "fgij";
		System.out.println("Unit test 2: " + testStr.equals(correctIDchars(testList)));
		System.out.println("The common letters between the two correct box IDs are: " + correctIDchars(IDlist));
	}

}
