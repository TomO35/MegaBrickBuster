package fr.mds.megabrickbuster.model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;


/***
 * The Ball is the free moving element of the game.
 * The ball tells us two things :
 * If it is moving and if it is out of screen.
 */

public class Ball extends Circle {
	
	private boolean isMoving, isOut;
	private float speedX, speedY;
	private Image img;
	
	public Ball(float centerPointX, float centerPointY, float radius, float speedX, float speedY) {
		super(centerPointX, centerPointY, radius);
		isMoving = false;
		isOut = false;
		this.speedX = speedX;
		this.speedY = speedY;
		try {
			this.img = new Image("res/Ball.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public boolean isOut() {
		return isOut;
	}

	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}
	
	public void render(Graphics g) {
		g.drawImage(img, this.minX, this.minY, this.maxX, this.maxY, 0f, 0f, 27f, 27f);
	}
	
	public void move() {
		setCenterX(getCenterX() + speedX);
		setCenterY(getCenterY() + speedY);
	}
	
	public void bounce(String s) {
		if (s == "x") {
			speedX = -speedX;
		}
		else if (s == "y") {
			speedY = -speedY;
		}
		else if (s == "angle") {
			float f = speedX;
			speedX = speedY;
			speedY = f;
		}
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
	
}
