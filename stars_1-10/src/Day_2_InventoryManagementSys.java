import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_2.txt");
		Scanner sc = new Scanner(file);
		ArrayList<String> IDlist = new ArrayList<>();
		sc.useDelimiter("\\Z");
		populate_list(sc, IDlist);
		
		ArrayList<String> testList = new ArrayList<>();
		testList.add("abcdef");
		testList.add("bababc");
		testList.add("abbcde");
		testList.add("abcccd");
		testList.add("aabcdd");
		testList.add("abcdee");
		testList.add("ababab");
		int[] outputArray = charCounter(testList);
		
		System.out.println(Arrays.toString(outputArray));
	}

}
