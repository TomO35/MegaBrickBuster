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
	private Image img;

	public Stick(float x, float y, float width, float height) {
		super(x, y, width, height);
		try {
			img = new Image("res/Paddle.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(img, this.minX, this.minY, this.maxX, this.maxY, 0f, 0f, 222f, 36f);
	}
	
	public void update(Input input, int winX, int i) {
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			if (getX() + getWidth() < winX) {
				setX(getX() + speed * i/1000);
			}
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			if (getX() > 0) {
				setX(getX() - speed * i/1000);
			}
		}
	}
	
	/*public float getPosition() {
		float position = getX();     A voir avec Tom
		return position;
	}*/
	
}
