package de.mm.spaceinvaders.menu;

import org.lwjgl.util.Rectangle;

public class MenuText extends MenuObject
{

	private String text;

	public MenuText(Rectangle rect, String text)
	{
		super(rect);
		this.text = text;
	}

	@Override
	public void draw()
	{
		int fx = getRect().getX() + (getRect().getWidth() / 2) - (ttf.getWidth(text) / 2);
		int fy = getRect().getY() + (getRect().getHeight() / 2) - (ttf.getHeight(text) / 2);
		ttf.drawString(fx, fy, text);
	}

}
