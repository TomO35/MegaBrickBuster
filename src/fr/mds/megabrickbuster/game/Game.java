package fr.mds.megabrickbuster.game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
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
	
	private int nbGamer;
	private ArrayList<Brick> bricks = new ArrayList<>();
	private ArrayList<Stick> sticks = new ArrayList<>();
	private ArrayList<Ball> balls = new ArrayList<>();
	private int windowSizeX, windowSizeY;
	private Input input;
	private Image background;
	
	private float initialSpeedX = .1f;
	private float initialSpeedY = -3f;
	
	private int brickMaxY;
	
	private int lives = 3;
	private int score = 0;
	
	public Game(int state, int windowSizeX, int windowSizeY, int nbGamer) {
		this.STATE = state;
		this.windowSizeX = windowSizeX;
		this.windowSizeY = windowSizeY;
		this.nbGamer = nbGamer;
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
		brickMaxY = y;
		// Creates the stick(s) and ball(s) for the game
		if (nbGamer == 1) {
			Stick stick = new Stick(windowSizeX / 2 - STICK_SIZE_X / 2, windowSizeY - 2 * STICK_SIZE_Y, STICK_SIZE_X, STICK_SIZE_Y);
			sticks.add(stick);
			Ball ball = new Ball(windowSizeX / 2, windowSizeY - BALL_RADIUS * 6, BALL_RADIUS, initialSpeedX, initialSpeedY);
			balls.add(ball);
		}
		else if (nbGamer == 2) {
			Stick stickP1 = new Stick(windowSizeX / 3 - STICK_SIZE_X / 2, windowSizeY - STICK_SIZE_Y * 2, STICK_SIZE_X, STICK_SIZE_Y);
			Stick stickP2 = new Stick(windowSizeX * 2 / 3 - STICK_SIZE_X / 2, windowSizeY - STICK_SIZE_Y * 2, STICK_SIZE_X, STICK_SIZE_Y);
			sticks.add(stickP1);
			sticks.add(stickP2);
			Ball ball1 = new Ball(windowSizeX / 3, windowSizeY - STICK_SIZE_Y * 3, BALL_RADIUS, initialSpeedX, initialSpeedY);
			Ball ball2 = new Ball(windowSizeX * 2 / 3, windowSizeY - STICK_SIZE_Y * 3, BALL_RADIUS, initialSpeedX, initialSpeedY);
			balls.add(ball1);
			balls.add(ball2);
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawImage(background, 0, 0, BrickBusterLauncher.WINDOW_SIZE_X, BrickBusterLauncher.WINDOW_SIZE_Y, 0, 0, 1920, 1200);
		arg2.drawString("Lives = " + lives + " | Score = " + score, 5, (BrickBusterLauncher.WINDOW_SIZE_Y - 60));
		// Each object has its own rendering method
		for (Brick brick : bricks) {
			brick.render(arg2);
		}
		for (Ball ball : balls) {
			ball.render(arg2);
		}
		for (Stick stick : sticks) {
			stick.render(arg2);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		input = arg0.getInput();
		
		// Get an input for start game
		if (input.isKeyDown(Input.KEY_SPACE)) {
			for (Ball ball : balls) {
				if (!ball.isMoving()) {
					ball.setMoving(true);
				}
			}
		}
		
		// Get an input to pause game
		if (input.isKeyDown(Input.KEY_P)) {
			arg1.enterState(BrickBusterLauncher.menu);
		}
		
		// Get input about moving the stick
		for (Stick stick : sticks) {
			stick.update(input, windowSizeX, arg2);
		}
		
		Ball deadBall = null;
		for (Ball ball : balls) {
			// Handle all the ball's movements
			if (ball.isMoving()) {	
				
				deadBall = ballToBorder(ball);
				
				for(Stick stick : sticks) {
					ballToStick(ball, stick);
				}
				
				// Check if there are still bricks
				if (ball.getMinY() <= brickMaxY) {
					if (bricks.size() > 0) {
						// This variable will become the colliding brick to be deleted after the bounce of the ball
						Brick b = ballToBrick(ball);
						// If a brick did collide with the ball, it is removed
						if (b != null) {
							bricks.remove(b);
						}
					}
					else {
						// If there are no brick anymore, game is won
						//TODO WIN - Screen
					}
				}
				ball.move();
			}			
		}
		if (deadBall != null) {
			balls.remove(deadBall);
			balls.add(new Ball(windowSizeX / 2, windowSizeY - STICK_SIZE_Y * 3, BALL_RADIUS, initialSpeedX, initialSpeedY));
			if(lives > 0) {
				lives -= 1;
				if(lives <= 0) {
					//TODO GAME OVER - display screen
				}
			}
		}
	}

	// Handle collisions with bricks 
	public Brick ballToBrick(Ball ball) {
		for (Brick brick : bricks) {
			if (ball.intersects(brick)) {
				// Handle the reaction of the ball in collision
				if(ball.getCenterY() >= brick.getMinY() - 3 && ball.getCenterY() <= brick.getMaxY() + 3) {
					ball.bounce("x");
				}
				else if(ball.getCenterX() >= brick.getMinX() - 3 && ball.getCenterX() <= brick.getMaxX() + 3) {
					ball.bounce("y");
				}
				else {
					ball.bounce("angle");
				}
				if(score == 0) {
					score = 1;
				} else {
					score *= 2;
				}
				return brick;
			}
		}
		return null;
	}

	// Handle collision with the stick
	public void ballToStick(Ball ball, Stick stick) {
		if (ball.intersects(stick)) {
//			if (ball.getCenterX() >= stick.getX() && ball.getCenterX() <= stick.getMaxX()) {
//				ball.setSpeedX(ball.getSpeedX() - (stick.getCenterX() - ball.getCenterX()));
//				ball.setSpeedY(ball.getSpeedY());
//				ball.setSpeedX(ball.getSpeedX() * (stick.getCenterX() - ball.getCenterX()) / (stick.getWidth() / 2));
//			} else {
//				ball.bounce("angle");
//			}
			ball.bounce("y");
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
		return STATE;
	}
	
}
