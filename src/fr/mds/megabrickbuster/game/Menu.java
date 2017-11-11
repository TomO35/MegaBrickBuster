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
	int windowSizeX, windowSizeY;
	
	public Menu(int state, int windowSizeX, int windowSizeY) {
		this.windowSizeX = windowSizeX;
		this.windowSizeY = windowSizeY;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		solo = new MenuButton(windowSizeX / 2 - 100, 150, 200, 40, "Jouer");
		multi = new MenuButton(windowSizeX / 2 - 100, 195, 200, 40, "Multijoueur");
		score = new MenuButton(windowSizeX / 2 - 100, 240, 200, 40, "Highscores");
		option = new MenuButton(windowSizeX / 2 - 100, 285, 200, 40, "Options");
		exit = new MenuButton(windowSizeX / 2 - 100, 330, 200, 40, "Quitter");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawString(BrickBusterLauncher.name, windowSizeX / 2 - 80, 30);
		solo.render(arg2);
		multi.render(arg2);
		score.render(arg2);
		//option.render(arg2);
		exit.render(arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = arg0.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		boolean isSolo = (posX > windowSizeX / 2 - 100  && posX < windowSizeX / 2 + 100) && (posY < windowSizeY - 150 && posY > windowSizeY - 190);
		boolean isMulti = (posX > windowSizeX / 2 - 100  && posX < windowSizeX / 2 + 100) && (posY < windowSizeY - 195 && posY > windowSizeY - 235);
		boolean isScore = (posX > windowSizeX / 2 - 100  && posX < windowSizeX / 2 + 100) && (posY < windowSizeY - 240 && posY > windowSizeY - 280);
		boolean isOption = (posX > windowSizeX / 2 - 100  && posX < windowSizeX / 2 + 100) && (posY < windowSizeY - 285 && posY > windowSizeY - 325);
		boolean isExit = (posX > windowSizeX / 2 - 100  && posX < windowSizeX / 2 + 100) && (posY < windowSizeY - 330 && posY > windowSizeY - 370);
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
