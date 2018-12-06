import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
		
public class Day_1_ChronalCalibration {

	public static void main(String[] args) {
		
		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\input.txt");

	    try {

	        Scanner sc = new Scanner(file);

	        while (sc.hasNextLine()) {
	            int i = sc.nextInt();
	            System.out.println(i);
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
}