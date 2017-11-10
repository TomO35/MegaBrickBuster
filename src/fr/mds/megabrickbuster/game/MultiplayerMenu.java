package fr.mds.megabrickbuster.game;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import fr.mds.megabrickbuster.launcher.BrickBusterLauncher;
import fr.mds.megabrickbuster.model.MenuButton;
import fr.mds.megabrickbuster.multiplayer.Server;

public class MultiplayerMenu extends BasicGameState {
	
	private static int STATE = 5;

	MenuButton clientOk, serverOk;
	TextField clientTextField, serverTextField;
	
	
	public MultiplayerMenu(int state) {
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		clientOk = new MenuButton(230, 220, 100, 25, "Search");
		serverOk = new MenuButton(230, 420, 100, 25, "Show my Ip");
		clientTextField = new TextField(arg0,arg0.getDefaultFont(), 140, 150, 270 , 25, new ComponentListener(){
            public void componentActivated(AbstractComponent arg0) {
                 
            }});
		serverTextField = new TextField(arg0, arg0.getDefaultFont(), 180, 350, 200 , 25);
		clientTextField.setText("Here");
		clientTextField.setBorderColor(Color.red);
		clientTextField.setBackgroundColor(Color.gray);
		serverTextField.setText("IPV4");
		serverTextField.setBorderColor(Color.red);
		serverTextField.setBackgroundColor(Color.gray);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawString(BrickBusterLauncher.name, 210, 30);
		clientOk.render(arg2);
		serverOk.render(arg2);
		arg2.drawRect(130, 100, 300, 150);
		arg2.drawRect(130, 300, 300, 150);
		arg2.drawString("Enter Ip adress of the server", 140, 110);
		arg2.drawString("Show me my Ip adress", 180, 310);
		clientTextField.render(arg0, arg2);
		serverTextField.render(arg0, arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = arg0.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		boolean isClientSearch = (posX > 230  && posX < 330) && (posY < BrickBusterLauncher.WINDOW_SIZE_X - 220 && posY > BrickBusterLauncher.WINDOW_SIZE_Y - 245);
		boolean isServerSearch = (posX > 230  && posX < 330) && (posY < BrickBusterLauncher.WINDOW_SIZE_X - 420 && posY > BrickBusterLauncher.WINDOW_SIZE_Y - 445);
		boolean isClientText = (posX > 140  && posX < 410) && (posY < BrickBusterLauncher.WINDOW_SIZE_X - 150 && posY > BrickBusterLauncher.WINDOW_SIZE_Y - 175);
		if(input.isMouseButtonDown(0)) {
			if (isClientSearch) {
				//a faire
			}
			else if (isServerSearch) {
				Server server = new Server();
				serverTextField.setText(server.getIpv4());
			}
			else if (isClientText) {
				clientTextField.setText("ok");
				clientTextField.setCursorVisible(true);
				clientTextField.setFocus(true);
			}
		}
		
	}

	@Override
	public int getID() {
		return STATE;
	}

}
