import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day_1_ChronalCalibration {

	private static void last_frequency(Scanner sc) {
		int frequency = 0;
		while (sc.hasNextLine()) {
			int i = Integer.parseInt(sc.nextLine());
	        frequency += i;
	        System.out.println(frequency);
	        }	
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\input.txt");
		Scanner sc=new Scanner(file);
		sc.useDelimiter("\\Z");
		last_frequency(sc);
	}
	
}