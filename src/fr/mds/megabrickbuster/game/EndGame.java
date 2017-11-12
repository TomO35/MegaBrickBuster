package fr.mds.megabrickbuster.game;

import java.io.IOException;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.mds.megabrickbuster.launcher.BrickBusterLauncher;
import fr.mds.megabrickbuster.tools.CSVHelper;

public class EndGame extends BasicGameState{
	
	public static int STATE = 6;
	public static int SCORE = 0;
	
	private TextField tfName;
	private Image background;
	private Input input;
	
	public EndGame(int state) {
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		tfName = new TextField(arg0, arg0.getDefaultFont(), BrickBusterLauncher.WINDOW_SIZE_X / 2 - 100, BrickBusterLauncher.WINDOW_SIZE_Y / 2 - 20, 200, 40);
		background = new Image("res/Background.jpg");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawImage(background, 0, 0, BrickBusterLauncher.WINDOW_SIZE_X, BrickBusterLauncher.WINDOW_SIZE_Y, 0, 0, 1920, 1200);
		arg2.drawString("Score :" + SCORE, BrickBusterLauncher.WINDOW_SIZE_X / 2 - 40, BrickBusterLauncher.WINDOW_SIZE_Y / 2 - 200);
		arg2.drawString("Ton Nom :", BrickBusterLauncher.WINDOW_SIZE_X / 2 - 40, BrickBusterLauncher.WINDOW_SIZE_Y / 2 - 60);
		arg2.drawString("Enter pour valider", BrickBusterLauncher.WINDOW_SIZE_X / 2 - 45, BrickBusterLauncher.WINDOW_SIZE_Y / 2 + 30);
		tfName.render(arg0, arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		SCORE = BrickBusterLauncher.score;
		input = arg0.getInput();
		tfName.setFocus(true);
		
		if (input.isKeyDown(Input.KEY_ENTER)) {
			try {
				if (tfName.getText().trim().equals("")) {
					List<String[]> scores = CSVHelper.readScores();
					scores.add(0, new String[] {tfName.getText().trim(), Integer.toString(SCORE)});
					CSVHelper.writeScores(scores);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			arg1.getState(Scores.STATE).init(arg0, arg1);
			arg1.enterState(BrickBusterLauncher.scores);
		}
		
	}

	@Override
	public int getID() {
		return STATE;
	}

}
