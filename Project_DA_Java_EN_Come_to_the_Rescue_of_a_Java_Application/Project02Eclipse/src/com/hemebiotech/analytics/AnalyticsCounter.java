package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author gnorman
 *This is a program that gets a text file in input.
 *It then stores it in a map, sorts it and counts occurrences.
 *Finally it outputs the sorted list with counts in an out file.
 *
 */
public class AnalyticsCounter {
	/**
	 * Calls on <code>ReadSymptomDataFromFile</code> to read a file, then populates a HashMap through a list. 
	 * @return a HashMap containing the content of the file in order of arrival
	 */
	public HashMap<String, Integer> symptomsReaderAndStorer() {
		try {
			HashMap<String, Integer> symptomsMap = new HashMap<String, Integer>();
			ReadSymptomDataFromFile readData = new ReadSymptomDataFromFile("Project_DA_Java_EN_Come_to_the_Rescue_of_a_Java_Application/Project02Eclipse/symptoms.txt");
			List<String> listSymptoms = readData.GetSymptoms();

			for (String symptom : listSymptoms) {
				symptomsMap.put(symptom, symptomsMap.getOrDefault(symptom, 0) + 1);
			}
			return symptomsMap;
		} catch (Exception e) {
			System.out.println("");
		}
		return null;
	}

	/**
	 * Transfers the map from <code>symptomsReaderAndStorer</code> to a tree map, to sort it alphabetically.
	 * @param map the HashMap populated by the symptoms and their count in order of arrival
	 * @return an alphabetically sorted map
	 */
	public TreeMap<String, Integer> alphaSorting(HashMap<String, Integer> map) {
		TreeMap<String, Integer> sortedSymptomsMap = new TreeMap<>();
		sortedSymptomsMap.putAll(map);
		return sortedSymptomsMap;
	}

	/**
	 * Takes the alphabetically sorted map from <code>alphaSorting</code>, then writes its content in the form a shopping list in a <code>result.out</code> file.
	 * @param sortedMap the TreeMap populated by all the symptoms and their respective counts
	 * @return null
	 */
	public TreeMap<String, Integer> writeToFile(TreeMap<String, Integer>sortedMap) {
		try {
			FileWriter writer = new FileWriter ("result.out");
			for (Entry<String, Integer> entry : sortedMap.entrySet())
				writer.write( entry.getKey() + " : " + entry.getValue() + " \n");
			writer.close();
		}
		catch (Exception e){
			System.out.println("Writing permission not granted.");
		}
		return null;
	}

	/**
	 * Create an object of the <code>AnalyticsCounter</code> class, 
	 * then create a HashMap that is populated by the 
	 * <code>symptomsReaderAndStorer</code> method using the aforementioned object. 
	 * The map is then passed onto the sorting method <code>alphaSorting</code>
	 * , stored in <code>sortedMap</code>, then sent to <code>writeToFile</code> the writing method.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		AnalyticsCounter listSymptomsMap = new AnalyticsCounter();
		HashMap<String, Integer> map = listSymptomsMap.symptomsReaderAndStorer();
		listSymptomsMap.alphaSorting(map);
		TreeMap<String, Integer> sortedMap = listSymptomsMap.alphaSorting(map);
		listSymptomsMap.writeToFile(sortedMap);
	}
}
