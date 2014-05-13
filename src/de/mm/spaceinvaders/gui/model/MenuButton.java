package de.mm.spaceinvaders.gui.model;

import static org.lwjgl.opengl.GL11.*;
import lombok.Getter;
import lombok.Setter;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import de.mm.spaceinvaders.gfx.TextDrawable;

@Getter
@Setter
public class MenuButton extends MenuObject implements TextDrawable
{
	private String text;
	private ObjectBorder border;
	private TrueTypeFont ttf;

	public MenuButton(Rectangle rect, String text, float size)
	{
		super(rect);
		this.text = text;
		border = new ObjectBorder(getRect());
		ttf = new TrueTypeFont(font.deriveFont(size), false);
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}

	@Override
	public void draw()
	{
		if (isHover())
		{
			glColor4f(0.7f, 0.7f, 1.0f, 1.0f);
		}
		else
		{
			glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		}

		border.draw();
	}

	@Override
	public void drawText()
	{
		int fx = getRect().getX() + (getRect().getWidth() / 2) - (ttf.getWidth(text) / 2);
		int fy = getRect().getY() + (getRect().getHeight() / 2)
				- (ttf.getHeight(text) / 2);
		Color color = Color.white;
		if (isHover())
		{
			color = Color.red;
		}
		ttf.drawString(fx, fy, text, color);
	}

}
