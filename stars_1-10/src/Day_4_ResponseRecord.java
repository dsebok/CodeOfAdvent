import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
	
	private static int getMinute(String event) {
		int minute = Integer.parseInt(event.substring(15, 17));
		return minute;
	}
	
	private static HashMap<String, int[]> collectGuardSleepTime(ArrayList<String> reorderedEvents) {
		HashMap<String, int[]> guardData = new HashMap<String, int[]>();
		String guardID = "";
		int sleepMin = 0;
		int wakeMin = 0;
		for (String event: reorderedEvents) {
			char startChar = event.charAt(19);
			switch (startChar) {
			case 'G':
				guardID = event.substring(26, 30);
				if (!guardData.containsKey(guardID)) {
					guardData.put(guardID, new int[60]);
				}
				break;
			case 'f':
				sleepMin = getMinute(event);
				break;
			case 'w':
				wakeMin = getMinute(event);
				for (int t = sleepMin; t < wakeMin; ++t) {
					++guardData.get(guardID)[t];
				}
			default:
			}
		}
		return guardData;
	}
	
	private static String getSleepyGuard(HashMap<String, int[]> guardData) {
		String sleepyGuard = "";
		int mostSleptTime = 0;
		for (Map.Entry<String, int[]> data: guardData.entrySet()) {
			int currentSleptTime = 0;
			for (int t: data.getValue()) {
				currentSleptTime += t;
			}
			if (mostSleptTime < currentSleptTime) {
				mostSleptTime = currentSleptTime;
				sleepyGuard = data.getKey();
			}
			
		}
		return sleepyGuard;
	}
	
	private static int getSleepyMin(HashMap<String, int[]> guardData, String sleepyGuard) {
		int sleepyMin = 0;
		int sleepLen = 0;
		int[] sleepTimes = guardData.get(sleepyGuard);
		for (int t = 0; t<sleepTimes.length; ++t) {
			if (sleepLen < sleepTimes[t]) {
				sleepLen = sleepTimes[t];
				sleepyMin = t;
			}
		}	
		return sleepyMin;
	}
	
	private static String getSleepyGuard2(HashMap<String, int[]> guardData) {
		String sleepyGuard = "";
		int mostSleptTime = 0;
		for (Map.Entry<String, int[]> data: guardData.entrySet()) {
			int[] sleepTimes = data.getValue();
			for (int t = 0; t<sleepTimes.length; ++t) {
				if (mostSleptTime < sleepTimes[t]) {
					mostSleptTime = sleepTimes[t];
					sleepyGuard = data.getKey();
				}
			}	
		}
		return sleepyGuard;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_4_test.txt");
		Scanner sc = new Scanner(file);
		ArrayList<String> testList = new ArrayList<String>();
		sc.useDelimiter("\\Z");
		populate_list(sc, testList);	
		ArrayList<String> outPutList = reorderEvents(testList);
		HashMap<String, int[]> guardData = collectGuardSleepTime(outPutList);
		String sleepyGuard = getSleepyGuard(guardData);
		int sleepyMin = getSleepyMin(guardData, sleepyGuard);
		System.out.println("Sleepy Guard: " + sleepyGuard + ", his most sleepy min: " + sleepyMin);
		sleepyGuard = getSleepyGuard2(guardData);
		sleepyMin = getSleepyMin(guardData, sleepyGuard);
		System.out.println("Sleepy Guard2: " + sleepyGuard + ", his most sleepy min: " + sleepyMin);
		
		file = new File("D:\\Code_Life\\repos\\CodeOfAdvent\\stars_1-10\\src\\input_4.txt");
		sc = new Scanner(file);
		ArrayList<String> guardEvents = new ArrayList<>();
		sc.useDelimiter("\\Z");
		populate_list(sc, guardEvents);
		ArrayList<String> orderedList = reorderEvents(guardEvents);
		guardData = collectGuardSleepTime(orderedList);
		sleepyGuard = getSleepyGuard(guardData);
		sleepyMin = getSleepyMin(guardData, sleepyGuard);
		System.out.println("Sleepy Guard: " + sleepyGuard + ", his most sleepy min: " + sleepyMin);
		sleepyGuard = getSleepyGuard2(guardData);
		sleepyMin = getSleepyMin(guardData, sleepyGuard);
		System.out.println("Sleepy Guard2: " + sleepyGuard + ", his most sleepy min: " + sleepyMin);
	}
}
