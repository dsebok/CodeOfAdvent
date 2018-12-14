import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_4_ResponseRecord {

	private static void populate_list(Scanner sc, ArrayList<String> aList) {
		while (sc.hasNextLine()) {
			aList.add(sc.nextLine());
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_4_test.txt");
		Scanner sc = new Scanner(file);
		ArrayList<String> testList = new ArrayList<String>();
		sc.useDelimiter("\\Z");
		populate_list(sc, testList);
		
		//int unit1 = 240;
		
		System.out.println("Unit test 1: ");
		
		/*
		file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_4.txt");
		sc = new Scanner(file);
		ArrayList<String> guardEvents = new ArrayList<>();
		sc.useDelimiter("\\Z");
		populate_list(sc, guardEvents);
		System.out.println("");
		*/
	}

}
