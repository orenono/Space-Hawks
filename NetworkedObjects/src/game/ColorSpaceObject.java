package game;

import java.awt.Color;
import java.awt.Graphics;

public class ColorSpaceObject extends SpaceObjectDecorator {
	private Color color;
	
	public ColorSpaceObject(SpaceObject decoratedObject, Color color) {
		super(decoratedObject);
		this.color = color;
	}
	
	@Override
	public void paint(Graphics g) {
		Color prevColor = g.getColor();
		g.setColor(color);
		super.paint(g);
		g.setColor(prevColor);
	}
}
