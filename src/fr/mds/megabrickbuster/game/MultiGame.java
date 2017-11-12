package fr.mds.megabrickbuster.game;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.mds.megabrickbuster.model.Ball;
import fr.mds.megabrickbuster.model.Brick;
import fr.mds.megabrickbuster.model.Stick;

public class MultiGame extends Game {
	
	private static int SPACE = 4;
	private static int BRICK_SIZE_X = 44;
	private static int BRICK_SIZE_Y = 16;
	private static int STICK_SIZE_X = 111;
	private static int STICK_SIZE_Y = 18;
	private static int BALL_RADIUS = 7;
	
	private static int STATE = 2;
	public static int SCORE = 0;
	
	private ArrayList<Brick> bricks = new ArrayList<>();
	private Stick stick1, stick2;
	private Ball ball1, ball2;
	private int windowSizeX, windowSizeY;
	private Input input;
	private Image background;
	
	private float initialSpeedX = .1f;
	private float initialSpeedY = -3.5f;
	
	private int brickMaxY;
	private int lives = 3;

	
	
	public MultiGame(int state, int windowSizeX, int windowSizeY) {
		super(state, windowSizeX, windowSizeY);
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		int x = SPACE;
		int y = SPACE;
		background = new Image("res/Background.jpg");
		// Create several new bricks for the game
		for(int i = 0; i < 200; i++) {
			bricks.add(new Brick(x, y, BRICK_SIZE_X, BRICK_SIZE_Y));
			x += BRICK_SIZE_X + SPACE;
			if (x >= windowSizeX) {
				x = SPACE;
				y += BRICK_SIZE_Y + SPACE; 
			}
		}
		brickMaxY = y + SPACE;
		// Creates the stick and ball for the game
		stick1 = new Stick(windowSizeX / 2 - STICK_SIZE_X / 2 - 100, windowSizeY - 2 * STICK_SIZE_Y, STICK_SIZE_X, STICK_SIZE_Y);
		stick2 = new Stick(windowSizeX / 2 - STICK_SIZE_X / 2 + 100, windowSizeY - 2 * STICK_SIZE_Y, STICK_SIZE_X, STICK_SIZE_Y);
		ball1 = new Ball(windowSizeX / 2 - 100, windowSizeY - BALL_RADIUS * 7, BALL_RADIUS, initialSpeedX, initialSpeedY);
		ball2 = new Ball(windowSizeX / 2 + 100, windowSizeY - BALL_RADIUS * 7, BALL_RADIUS, initialSpeedX, initialSpeedY);
	}
	
	@Override
	public int getID() {
		return MultiGame.STATE;
	}
}
