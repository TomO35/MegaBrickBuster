 package fr.mds.megabrickbuster.game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.mds.megabrickbuster.launcher.BrickBusterLauncher;
import fr.mds.megabrickbuster.model.MenuButton;

public class Menu extends BasicGameState {
	
	private static int STATE = 0;

	MenuButton solo, multi, score, option, exit;
	
	public Menu(int state) {
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		solo = new MenuButton(220, 120, 100, 25, "solo");
		multi = new MenuButton(220, 150, 100, 25, "multi");
		score = new MenuButton(220, 180, 100, 25, "score");
		option = new MenuButton(220, 210, 100, 25, "option");
		exit = new MenuButton(220, 240, 100, 25, "exit");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawString(BrickBusterLauncher.name, 210, 30);
		solo.render(arg2);
		multi.render(arg2);
		score.render(arg2);
		option.render(arg2);
		exit.render(arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = arg0.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		boolean isSolo = (posX > 220  && posX < 320) && (posY < BrickBusterLauncher.WINDOW_SIZE_X - 120 && posY > BrickBusterLauncher.WINDOW_SIZE_Y - 145);
		boolean isMulti = (posX > 220  && posX < 320) && (posY < BrickBusterLauncher.WINDOW_SIZE_X - 150 && posY > BrickBusterLauncher.WINDOW_SIZE_Y - 175);
		boolean isScore = (posX > 220  && posX < 320) && (posY < BrickBusterLauncher.WINDOW_SIZE_X - 180 && posY > BrickBusterLauncher.WINDOW_SIZE_Y - 205);
		boolean isOption = (posX > 220  && posX < 320) && (posY < BrickBusterLauncher.WINDOW_SIZE_X - 210 && posY > BrickBusterLauncher.WINDOW_SIZE_Y - 235);
		boolean isExit = (posX > 220  && posX < 320) && (posY < BrickBusterLauncher.WINDOW_SIZE_X - 240 && posY > BrickBusterLauncher.WINDOW_SIZE_Y - 265);
		if(input.isMouseButtonDown(0)) {
			if (isSolo) {
				arg1.enterState(BrickBusterLauncher.solo);
			}
			else if (isMulti) {
				arg1.enterState(BrickBusterLauncher.multiMenu);
			}
			else if (isScore) {
				arg1.enterState(BrickBusterLauncher.score);
			}
			else if (isOption) {
				
			}
			else if (isExit) {
				arg0.exit();
			}
		}		
	}

	@Override
	public int getID() {
		return STATE;
	}

}
