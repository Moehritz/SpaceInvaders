package de.mm.spaceinvaders.menu;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import de.mm.spaceinvaders.gfx.TextDrawable;

public class MenuText extends MenuObject implements TextDrawable
{

	private String text;
	private TrueTypeFont ttf;
	private Color color;

	public MenuText(Rectangle rect, String text, float size, Color color)
	{
		super(rect);
		this.text = text;
		ttf = new TrueTypeFont(font.deriveFont(size), false);
		this.color = color;
	}
	
	@Override
	public void drawText() {
		int fx = getRect().getX() + (getRect().getWidth() / 2) - (ttf.getWidth(text) / 2);
		int fy = getRect().getY() + (getRect().getHeight() / 2) - (ttf.getHeight(text) / 2);
		ttf.drawString(fx, fy, text, color);
	}

	@Override
	public void draw()
	{
		
	}

}
