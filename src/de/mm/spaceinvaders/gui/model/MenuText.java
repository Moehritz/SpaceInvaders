package de.mm.spaceinvaders.gui.model;

import lombok.Getter;
import lombok.Setter;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import de.mm.spaceinvaders.gfx.TextDrawable;

public class MenuText extends MenuObject implements TextDrawable
{
	@Setter
	@Getter
	private String text;
	private TrueTypeFont ttf;
	@Setter
	@Getter
	private Color color;
	@Getter
	@Setter
	private TextAlignment alignment = TextAlignment.CENTER;

	public MenuText(Rectangle rect, String text, float size, Color color)
	{
		super(rect);
		this.text = text;
		setSize(size);
		this.color = color;
	}

	public void setSize(float size)
	{
		ttf = new TrueTypeFont(font.deriveFont(size), false);
	}

	@Override
	public void drawText()
	{
		int fx = getRect().getX(); // LEFT
		if (alignment == TextAlignment.CENTER)
		{
			fx += (getRect().getWidth() / 2) - (ttf.getWidth(text) / 2);
		}
		else if (alignment == TextAlignment.RIGHT)
		{
			fx += getRect().getWidth() - ttf.getWidth(text);
		}
		int fy = getRect().getY() + (getRect().getHeight() / 2) - (ttf.getHeight(text) / 2);
		ttf.drawString(fx, fy, text, color);
	}

	@Override
	public void draw()
	{

	}

	public enum TextAlignment
	{
		LEFT, CENTER, RIGHT
	}

}
