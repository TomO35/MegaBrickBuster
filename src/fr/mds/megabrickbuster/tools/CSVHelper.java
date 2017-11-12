package fr.mds.megabrickbuster.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

	private final static String scoresPath = "res/scores.csv";
	private final static String levelsPath = "res/levels.csv";
	
	public static List<String[]> readScores() throws IOException {
		
		List<String[]> scores = new ArrayList<>();
		FileReader fr = new FileReader(new File(scoresPath));
		BufferedReader br = new BufferedReader(fr);
		
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			String[] score = line.split(";");
			scores.add(score);
		}
		
		br.close();
		fr.close();
		
		return scores;
	}
	
	public static void writeScores(List<String[]> values) throws IOException {

        FileWriter writer = new FileWriter(scoresPath);

        StringBuilder sb = new StringBuilder();
        for (String[] value : values) {
            sb.append(value[0].trim() + ";" + value[1].trim());
            sb.append("\n");
            writer.append(sb.toString());
        }
        writer.close();
	}
	
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
