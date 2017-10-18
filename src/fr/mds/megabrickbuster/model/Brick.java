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

	public Brick(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public void render(Graphics g) {
		try {
			g.drawImage(new Image("res/Brick.jpg"), x, y);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
