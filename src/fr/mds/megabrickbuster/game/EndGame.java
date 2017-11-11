package fr.mds.megabrickbuster.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.mds.megabrickbuster.launcher.BrickBusterLauncher;
import fr.mds.megabrickbuster.model.MenuButton;

public class EndGame extends BasicGameState{
	
	private static int STATE = 6;
	
	private TextField tfName;
	private Image background;
	private MenuButton btnOk;
	
	public EndGame(int state) {
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		tfName = new TextField(arg0, arg0.getDefaultFont(), BrickBusterLauncher.WINDOW_SIZE_X / 2 - 100, BrickBusterLauncher.WINDOW_SIZE_Y / 2 - 20, 200, 40);
		background = new Image("res/Background.jpg");
		btnOk = new MenuButton(BrickBusterLauncher.WINDOW_SIZE_X / 2 - 45, BrickBusterLauncher.WINDOW_SIZE_Y / 2 + 30, 90, 30, "Valider");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawImage(background, 0, 0, BrickBusterLauncher.WINDOW_SIZE_X, BrickBusterLauncher.WINDOW_SIZE_Y, 0, 0, 1920, 1200);
		arg2.drawString("Score :" + Game.SCORE, BrickBusterLauncher.WINDOW_SIZE_X / 2 - 100, BrickBusterLauncher.WINDOW_SIZE_Y / 2 - 60);
		arg2.drawString("Ton Nom :", BrickBusterLauncher.WINDOW_SIZE_X / 2 - 40, BrickBusterLauncher.WINDOW_SIZE_Y / 2 - 60);
		tfName.render(arg0, arg2);
		btnOk.render(arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		return STATE;
	}

}
