package game;

import java.awt.Rectangle;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {

	private static int WINDOW_SIZE_X = 570;
	private static int WINDOW_SIZE_Y = 500;

	Rectangle solo;
	Rectangle multi;
	Rectangle score;
	Rectangle option;
	Rectangle exit;
	
	public Menu(int state) {
		
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawString("Solo", 50, 20);
		arg2.drawString("Multi", 50, 45);
		arg2.drawString("Scores", 50, 70);
		arg2.drawString("Options", 50, 95);
		arg2.drawString("Exit", 50, 120);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void goSolo() {
		try {
			AppGameContainer app = new AppGameContainer(new Game("Mega BrickBuster Solo", WINDOW_SIZE_X, WINDOW_SIZE_Y, 1));
			app.setDisplayMode(WINDOW_SIZE_X, WINDOW_SIZE_Y, false);
			app.setTargetFrameRate(60);
			app.setVSync(true);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
