import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_6_Chronal_Coordinates {

	private static void populate_list(Scanner sc, ArrayList<String> aList) {
		sc.useDelimiter("\\Z");
		while (sc.hasNextLine()) {
			aList.add(sc.nextLine());
		}
	}

	private static int getX(String coordinate) {
		int i = 0;
		String X = "";
		while (coordinate.charAt(i) != ',' && i < coordinate.length()) {
			X += coordinate.charAt(i);
			++i;
		}
		return Integer.parseInt(X);
	}
	
	private static int getY(String coordinate) {
		int i = 0;
		String Y = "";
		Boolean readY = false;
		while (i < coordinate.length()) {
			if (readY) {
				Y += coordinate.charAt(i);
			}
			if (coordinate.charAt(i) == ' ') {
				readY = true;
			}
			++i;
		}
		return Integer.parseInt(Y);
	}

	private static int[][] createCoordinateDataBase(ArrayList<String> rawCoordinates) {
		int dimension = 2;
		int size = rawCoordinates.size();
		int[][] dataBase = new int[size][dimension];
		for (int i = 0; i<size; ++i) {
			dataBase[i][0] = getX(rawCoordinates.get(i));
			dataBase[i][1] = getY(rawCoordinates.get(i));
		}
		return dataBase;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_6.txt");
		Scanner sc = new Scanner(file);
		ArrayList<String> rawCoordinates = new ArrayList<String>();
		populate_list(sc, rawCoordinates);
		int[][] coordinates = createCoordinateDataBase(rawCoordinates);
		
		for (int i = 0; i<coordinates.length; ++i) {
			int f1 = coordinates[i][0];
			int f2 = coordinates[i][1];
			System.out.println(f1 + ", " + f2);
		}
	}

}
