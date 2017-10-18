package fr.mds.megabrickbuster.model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public abstract class InteractiveElement extends Shape {

	private float sizeX, sizeY;
	private Image image;
	float scale = 1.0f;

	public InteractiveElement(float x, float y, float sizeX, float sizeY, String image) throws SlickException {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.image = new Image(image);
	}

	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		image.draw(x, y, scale);
	}

	public float getSizeX() {
		return sizeX;
	}

	public void setSizeX(float sizeX) {
		this.sizeX = sizeX;
	}

	public float getSizeY() {
		return sizeY;
	}

	public void setSizeY(float sizeY) {
		this.sizeY = sizeY;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
}
