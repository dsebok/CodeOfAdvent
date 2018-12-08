import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day_3_OverlappingFabrics {

	private static void populate_list(Scanner sc, ArrayList<String> aList) {
		while (sc.hasNextLine()) {
			aList.add(sc.nextLine());
		}
	}
	
	private static int[] findSeparators(String inputStr) {
		int locOfAt = 0;
		int locOfComma = 0;
		int locOfColon = 0;
		int locOfX = 0;
		for (int i = 0; i< inputStr.length(); ++i) {
			char Char = inputStr.charAt(i);
			if (Char == '@') {
				locOfAt = i;
			} else if (Char == ',') {
				locOfComma = i;
			} else if (Char == ':') {
				locOfColon = i;
			} else if (Char == 'x') {
				locOfX = i;
			}
		}
		int[] coordinates = {locOfAt, locOfComma, locOfColon, locOfX};
		return coordinates;
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_3.txt");
		Scanner sc = new Scanner(file);
		ArrayList<String> IDlist = new ArrayList<>();
		sc.useDelimiter("\\Z");
		populate_list(sc, IDlist);
		
		ArrayList<String> testList = new ArrayList<String>();
		testList.add("#1 @ 1,3: 4x4");
		testList.add("#2 @ 3,1: 4x4");
		testList.add("#3 @ 5,5: 2x2");
		//int test1 = 4;
		//System.out.println("Unit test 1: " + (test1 == test1));
		
		System.out.println("test: " + Arrays.toString(findSeparators(testList.get(1))));
		
		
	}

}
