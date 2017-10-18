package fr.mds.megabrickbuster.launcher;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Launcher {
	
	private static int WINDOW_SIZE_X = 570;
	private static int WINDOW_SIZE_Y = 500;

	public static void main(String[] args) {

		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		
		
		try {
			AppGameContainer app = new AppGameContainer(new Game("Mega BrickBuster", WINDOW_SIZE_X, WINDOW_SIZE_Y));
				
			app.setDisplayMode(WINDOW_SIZE_X, WINDOW_SIZE_Y, false);
			app.setTargetFrameRate(300);
			app.setShowFPS(false);
			app.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

}