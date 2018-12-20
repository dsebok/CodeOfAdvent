import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	private static int[] createDelta(int[] startPoint, int[] endPoint) {
		int[] diff = {endPoint[0] - startPoint[0], endPoint[1] - startPoint[1]};
		if (diff[0] != 0 && diff[1] != 0) {
			System.out.println("createDelta warning01: the line is neither horizontal nor vertical!");
			System.out.println("Startpoint: " + Arrays.toString(startPoint) + ", Endpoint: " + Arrays.toString(endPoint));
		}
		
		int distance = Math.abs(diff[0] + diff[1]);
		int[] delta = {diff[0]/distance, diff[1]/distance};
		return delta;
	}
	
	private static ArrayList<int[]> deniedPoints = new ArrayList<int[]>();
	
	private static ArrayList<int[]> deniedPointsOnLine(int[] startPoint, int[] endPoint, int[][] coordinates) {
		int delta[] = createDelta(startPoint, endPoint);
		ArrayList<int[]> currentDeniedPoints = new ArrayList<int[]>();
		
		int[] examPoint = {startPoint[0], startPoint[1]};
		examPoint[0] += delta[0];
		examPoint[1] += delta[1];
		
		while (examPoint[0] != endPoint[0] || examPoint[1] != endPoint[1]) {
			for (int[] currentPoint: coordinates) {
				if (examPoint[0] == currentPoint[0] && examPoint[1] == currentPoint[1]) {
					currentDeniedPoints.add(currentPoint);
					deniedPoints.add(currentPoint);
				}
			}
			examPoint[0] += delta[0];
			examPoint[1] += delta[1];
		}
		
		return currentDeniedPoints;
	}
	
	private static void midSequence(int[] startPoint, int[] endPoint, int direction, int[][] coordinates) {
		if (manDistance(startPoint, endPoint) > 1) {
			ArrayList<int[]> deniedPoints = deniedPointsOnLine(startPoint, endPoint, coordinates);
			
			if (deniedPoints.size()==0) {
				direction = direction / Math.abs(direction);
				int[] delta = createDelta(startPoint, endPoint);
				if (delta[1] == 0) {
					startPoint[0] += delta[0];
					startPoint[1] += direction;
					endPoint[0] -= delta[0];
					endPoint[1] += direction;
				} else {
					startPoint[0] += direction;
					startPoint[1] += delta[1];
					endPoint[0] += direction;
					endPoint[1] -= delta[1];
				}	
				midSequence(startPoint, endPoint, direction, coordinates);
				
			} else {
				ArrayList<int[]> separationPoints = new ArrayList<int[]>(deniedPoints);
				separationPoints.add(0, startPoint);
				separationPoints.add(endPoint);
				int i = 1;
				while (i < separationPoints.size()) {
					int[] currentStart = separationPoints.get(i-1);
					int[] currentEnd = separationPoints.get(i);
					
					System.out.println("seq");
					System.out.println(Arrays.toString(startPoint));
					System.out.println(Arrays.toString(endPoint));
					System.out.println(Arrays.toString(currentStart));
					System.out.println(Arrays.toString(currentEnd));
					System.out.println(Arrays.toString(separationPoints.get(0)));
					System.out.println(Arrays.toString(separationPoints.get(1)));
					System.out.println(Arrays.toString(separationPoints.get(2)));
					
					midSequence(currentStart, currentEnd, direction, coordinates);
					
					System.out.println("lastone");
					System.out.println(Arrays.toString(startPoint));
					System.out.println(Arrays.toString(endPoint));
					System.out.println(Arrays.toString(currentStart));
					System.out.println(Arrays.toString(currentEnd));
					System.out.println(Arrays.toString(separationPoints.get(0)));
					System.out.println(Arrays.toString(separationPoints.get(1)));
					System.out.println(Arrays.toString(separationPoints.get(2)));
					
					++i;
				}
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_6.txt");
		Scanner sc = new Scanner(file);
		ArrayList<String> rawCoordinates = new ArrayList<String>();
		populate_list(sc, rawCoordinates);
		
		rawCoordinates.add("303, 95");
		
		int[][] coordinates = createCoordinateDataBase(rawCoordinates);
		int[] corners = defineCorners(coordinates);
		
		
		System.out.println(corners[0] + ", " + corners[1] + ", " + corners[2] + ", " + corners[3]);
		
		int z = 4;
		int[] t = {z,z};
		int[] h = {t[0], t[1]};
		t[0] = 2*z;
		t[1] = 2*z;
		System.out.println(Arrays.toString(h));
		
		/*
		int[] point1 = {corners[0], corners[1]};
		int[] point2 = {corners[0], corners[3]};
		excludeSeq(point1, point2, 1, coordinates);
		*/
		int[] point3 = {corners[2], corners[1]};
		int[] point4 = {corners[2], corners[3]};
		midSequence(point3, point4, -1, coordinates);
		
		for (int[] point: deniedPoints) {
			System.out.println(Arrays.toString(point));
		}
		System.out.println(deniedPoints.size());
		/*
		point1[0] = corners[0];
		point2[1] = corners[1];
		excludeSeq(point1, point2, 1, coordinates);
		point1[1] = corners[3];
		point2[1] = corners[3];
		excludeSeq(point1, point2, -1, coordinates);
		*/
	}
		
}
