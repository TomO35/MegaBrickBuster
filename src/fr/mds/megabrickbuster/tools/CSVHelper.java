package fr.mds.megabrickbuster.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CSVHelper {

	private final static String scoresPath = "res/scores.csv";
	private final static String levelsPath = "res/levels.csv";
	
	// This method reads the CSV, sorts the scores and the return the list of scores with associated names
	public static List<String[]> readScores() throws IOException {
		
		List<String[]> scores = new ArrayList<>();
		FileReader fr = new FileReader(new File(scoresPath));
		BufferedReader br = new BufferedReader(fr);
		
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			String[] score = line.split(";");
			scores.add(score);
		}
		
		Collections.sort(scores, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				String[] s1 = (String[]) o1;
				String[] s2 = (String[]) o2;
				if (Integer.valueOf(s1[1]) > Integer.valueOf(s2[1])){
					return 1;
				} else if(Integer.valueOf(s1[1]) == Integer.valueOf(s2[1])) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		
		while (scores.size() > 10) {
			scores.remove(scores.size() - 1);
		}
		
		br.close();
		fr.close();
		
		return scores;
	}
	
	// This method writes the scores into the CSV
	public static void writeScores(List<String[]> values) throws IOException {
		
        FileWriter writer = new FileWriter(scoresPath, false);

        StringBuilder sb = new StringBuilder();
        for (String[] value : values) {
            sb.append(value[0].trim() + ";" + value[1].trim());
            sb.append("\n");
        }
        writer.append(sb.toString());
        System.out.println("Has written Score !");
        writer.close();
	}
	
	// This method is to read the level to build brick patterns
	public static List<String> readLevel() throws IOException {
		
		List<String> lines = new ArrayList<String>();
		FileReader fr = new FileReader(new File(levelsPath));
		BufferedReader br = new BufferedReader(fr);
		
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			lines.add(line);
		}
		
		br.close();
		fr.close();
		
		return lines;
	}

}
