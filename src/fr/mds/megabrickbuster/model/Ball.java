package fr.mds.megabrickbuster.model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;


/***
 * The Ball is the free moving element of the game.
 * The ball tells us to things :
 * If it is moving and if it is out of screen.
 */

public class Ball extends Circle {
	
	private boolean isMoving, isOut;
	
	public Ball(float centerPointX, float centerPointY, float radius) {
		super(centerPointX, centerPointY, radius);
		isMoving = false;
		isOut = false;
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
		g.draw(this);
		g.setColor(Color.red);
		//g.drawImage(new Image("res/Ball.jpg"), x, y);
	}

}
