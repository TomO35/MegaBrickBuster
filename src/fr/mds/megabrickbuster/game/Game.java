package fr.mds.megabrickbuster.game;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.mds.megabrickbuster.launcher.BrickBusterLauncher;
import fr.mds.megabrickbuster.model.Ball;
import fr.mds.megabrickbuster.model.Brick;
import fr.mds.megabrickbuster.model.Stick;

public class Game extends BasicGameState {
	
	private static int SPACE = 4;
	private static int BRICK_SIZE_X = 44;
	private static int BRICK_SIZE_Y = 16;
	private static int STICK_SIZE_X = 111;
	private static int STICK_SIZE_Y = 18;
	private static int BALL_RADIUS = 7;
	
	private static int STATE = 1;
	
	private ArrayList<Brick> bricks = new ArrayList<>();
	private Stick stick;
	private Ball ball;
	private int windowSizeX, windowSizeY;
	private Input input;
	private Image background;
	
	private float initialSpeedX = .1f;
	private float initialSpeedY = -3.5f;
	
	private int brickMaxY;
	private int lives;
	public int score;
	
	public static int ENDSCORE = 0;
	
	public Game(int state, int windowSizeX, int windowSizeY) {
		this.windowSizeX = windowSizeX;
		this.windowSizeY = windowSizeY;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		lives = 3;
		score = 0;
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
		stick = new Stick(windowSizeX / 2 - STICK_SIZE_X / 2, windowSizeY - 2 * STICK_SIZE_Y, STICK_SIZE_X, STICK_SIZE_Y);
		ball = new Ball(windowSizeX / 2, windowSizeY - BALL_RADIUS * 7, BALL_RADIUS, initialSpeedX, initialSpeedY);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawImage(background, 0, 0, windowSizeX, windowSizeY, 0, 0, 1920, 1200);
		arg2.drawString("Lives = " + lives + " | Score = " + score, 5, (windowSizeY - 60));
		// Each object has its own rendering method
		for (Brick brick : bricks) {
			brick.render(arg2);
		}
		ball.render(arg2);
		stick.render(arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		input = arg0.getInput();
		
		// Get an input for start game
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if (!ball.isMoving()) {
				ball.setMoving(true);
			} else {
				ball.setMoving(false);
			}
		}
		
		// Returns to menu when escape pressed
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			arg1.enterState(BrickBusterLauncher.menu);
		}
		
		// Get input about moving the stick
		stick.update(input, windowSizeX, arg2);
		
		// Handle all the ball's movements
		if (ball.isMoving()) {	
			
			// Checks if ball touched borders
			if (ballToBorder(ball) != null) {
				ball = new Ball(windowSizeX / 2, windowSizeY - BALL_RADIUS * 7, BALL_RADIUS, initialSpeedX, initialSpeedY);
				if(lives > 0) {
					lives -= 1;
					if(lives <= 0) {
						arg1.enterState(BrickBusterLauncher.endgame);
					}
				}
			}
			
			// Check if ball touched stick
			ballToStick(ball, stick);
			
			// Check if there are still bricks
			if (ball.getMinY() <= brickMaxY) {
				if (bricks.size() > 0) {
					// This variable will become the colliding brick to be deleted after the bounce of the ball
					Brick b = ballToBrick(ball);
					// If a brick did collide with the ball, it is removed
					if (b != null) {
						bricks.remove(b);
					}
				} else {
					// When there's no Bricks anymore, player is sent to the endgame page
					ENDSCORE = score;
					arg1.enterState(BrickBusterLauncher.endgame);
				}
			}
			ball.move();
		}			
	}

	// Handle collisions with bricks 
	public Brick ballToBrick(Ball ball) {
		for (Brick brick : bricks) {
			if (ball.intersects(brick)) {
				// Handle the reaction of the ball in collision
				if(ball.getCenterY() >= brick.getMinY() - 4 && ball.getCenterY() <= brick.getMaxY() + 4) {
					ball.bounce("x");
				}
				else if(ball.getCenterX() >= brick.getMinX() - 4 && ball.getCenterX() <= brick.getMaxX() + 4) {
					ball.bounce("y");
				}
				else {
					ball.bounce("angle");
				}
				score += 1;
				return brick;
			}
		}
		return null;
	}

	// Handle collision with the stick
	public void ballToStick(Ball ball, Stick stick) {
		if (ball.intersects(stick)) {
			// Changes angle depending of the position on the stick
			System.out.println("Before : " + ball.getAngle() + "�");
			if ((ball.getCenterX() >= stick.getCenterX() + 2 && ball.getCenterX() <= stick.getMaxX()) && ball.getSpeedY() > 0 && ball.getAngle() > 20) {
				ball.setSpeedToAngle(-15);
				System.out.println("After left : " + ball.getAngle() + "�");
			} else if ((ball.getCenterX() <= stick.getCenterX() - 2 && ball.getCenterX() >= stick.getMinX()) && ball.getSpeedY() > 0 && ball.getAngle() < 160){
				ball.setSpeedToAngle(15);
				System.out.println("after right : " + ball.getAngle() + "�");
			} else {
				ball.bounce("y");
				System.out.println("After else : " + ball.getAngle() + "�");
			}
		}
	}
	
	// Handle collisions with window's Border
	public Ball ballToBorder(Ball ball) {
		if (ball.getMaxX() >= windowSizeX || ball.getMinX() <= 0) {
			ball.bounce("x");
		}
		if (ball.getMinY() <= 0) {
			ball.bounce("y");
		}
		if (ball.getMinY() > windowSizeY) {
			return ball;
		}
		return null;
	}

	@Override
	public int getID() {
		return Game.STATE;
	}
	
}
