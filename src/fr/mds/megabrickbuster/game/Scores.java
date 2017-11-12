package fr.mds.megabrickbuster.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.mds.megabrickbuster.launcher.BrickBusterLauncher;
import fr.mds.megabrickbuster.tools.CSVHelper;

public class Scores extends BasicGameState {
	
	public static int STATE = 3;

	private List<String[]> scores = new ArrayList<>();
	private Image background;
	private Input input;
	
	public Scores(int state) {
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		background = new Image("res/Background.jpg");
		try {
			scores = CSVHelper.readScores();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawImage(background, 0, 0, BrickBusterLauncher.WINDOW_SIZE_X, BrickBusterLauncher.WINDOW_SIZE_Y, 0, 0, 1920, 1200);
		String title = new String("Highscores");
		arg2.drawString(title, BrickBusterLauncher.WINDOW_SIZE_X / 2 - title.length() * 8, 50);
		
		Collections.sort(scores, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				String[] s1 = (String[]) o1;
				String[] s2 = (String[]) o2;
				if (Integer.valueOf(s1[1]) > Integer.valueOf(s2[1])){
					return -1;
				} else {
					return 1;
				}
			}
		});
		
		while (scores.size() > 10) {
			scores.remove(scores.size() - 1);
		}
		
		int i = 100;
		for(int x = 0; x < scores.size(); x++) {
			String s = new String(scores.get(x)[0] + " : " + scores.get(x)[1]);
			arg2.drawString(s, BrickBusterLauncher.WINDOW_SIZE_X / 2 - s.length() * 8, i);
			i += 20;
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		input = arg0.getInput();
		
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			arg1.enterState(BrickBusterLauncher.menu);
		}
		
		try {
			scores = CSVHelper.readScores();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getID() {
		return STATE;
	}

}
