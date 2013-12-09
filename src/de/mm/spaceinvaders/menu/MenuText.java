package de.mm.spaceinvaders.menu;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.TrueTypeFont;

public class MenuText extends MenuObject
{

	private String text;
	private TrueTypeFont ttf;

	public MenuText(Rectangle rect, String text, float size)
	{
		super(rect);
		this.text = text;
		ttf = new TrueTypeFont(font.deriveFont(size), false);
	}

	@Override
	public void draw()
	{
		int fx = getRect().getX() + (getRect().getWidth() / 2) - (ttf.getWidth(text) / 2);
		int fy = getRect().getY() + (getRect().getHeight() / 2) - (ttf.getHeight(text) / 2);
		ttf.drawString(fx, fy, text);
	}

}
