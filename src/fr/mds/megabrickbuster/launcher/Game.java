package fr.mds.megabrickbuster.launcher;

import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import fr.mds.megabrickbuster.model.Ball;
import fr.mds.megabrickbuster.model.Brick;
import fr.mds.megabrickbuster.model.Stick;

public class Game extends BasicGame {
	
	private static int SPACE = 10;
	private static int BRICK_SIZE_X = 30;
	private static int BRICK_SIZE_Y = 10;
	private static int STICK_SIZE_X = 60;
	private static int STICK_SIZE_Y = 10;
	private static int BALL_RADIUS = 10;
	
	private int nbGamer;
	private ArrayList<Brick> bricks = new ArrayList<>();
	private ArrayList<Stick> sticks = new ArrayList<>();
	private ArrayList<Ball> balls = new ArrayList<>();
	private int windowSizeX, windowSizeY;
	private Input input;
	
	private float initialSpeedX = .1f;
	private float initialSpeedY = -5f;
	
	private int brickMaxY;
	
	private int lives = 3;
	
	public Game(String title, int windowSizeX, int windowSizeY, int nbGamer) {
		super(title);
		this.windowSizeX = windowSizeX;
		this.windowSizeY = windowSizeY;
		this.nbGamer = nbGamer;
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		int x = SPACE;
		int y = SPACE;
		// Create several new bricks for the game
		for(int i = 0; i < 196; i++) {
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
			Stick stick = new Stick(windowSizeX / 2 - STICK_SIZE_X / 2, windowSizeY - STICK_SIZE_Y, STICK_SIZE_X, STICK_SIZE_Y);
			sticks.add(stick);
			Ball ball = new Ball(windowSizeX / 2, windowSizeY - BALL_RADIUS * 4, BALL_RADIUS, initialSpeedX, initialSpeedY);
			balls.add(ball);
		}
		else if (nbGamer == 2) {
			Stick stickP1 = new Stick(windowSizeX / 2 - STICK_SIZE_X / 2, windowSizeY - STICK_SIZE_Y * 2, STICK_SIZE_X, STICK_SIZE_Y);
			Stick stickP2 = new Stick(windowSizeX / 2 - STICK_SIZE_X / 2, windowSizeY - STICK_SIZE_Y * 2, STICK_SIZE_X, STICK_SIZE_Y);
			sticks.add(stickP1);
			sticks.add(stickP2);
			Ball ball1 = new Ball(windowSizeX / 3, windowSizeY - BALL_RADIUS * 4, BALL_RADIUS, initialSpeedX, initialSpeedY);
			Ball ball2 = new Ball(windowSizeX *2 / 3, windowSizeY - BALL_RADIUS * 4, BALL_RADIUS, initialSpeedX, initialSpeedY);
			balls.add(ball1);
			balls.add(ball2);
		}
		
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// Each object has its own rendering method
		for (Brick brick : bricks) {
			brick.render(arg1);
		}
		for (Ball ball : balls) {
			ball.render(arg1);
		}
		for (Stick stick : sticks) {
			stick.render(arg1);
		}
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		input = arg0.getInput();
		
		// Get an input for start game
		if (input.isKeyDown(Input.KEY_SPACE)) {
			for (Ball ball : balls) {
				if (!ball.isMoving()) {
					ball.setMoving(true);
				}
			}
		}
		
		// Get input about moving the stick
		for (Stick stick : sticks) {
			stick.update(input, windowSizeX, arg1);
		}
		
		for (Ball ball : balls) {
			// Handle all the ball's movements
			if (ball.isMoving()) {	
				
				ballToBorder(ball);
				for(Stick stick : sticks) {
					ballToStick(ball, stick);
				}
				// Check if there are still bricks
				if (ball.getMinY() <= brickMaxY) {
					if (bricks.size() > 0) {
						// This variable will become the colliding brick to be deleted after the bounce of the ball
						Brick b = null;
						for (Brick brick : bricks) {
							b = ballToBrick(ball, brick);
						}
						// If a brick did collide with the ball, it is removed
						if (b != null) {
							bricks.remove(b);
						}
					}
				}
				else {
					// If there are no brick anymore, game is won
					//TODO WIN - Screen
				}
				ball.move();
			}			
		}
	}

	// Handle collisions with bricks 
	public Brick ballToBrick(Ball ball, Brick brick) {
		if (ball.intersects(brick)) {
			// Handle the reaction of the ball in collision
			if(ball.getCenterY() >= brick.getMinY() && ball.getCenterY() <= brick.getMaxY()) {
				ball.bounce("x");
			}
			else if(ball.getCenterX() >= brick.getMinX() && ball.getCenterX() <= brick.getMaxX()) {
				ball.bounce("y");
			}
			else {
				ball.bounce("angle");
			}
			return brick;
		}
		else {
			return null;
		}
	}

	// Handle collision with the stick
	public void ballToStick(Ball ball,Stick stick) {
		if (ball.intersects(stick)) {
			if (ball.getCenterX() >= stick.getX() && ball.getCenterX() <= stick.getMaxX()) {
				//if (stick.getCenterX() - stick.getMaxX() != 0) {
					//TODO Reaction on stick
					//float f = (stick.getCenterX() - stick.getMaxX()) * 80 / (stick.getWidth() / 2);
					//moveY = -moveY * f / 80;
					//moveX = moveX * (f - 80) / 80;
				//}
				//else {
					ball.bounce("y");
				//}
			}
			else {
				ball.bounce("x");
			}
		}
	}
	
	// Handle collisions with window's Border
	public void ballToBorder(Ball ball) {
		if (ball.getMaxX() >= windowSizeX || ball.getMinX() <= 0) {
			ball.bounce("x");
		}
		if (ball.getMinY() <= 0) {
			ball.bounce("y");
		}
		if (ball.getMinY() > windowSizeY) {
			if(lives > 0) {
				balls.remove(ball);
				balls.add(new Ball(windowSizeX / 2, windowSizeY - BALL_RADIUS * 4, BALL_RADIUS, initialSpeedX, initialSpeedY));
				lives -= 1;
				if(lives <= 0) {
					//TODO GAME OVER - display screen
				}
			}
		}
	}
	
}
