package fr.mds.megabrickbuster.game;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.TextField;

import fr.mds.megabrickbuster.launcher.BrickBusterLauncher;
import fr.mds.megabrickbuster.model.MenuButton;

public class MultiplayerMenu extends BasicGameState {
	
	private static int STATE = 5;
	
	Font font;
	MenuButton client, server;
	TextField clientTextField, serverTextField;
	
	
	public MultiplayerMenu(int state) {
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		client = new MenuButton(220, 120, 100, 25, "client");
		server = new MenuButton(220, 160, 100, 25, "server");
		font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 26), false);
		clientTextField = new TextField(arg0, font, 10, 30, 50 , 30);
		serverTextField = new TextField(arg0, font, 10, 30, 50 , 30);

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawString(BrickBusterLauncher.name, 210, 30);
		client.render(arg2);
		server.render(arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = arg0.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		boolean isClient = (posX > 220  && posX < 320) && (posY < BrickBusterLauncher.WINDOW_SIZE_X - 120 && posY > BrickBusterLauncher.WINDOW_SIZE_Y - 145);
		boolean isServer = (posX > 220  && posX < 320) && (posY < BrickBusterLauncher.WINDOW_SIZE_X - 150 && posY > BrickBusterLauncher.WINDOW_SIZE_Y - 175);
		if(input.isMouseButtonDown(0)) {
			if (isClient) {
				
			}
			else if (isServer) {
				
			}
		}		
	}

	@Override
	public int getID() {
		return STATE;
	}

}
