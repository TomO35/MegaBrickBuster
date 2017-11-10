package fr.mds.megabrickbuster.game;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.TextField;

import fr.mds.megabrickbuster.launcher.BrickBusterLauncher;
import fr.mds.megabrickbuster.model.MenuButton;

public class MultiplayerMenu extends BasicGameState {
	
	private static int STATE = 5;

	MenuButton client, server;
	TextField clientTextField, serverTextField;
	
	
	public MultiplayerMenu(int state) {
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		client = new MenuButton(220, 100, 100, 25, "client");
		server = new MenuButton(220, 200, 100, 25, "server");
		clientTextField = new TextField(arg0,arg0.getDefaultFont(), 170, 150, 300 , 25);
		serverTextField = new TextField(arg0, arg0.getDefaultFont(), 170, 250, 300 , 25);
		clientTextField.setText("Enter Ip adress of the server");
		serverTextField.setText("Show me my Ip adress");

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawString(BrickBusterLauncher.name, 210, 30);
		client.render(arg2);
		server.render(arg2);
		clientTextField.render(arg0, arg2);
		serverTextField.render(arg0, arg2);
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
