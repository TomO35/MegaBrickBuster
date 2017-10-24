package fr.mds.megabrickbuster.model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/***
 * The stick is an element moved by the gamer.
 */
public class Stick extends Rectangle {
	
	private static float speed = 200f;
	Image img;

	public Stick(float x, float y, float width, float height) {
		super(x, y, width, height);
		try {
			img = new Image("res/Stick.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(img, x, y);
	}
	
	public void update(Input input, int winX, int arg1) {
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			if (getX() + getWidth() < winX) {
				setX(getX() + speed * arg1/1000);
			}
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			if (getX() > 0) {
				setX(getX() - speed * arg1/1000);
			}
		}
	}
	
}
