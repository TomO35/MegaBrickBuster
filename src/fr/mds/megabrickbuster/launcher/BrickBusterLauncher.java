package fr.mds.megabrickbuster.launcher;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import game.Game;

public class BrickBusterLauncher {
	
	private static int WINDOW_SIZE_X = 570;
	private static int WINDOW_SIZE_Y = 500;

	public static void main(String[] args) {

		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		
		// TODO Display Menu
		
		try {
			AppGameContainer app = new AppGameContainer(new Game("Mega BrickBuster", WINDOW_SIZE_X, WINDOW_SIZE_Y, 1));
				
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
