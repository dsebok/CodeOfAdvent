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
	
	private static int[] defineCorners(int[][] coordinates) {
		int upLeftX = coordinates[0][0];
		int upLeftY = coordinates[0][1];
		int downRightX = coordinates[0][0];
		int downRightY = coordinates[0][1];
		for (int[] point: coordinates) {
			int X = point[0];
			int Y = point[1];
			if (upLeftX > X) {
				upLeftX = X;
			}
			if (upLeftY > Y) {
				upLeftY = Y;
			}
			if (downRightX < X) {
				downRightX = X;
			}
			if (downRightY < Y) {
				downRightY = Y;
			}
		}
		int[] corners = {upLeftX, upLeftY, downRightX, downRightY};
		return corners;
	}
	
	private static int distance(int X1, int Y1, int X2, int Y2) {
		return Math.abs(X1-X2) + Math.abs(Y1 - Y2);
	}
	
	private static int examineLineHor(int X1, int X2, int Y, int[][] coordinates) {
		int deniedPoints = 0;
		for (int i = X1; i <= X2; ++i) {
			for (int[] point: coordinates) {
				if (point[0] == i && point[1] == Y) {
					++deniedPoints;
				}
			}
		}
		return deniedPoints;
	}
	
	private static int examineLineVer(int Y1, int Y2, int X, int[][] coordinates) {
		int deniedPoints = 0;
		for (int i = Y1; i <= Y2; ++i) {
			for (int[] point: coordinates) {
				if (point[1] == i && point[0] == X) {
					++deniedPoints;
				}
			}
		}
		return deniedPoints;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_6.txt");
		Scanner sc = new Scanner(file);
		ArrayList<String> rawCoordinates = new ArrayList<String>();
		populate_list(sc, rawCoordinates);
		int[][] coordinates = createCoordinateDataBase(rawCoordinates);
		int[] corners = defineCorners(coordinates);
		ArrayList<int[]> deniedPoints = new ArrayList<int[]>();
		
	
		System.out.println(corners[0] + ", " + corners[1] + ", " + corners[2] + ", " + corners[3]);
	}
}
