package de.mm.spaceinvaders.menu;

import static org.lwjgl.opengl.GL11.*;

import lombok.Getter;
import lombok.Setter;

import org.lwjgl.util.Rectangle;

@Getter
@Setter
public class MenuButton extends MenuObject
{
	private String text;
	private ObjectBorder border;

	public MenuButton(int x, int y, int width, int height, String text)
	{
		super(new Rectangle(x, y, width, height));
		this.text = text;
		border = new ObjectBorder(getRect());
	}

	public MenuButton(Rectangle rect, String text)
	{
		super(rect);
		this.text = text;
		border = new ObjectBorder(getRect());
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

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		int fx = getRect().getX() + (getRect().getWidth() / 2) - (ttf.getWidth(text) / 2);
		int fy = getRect().getY() + (getRect().getHeight() / 2) - (ttf.getHeight(text) / 2);
		ttf.drawString(fx, fy, text);
	}

}
