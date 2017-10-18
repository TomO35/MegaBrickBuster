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

	public Stick(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public void render(Graphics g) {
		try {
			g.drawImage(new Image("res/Stick.png"), x, y);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Input input, int winX, int arg1) {
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			if (getX() + getWidth() < winX) {
				setX(getX() + 0.2f * arg1);
			}
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			if (getX() > 0) {
				setX(getX() - 0.2f * arg1);
			}
		}
	}
	
}
