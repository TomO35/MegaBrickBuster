package fr.mds.megabrickbuster.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
	
	public static void writeScores() throws IOException {
		
	}
	
	public static List<String> readLevel() throws IOException {
		
		List<String> scores = new ArrayList<String>();
		FileReader fr = new FileReader(new File(levelsPath));
		BufferedReader br = new BufferedReader(fr);
		
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			scores.add(line);
		}
		
		br.close();
		fr.close();
		
		return scores;
	}

}
