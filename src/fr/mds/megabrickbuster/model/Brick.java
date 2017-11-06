package fr.mds.megabrickbuster.model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/***
 * The brick is the element getting destroyed when touched by the ball.
 * 
 */

public class Brick extends Rectangle {
	
	private Image img;

	public Brick(float x, float y, float width, float height) {
		super(x, y, width, height);
		try {
			img = new Image("res/Brick.jpg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(img, x, y);
	}
}
