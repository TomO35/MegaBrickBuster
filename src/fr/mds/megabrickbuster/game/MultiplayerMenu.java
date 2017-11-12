package fr.mds.megabrickbuster.game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	private String ipv4;
	private Image background;
	
	public MultiplayerMenu(int state, int windowSizeX, int windowSizeY) {
		this.windowSizeX = windowSizeX;
		this.windowSizeY = windowSizeY;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		background = new Image("res/Background.jpg");
		clientButton = new MenuButton(windowSizeX / 2 - 140, 220, 250, 25, "Search server connection");
		serverButton = new MenuButton(windowSizeX / 2 - 140, 420, 250, 25, "Open your connection");
		clientTextField = new TextField(arg0,arg0.getDefaultFont(), windowSizeX / 2 - 150, 150, 270 , 25);
		serverTextField = new TextField(arg0, arg0.getDefaultFont(), windowSizeX / 2 - 120, 350, 200 , 25);
		clientTextField.setText("Here");
		clientTextField.setBorderColor(Color.red);
		ipv4 = server.getIpv4();
		serverTextField.setText(ipv4);
		serverTextField.setBorderColor(Color.red);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		//this part draw the menu
		arg2.drawImage(background, 0, 0, windowSizeX, windowSizeY, 0, 0, 1920, 1200);
		arg2.drawString(BrickBusterLauncher.name,windowSizeX / 2 - 80, 30);
		clientButton.render(arg2);
		serverButton.render(arg2);
		arg2.drawRect(windowSizeX / 2 - 170, 100, 300, 150);
		arg2.drawRect(windowSizeX / 2 - 170, 300, 300, 150);
		arg2.drawString("Enter Ip adress of the server", windowSizeX / 2 - 150, 110);
		arg2.drawString("Your ip address", windowSizeX / 2 - 100, 310);
		arg2.drawString("Once a button pressed wait a moment, and make sure that 2nd player pressed the other button. ", windowSizeX / 2 - 400, 540);
		clientTextField.render(arg0, arg2);
		serverTextField.render(arg0, arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = arg0.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		boolean isClientButton = (posX > windowSizeX / 2 - 140  && posX < windowSizeX / 2 + 110) && (posY < windowSizeY - 220 && posY > windowSizeY - 245);
		boolean isServerButton = (posX > windowSizeX / 2 - 140  && posX < windowSizeX / 2 + 110) && (posY < windowSizeY - 420 && posY > windowSizeY - 445);
		boolean isClientText = (posX > windowSizeX / 2 - 150  && posX < windowSizeX / 2 + 120) && (posY < windowSizeY - 150 && posY > windowSizeY - 175);
		if(input.isMouseButtonDown(0)) {
			//when ClientButton pressed it search for a connection with the server on port 2005
			if (isClientButton) {
				//close the actual socket in order to set free the port
				//if(client.isConnected()) {
					//client.closeConnection();
				//}
				if (client.getServerConnection(clientTextField.getText())) {
					arg1.enterState(BrickBusterLauncher.multiclient);
				}
				arg1.enterState(BrickBusterLauncher.multiclient);		
			}
			//when ServerButton pressed it search for a connection with the client on port 2005
			else if (isServerButton) {
				//close the actual socket in order to set free the port
				//if(server.isConnected()) {
					//server.closeConnection();
				//}
				if (server.getClientConnection(ipv4)) {
					arg1.enterState(BrickBusterLauncher.multiserver);
				}
			}
			//Change the EditText of the Client in order to right the ip address of the server
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
