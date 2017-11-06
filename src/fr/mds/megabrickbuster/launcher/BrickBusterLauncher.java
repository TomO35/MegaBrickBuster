package fr.mds.megabrickbuster.launcher;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.mds.megabrickbuster.game.Game;
import fr.mds.megabrickbuster.game.Menu;

public class BrickBusterLauncher extends StateBasedGame {

	private static int WINDOW_SIZE_X = 570;
	private static int WINDOW_SIZE_Y = 500;
	
	public static final String name = "Mega BrickBuster";
	public static final int menu = 0;
	public static final int solo = 1;
	public static final int multi = 2;

	public static void main(String[] args) {

		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		
		try {
			AppGameContainer app = new AppGameContainer(new BrickBusterLauncher(name));
				
			app.setDisplayMode(WINDOW_SIZE_X, WINDOW_SIZE_Y, false);
			app.setTargetFrameRate(60);
			app.setVSync(true);
			app.setShowFPS(false);
			app.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public BrickBusterLauncher(String name) {
		super(name);
		this.addState(new Menu(menu));
		this.addState(new Game(solo, WINDOW_SIZE_X, WINDOW_SIZE_Y, 1));
		this.addState(new Game(multi, WINDOW_SIZE_X, WINDOW_SIZE_Y, 2));
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		this.getState(menu).init(arg0, this);
		this.getState(solo).init(arg0, this);
		this.getState(multi).init(arg0, this);
		this.enterState(menu);
	}

}
