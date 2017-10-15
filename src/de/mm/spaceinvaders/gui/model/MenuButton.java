package de.mm.spaceinvaders.gui.model;

import static org.lwjgl.opengl.GL11.*;

import java.awt.geom.Rectangle2D;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuButton extends MenuObject
{
	private Text text;
	private ObjectBorder border;

	public MenuButton(Rectangle2D.Double rect, Text text)
	{
		super(rect);
		this.text = text;
		border = new ObjectBorder(getRect());
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
		text.writeToScreen(getRect().x, getRect().y, getRect().width, getRect().height);
	}
}
