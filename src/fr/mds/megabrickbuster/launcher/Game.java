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
	private static int SIZEX = 30;
	private static int SIZEY = 10;
	
	private ArrayList<Brick> bricks = new ArrayList<>();
	private Stick stick;
	private Ball ball;
	private int windowSizeX, windowSizeY;
	private Input input;
	
	private float moveX = 1.0f;
	private float moveY = -5f;
	
	private int lives = 3;
	
	public Game(String title, int windowSizeX, int windowSizeY) {
		super(title);
		this.windowSizeX = windowSizeX;
		this.windowSizeY = windowSizeY;
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {

		int x = SPACE;
		int y = SPACE;
		for(int i = 0; i < 196; i++) {
			bricks.add(new Brick(x, y, SIZEX, SIZEY));
			x += SIZEX + SPACE;
			if (x >= windowSizeX) {
				x = SPACE;
				y += SIZEY + SPACE;
			}
		}
		stick = new Stick(windowSizeX / 2 - SIZEX, windowSizeY - (SPACE + SIZEY), SIZEX*2, SIZEY);
		ball = new Ball(windowSizeX / 2, stick.getY() - SIZEY * 2, SIZEY * 0.8f);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		for (Brick brick : bricks) {
			brick.render(arg1);
		}
		ball.render(arg1);
		stick.render(arg1);
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		input = arg0.getInput();
		
		// Get an input for start and pause game
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if (ball.isMoving()) {
				ball.setMoving(false);
			}
			else {
				ball.setMoving(true);
			}
		}
		
		// Get input about moving the stick
		stick.update(input, windowSizeX, arg1);
		
		// Handle all the ball's movements
		if (!ball.isOut()) {
			if (ball.isMoving()) {
				ball.setCenterX(ball.getCenterX() + moveX);
				ball.setCenterY(ball.getCenterY() + moveY);
				
				// Handle collisions with window's Border
				if (ball.getMaxX() >= windowSizeX || ball.getMinX() <= 0) {
					moveX = -moveX;
				}
				if (ball.getMinY() <= 0) {
					moveY = -moveY;
				}
				if (ball.getMinY() > windowSizeY) {
					if(lives > 0) {
						ball = new Ball(windowSizeX / 2, stick.getY() - SIZEY * 2, SIZEY * 0.8f);
						moveY = -2f;
						lives -= 1;
					}
				}
				
				// Handle collisions with bricks
				if (bricks.size() > 0) {
					Brick b = null;
					for (Brick brick : bricks) {
						if (ball.intersects(brick)) {
							
							b = brick;						
							
							// Handle the reaction of the ball in collision
							if(ball.getCenterY() >= brick.getMinY() && ball.getCenterY() <= brick.getMaxY()) {
								moveX = -moveX;
							}
							else if(ball.getCenterX() >= brick.getMinX() && ball.getCenterX() <= brick.getMaxX()) {
								moveY = -moveY;
							}
							else {
								float f = moveX;
								moveX = moveY;
								moveY = f;
							}
						}
					}
					if (b != null) {
						bricks.remove(b);
					}
				}
				else {
					//TODO WIN - Screen
				}
				
				// Handle collision with the stick
				if (ball.intersects(stick)) {
					if (ball.getCenterX() >= stick.getX() && ball.getCenterX() <= stick.getMaxX()) {
						//if (stick.getCenterX() - stick.getMaxX() != 0) {
							//TODO Reaction on stick
							//float f = (stick.getCenterX() - stick.getMaxX()) * 80 / (stick.getWidth() / 2);
							//moveY = -moveY * f / 80;
							//moveX = moveX * (f - 80) / 80;
						//}
						//else {
							moveY = -moveY;
						//}
					}
					else {
						moveX = -moveX;
					}
				}
			}
		}
		
		switch (bricks.size()) {
			case 150 :
				moveX *= 1.5f;
				break;
			case 100 : 
				moveX *= 1.5f;
				break;
			case 75 : 
				moveX *= 1.5f;
				break;
			case 50 :
				moveX *= 1.5f;
				break;
			case 25 :
				moveX *= 1.5f;
				break;
		}
			
		
		if(lives <= 0) {
			//TODO GAME OVER - display screen
		}
		
	}

}
