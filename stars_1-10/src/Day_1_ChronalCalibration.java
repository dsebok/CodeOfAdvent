import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
		
public class Day_1_ChronalCalibration {

	private static void last_frequency(Scanner sc) {
		int frequency = 0;
		while (sc.hasNextLine()) {
			int i = sc.nextInt();
	        frequency += i;
	        System.out.println(frequency);
	        }
		System.out.println(frequency);
		sc.close();	
	}
	
	public static void main(String[] args) {
		
		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\input.txt");

	    try {
	        Scanner sc = new Scanner(file);
	        last_frequency(sc);
	    }
	    
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
}