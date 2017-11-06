 package fr.mds.megabrickbuster.game;

import java.awt.Rectangle;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.mds.megabrickbuster.launcher.BrickBusterLauncher;

public class Menu extends BasicGameState {
	
	private static int STATE = 0;

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
		arg2.drawString(BrickBusterLauncher.name, 230, 20);
		arg2.drawString("Solo", 100, 120);
		arg2.drawString("Multi", 100, 145);
		arg2.drawString("Scores", 100, 170);
		arg2.drawString("Options", 100, 195);
		arg2.drawString("Exit", 100, 220);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = arg0.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		if((posX > 100 && posX < 120) && (posY > 120 && posY < 200)) {
			if (input.isMouseButtonDown(0)) {
				arg1.enterState(BrickBusterLauncher.solo);
			}
			
		}		
	}

	@Override
	public int getID() {
		return STATE;
	}

}
