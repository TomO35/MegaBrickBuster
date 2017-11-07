package fr.mds.megabrickbuster.model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class MenuButton extends Rectangle {
	
	private String title;

	public MenuButton(float x, float y, float width, float height, String title) {
		super(x, y, width, height);
		this.title = title;
	}
	
	public void render(Graphics g) {
		g.draw(this);
		g.setColor(Color.red);
		g.drawString(title, this.minX + 4, this.minY + 4);
	}
	
}
