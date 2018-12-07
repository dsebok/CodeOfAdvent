import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_1_ChronalCalibration {

	private static void populate_list(Scanner sc, ArrayList<Integer> freqMods) {
		while (sc.hasNextLine()) {
			freqMods.add(Integer.parseInt(sc.nextLine()));
		}
	}
	
	private static void last_freq(ArrayList<Integer> freqMods) {
		int frequency = 0;
		for (Integer freqMod: freqMods) {
			frequency += freqMod;
		}
		System.out.println("The last frequency is: " + frequency);
	}
	
	private static void first_double_freq(ArrayList<Integer> freqMods) {
		ArrayList<Integer> freqList = new ArrayList<Integer>();
		int frequency = 0;
		freqList.add(frequency);
		int j = 0;
		while (j > -1) {
			int i = 0;
			while (i < freqMods.size()) {
		        frequency += freqMods.get(i);
		        for (Integer freq: freqList) {
		        	if (freq == frequency) {
		        		System.out.println("The first repeated frequency is: " + frequency);
		        		j = -2;
		        	}
		        }
		        freqList.add(frequency);
		        if (j == -2) {
		        	break;
		        }
		        ++i;
		        }
			++j;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\input.txt");
		Scanner sc = new Scanner(file);
		ArrayList<Integer> freqMods = new ArrayList<Integer>();
		sc.useDelimiter("\\Z");
		populate_list(sc, freqMods);
		last_freq(freqMods);
		first_double_freq(freqMods);
	}
	
}