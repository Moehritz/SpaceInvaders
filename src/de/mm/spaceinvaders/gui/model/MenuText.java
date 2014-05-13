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
	@Getter
	private float fontSize;
	private boolean fontInitialized = false;

	public MenuText(Rectangle rect, String text, float size, Color color)
	{
		super(rect);
		this.text = text;
		this.fontSize = size;
		this.color = color;
	}

	public void initThisFont()
	{
		ttf = new TrueTypeFont(font.deriveFont(fontSize), false);
		fontInitialized = true;
	}

	@Override
	public void drawText()
	{
		if (!fontInitialized) initThisFont();
		int fx = getRect().getX(); // LEFT
		if (alignment == TextAlignment.CENTER)
		{
			fx += (getRect().getWidth() / 2) - (ttf.getWidth(text) / 2);
		}
		else if (alignment == TextAlignment.RIGHT)
		{
			fx += getRect().getWidth() - ttf.getWidth(text);
		}
		int fy = getRect().getY() + (getRect().getHeight() / 2)
				- (ttf.getHeight(text) / 2);
		ttf.drawString(fx, fy, text, color);
	}

	public void setSize(float size)
	{
		fontInitialized = false;
		this.fontSize = size;
	}

	public enum TextAlignment
	{
		LEFT, CENTER, RIGHT
	}

	@Override
	public void draw()
	{

	}

}