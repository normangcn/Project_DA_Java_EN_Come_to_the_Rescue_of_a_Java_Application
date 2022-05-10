package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AnalyticsCounter {
	public HashMap<String, Integer> symptomsReaderAndStorer() {
		try {
			HashMap<String, Integer> symptomsMap = new HashMap<String, Integer>();
			ReadSymptomDataFromFile readData = new ReadSymptomDataFromFile("Project_DA_Java_EN_Come_to_the_Rescue_of_a_Java_Application/Project02Eclipse/symptoms.txt");
			List<String> listSymptoms = readData.GetSymptoms();

			for (String symptom : listSymptoms) {
				symptomsMap.put(symptom, symptomsMap.getOrDefault(symptom, 0) + 1);
			}
			for (String i : symptomsMap.keySet()) {
				System.out.println("key: " + i + " value: " + symptomsMap.get(i));
			}
			return symptomsMap;
		} catch (Exception e) {
			System.out.println("");
		}
		return null;
	}

	public TreeMap<String, Integer> alphaSorting(HashMap<String, Integer> map) {
		TreeMap<String, Integer> sortedSymptomsMap = new TreeMap<>();
		sortedSymptomsMap.putAll(map);
		for (Entry<String, Integer> entry : sortedSymptomsMap.entrySet())
			System.out.println(entry.getKey() + " : " + entry.getValue());
		return sortedSymptomsMap;
	}

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

	public static void main(String args[]) throws Exception {
		AnalyticsCounter listSymptomsMap = new AnalyticsCounter();
		HashMap<String, Integer> map = listSymptomsMap.symptomsReaderAndStorer();
		listSymptomsMap.alphaSorting(map);
		TreeMap<String, Integer> sortedMap = listSymptomsMap.alphaSorting(map);
		listSymptomsMap.writeToFile(sortedMap);
	}
}
