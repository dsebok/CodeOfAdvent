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
	
	private static int manDistance(int[] point1, int[] point2) {
		return Math.abs(point2[0] - point1[0]) + Math.abs(point2[1] - point1[1]);
	}
	
	private static int examineLine(int[] startPoint, int[] endPoint, int[][] coordinates) {
		int[] diff = {endPoint[0] - startPoint[0], endPoint[1] - startPoint[1]};
		if (diff[0] != 0 && diff[1] != 0) {
			System.out.println("examineLine error001: the line is neither horizontal nor vertical!");
			return 0;
		}
		
		int distance = Math.abs(diff[0] + diff[1]);
		int[] delta = {diff[0]/distance, diff[1]/distance};
		
		int deniedPoints = 0;
		int[] examPoint = {startPoint[0], startPoint[1]};
		for (int[] currentPoint: coordinates) {
			if (examPoint[0] == currentPoint[0] && examPoint[1] == currentPoint[1]) {
				++deniedPoints;
			}
		}
		
		while (examPoint[0] != endPoint[0] || examPoint[1] != endPoint[1]) {
			examPoint[0] = examPoint[0] + delta[0];
			examPoint[1] = examPoint[1] + delta[1];
			for (int[] currentPoint: coordinates) {
				if (examPoint[0] == currentPoint[0] && examPoint[1] == currentPoint[1]) {
					++deniedPoints;
				}
			}
		}
		
		return deniedPoints;
	}
	
	private static void excludeSeq() {
		
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
		int[] point1 = {corners[0], corners[3]};
		int[] point2 = {corners[2], corners[3]};
		System.out.println(examineLine(point1, point2, coordinates));
	}
		
}
