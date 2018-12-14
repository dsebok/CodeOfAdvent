import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day_4_ResponseRecord {

	private static void populate_list(Scanner sc, ArrayList<String> aList) {
		while (sc.hasNextLine()) {
			aList.add(sc.nextLine());
		}
	}
	
	private static int[] interpretTime(String inputStr) {
		String delimiter = "firstBracket";
		int year = 0;
		int month = 0;
		int day = 0;
		int hours = 0;
		int mins = 0;
		String timeString = "";
		for (char Char: inputStr.toCharArray()) {
			
			switch (delimiter) {
			case "firstBracket":
				delimiter = "hyphen1"; 
				break;
			case "hyphen1":
				if (Char == '-') {
					delimiter = "hyphen2";
					year = Integer.parseInt(timeString);
					timeString = "";
					break;
				}
			case "hyphen2":
				if (Char == '-') {
					delimiter = "space";
					month = Integer.parseInt(timeString);
					timeString = "";
					break;
				}
			case "space":
				if (Char == ' ') {
					delimiter = "colon";
					day = Integer.parseInt(timeString);
					timeString = "";
					break;
				}
			case "colon":
				if (Char == ':') {
					delimiter = "lastBracket";
					hours = Integer.parseInt(timeString);
					timeString = "";
					break;
					}
			case "lastBracket":
				if (Char == ']') {
					delimiter = "";
					mins = Integer.parseInt(timeString);
					timeString = "";
					break;
				}
			default: timeString += Char;
			}
		}
		int[] interpretTime = {year, month, day, hours, mins};
		return interpretTime;
	}
	
	private static int inMinutes(int[] time) {
		int months = time[0] * 12 + time[1];
		int days = months * 31 + time[2];
		int hours = days * 24 + time[3];
		int minutes = hours * 60 + time[4];
		return minutes;
	}
	
	private static HashMap<String, Integer> assignTime(ArrayList<String> guardEvents) {
		HashMap<String, Integer> eventsWithTime = new HashMap<String, Integer>();
		
		for (String event: guardEvents) {
			eventsWithTime.put(event, inMinutes(interpretTime(event)));
		}
		return eventsWithTime;
	}
	
	private static ArrayList<String> reorderEvents(ArrayList<String> guardEvents) {
		HashMap<String, Integer> eventsWithTime = assignTime(guardEvents);
		ArrayList<String> reordered = new ArrayList<>();
		for (String event: guardEvents) {
			int currentEventTime = eventsWithTime.get(event);
			switch (reordered.size()) {
			case 0:
				reordered.add(event);
				break;
			case 1:
				if (currentEventTime < eventsWithTime.get(reordered.get(0))) {
					reordered.add(0, event);
				} else {
					reordered.add(event);
				}
				break;
			default:
				for (int i=0; i<reordered.size(); ++i) {
					int listEventTime = eventsWithTime.get(reordered.get(i));
					if (currentEventTime < listEventTime) {
						reordered.add(i, event);
						break;
					} else if (i == reordered.size()-1) {
						reordered.add(event);
						break;
					}
				}
			}
		}
		return reordered;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_4_test.txt");
		Scanner sc = new Scanner(file);
		ArrayList<String> testList = new ArrayList<String>();
		sc.useDelimiter("\\Z");
		populate_list(sc, testList);
		
		//int unit1 = 240;
		/*
		ArrayList<String> outPutList = reorderEvents(testList);
		for (String event: outPutList) {
			System.out.println(event);
		}
		*/
		
		file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_4.txt");
		sc = new Scanner(file);
		ArrayList<String> guardEvents = new ArrayList<>();
		sc.useDelimiter("\\Z");
		populate_list(sc, guardEvents);
		ArrayList<String> orderedList = reorderEvents(guardEvents);
		for (String event: orderedList) {
			System.out.println(event);
		}
	}

}
