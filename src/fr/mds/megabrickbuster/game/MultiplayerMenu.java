package fr.mds.megabrickbuster.game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.TextField;
import fr.mds.megabrickbuster.launcher.BrickBusterLauncher;
import fr.mds.megabrickbuster.model.MenuButton;
import fr.mds.megabrickbuster.multiplayer.Client;
import fr.mds.megabrickbuster.multiplayer.Server;

public class MultiplayerMenu extends BasicGameState {
	
	private static int STATE = 5;
	public static final Server server = new Server();
	public static final Client client = new Client();

	private int windowSizeX, windowSizeY;
	MenuButton clientButton, serverButton;
	TextField clientTextField, serverTextField;
	String ipv4;
	
	public MultiplayerMenu(int state, int windowSizeX, int windowSizeY) {
		this.windowSizeX = windowSizeX;
		this.windowSizeY = windowSizeY;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		clientButton = new MenuButton(windowSizeX / 2 - 80, 220, 100, 25, "Search");
		serverButton = new MenuButton(windowSizeX / 2 - 80, 420, 100, 25, "Show my Ip");
		clientTextField = new TextField(arg0,arg0.getDefaultFont(), windowSizeX / 2 - 150, 150, 270 , 25);
		serverTextField = new TextField(arg0, arg0.getDefaultFont(), windowSizeX / 2 - 120, 350, 200 , 25);
		clientTextField.setText("Here");
		clientTextField.setBorderColor(Color.red);
		serverTextField.setText("IPV4");
		serverTextField.setBorderColor(Color.red);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawString(BrickBusterLauncher.name,windowSizeX / 2 - 80, 30);
		clientButton.render(arg2);
		serverButton.render(arg2);
		arg2.drawRect(windowSizeX / 2 - 170, 100, 300, 150);
		arg2.drawRect(windowSizeX / 2 - 170, 300, 300, 150);
		arg2.drawString("Enter Ip adress of the server", windowSizeX / 2 - 150, 110);
		arg2.drawString("Show me my Ip adress", windowSizeX / 2 - 120, 310);
		arg2.drawString("Once a button pressed wait a moment, and make sure that 2nd player pressed the other button. ", windowSizeX / 2 - 400, 540);
		clientTextField.render(arg0, arg2);
		serverTextField.render(arg0, arg2);

			


	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = arg0.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		boolean isClientButton = (posX > windowSizeX / 2 - 80  && posX < windowSizeX / 2 + 20) && (posY < windowSizeY - 220 && posY > windowSizeY - 245);
		boolean isServerButton = (posX > windowSizeX / 2 - 80  && posX < windowSizeX / 2 + 20) && (posY < windowSizeY - 420 && posY > windowSizeY - 445);
		boolean isClientText = (posX > windowSizeX / 2 - 150  && posX < windowSizeX / 2 + 120) && (posY < windowSizeY - 150 && posY > windowSizeY - 175);
		if(input.isMouseButtonDown(0)) {
			if (isClientButton) {
				if (client.getServerConnection(clientTextField.getText())) {
					arg1.enterState(BrickBusterLauncher.multiclient);
				}
				arg1.enterState(BrickBusterLauncher.multiclient);
					
			}
			else if (isServerButton) {	
				ipv4 = server.getIpv4();
				serverTextField.setText(ipv4);
				if (server.getClientConnection(ipv4)) {
					arg1.enterState(BrickBusterLauncher.multiserver);
				}
			}
			else if (isClientText) {
				if(!clientTextField.hasFocus()){
					clientTextField.setFocus(true);
					clientTextField.setText("");
					
                }
			}
		}
		
	}

	@Override
	public int getID() {
		return STATE;
	}

}
