import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
		int[] separators = {locOfAt, locOfComma, locOfColon, locOfX};
		return separators;
	}
	
	private static int[] findCorners(String inputStr, int[] separators) {
		int locOfAt = separators[0];
		int locOfComma = separators[1];
		int locOfColon = separators[2];
		int locOfX = separators[3];
		int upLeftX = Integer.parseInt(inputStr.substring(locOfAt + 2, locOfComma));
		int upLeftY = Integer.parseInt(inputStr.substring(locOfComma + 1, locOfColon));
		int downRightX = Integer.parseInt(inputStr.substring(locOfColon + 2, locOfX)) + upLeftX;
		int downRightY = Integer.parseInt(inputStr.substring(locOfX + 1, inputStr.length())) + upLeftY;
		int[] corners = {upLeftX, upLeftY, downRightX, downRightY};
		return corners;
	}
	
	private static int[] coordinates(String inputStr) {
		int[] separators = findSeparators(inputStr);
		return findCorners(inputStr, separators);
	}
	
	private static int maxValue(ArrayList<String> elfPlans) {
		int maxValue = 0;
		for (String plan: elfPlans) {
			int[] coordinates = coordinates(plan);
			for (int coord: coordinates) {
				if (coord > maxValue) {
					maxValue = coord;
				}
			}
		}
		return maxValue;
	}
	
	private static int[][] planDataBase(ArrayList<String> elfPlans) {
		int maxValue =  maxValue(elfPlans);
		int[][] planDataBase = new int[maxValue+1][maxValue+1];
		for (String plan: elfPlans) {
			int[] coordinates = coordinates(plan);
			int upLeftX = coordinates[0];
			int upLeftY = coordinates[1];
			int downRightX = coordinates[2];
			int downRightY = coordinates[3];
			for (int X = upLeftX; X < downRightX; ++X) {
				for (int Y = upLeftY; Y < downRightY; ++Y) {
					planDataBase[X][Y]++;
				}
			}
		}
		return planDataBase;
	}
	
	private static int commonSquares(int[][] planDataBase) {
		int commonSquares = 0;
		for (int[] row: planDataBase ) {
			for (int square: row) {
				if (square > 1) {
					++commonSquares;
				}
			}
		}
		return commonSquares;
	}
	
	static String IDofIntactSquare(ArrayList<String> elfPlans, int[][] planDataBase) {
		for (String plan: elfPlans) {
			int[] coordinates = coordinates(plan);
			int upLeftX = coordinates[0];
			int upLeftY = coordinates[1];
			int downRightX = coordinates[2];
			int downRightY = coordinates[3];
			boolean intact = true;
			for (int X = upLeftX; X < downRightX; ++X) {
				for (int Y = upLeftY; Y < downRightY; ++Y) {
					if (planDataBase[X][Y] != 1) {
						intact = false;
						break;
					}
				}
			}
			if (intact) {
				return plan;
			}
		}
		return "None";
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_3.txt");
		Scanner sc = new Scanner(file);
		ArrayList<String> elfPlans = new ArrayList<>();
		sc.useDelimiter("\\Z");
		populate_list(sc, elfPlans);
		
		ArrayList<String> testList = new ArrayList<String>();
		testList.add("#1 @ 1,3: 4x4");
		testList.add("#2 @ 3,1: 4x4");
		testList.add("#3 @ 5,5: 2x2");
		int unit1 = 4;
		int[][] testDataBase = planDataBase(testList);
		int commonSquares = commonSquares(testDataBase);
		System.out.println("Unit test 1: " + (commonSquares == unit1));
		String unit2 = "#3 @ 5,5: 2x2";
		System.out.println("Unit test 2: " + (IDofIntactSquare(testList, testDataBase) == unit2));
		
		int[][] planDataBase = planDataBase(elfPlans);
		commonSquares = commonSquares(planDataBase);
		System.out.println("The number of the common squares is: " + commonSquares);
		System.out.println("The intact claim is: " + IDofIntactSquare(elfPlans, planDataBase));
	}

}
