package de.mm.spaceinvaders.gui.model;

import java.awt.geom.Rectangle2D;

import lombok.Getter;
import lombok.Setter;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;

public class MenuStatusBar extends MenuObject
{
	@Getter
	@Setter
	private float percent;

	@Getter
	@Setter
	private boolean right;

	private ObjectBorder border;

	public MenuStatusBar(Rectangle2D.Double rect)
	{
		super(rect);
		border = new ObjectBorder(rect);
	}

	@Override
	public void draw()
	{
		border.draw();

		glBegin(GL_QUADS);
		int sizeX = (int) (getRect().getWidth() * percent / 100);

		if (right)
		{
			glVertex2d(getRect().getX() + getRect().getWidth() - sizeX, getRect().getY());
			glVertex2d(getRect().getX() + getRect().getWidth(), getRect().getY());
			glVertex2d(getRect().getX() + getRect().getWidth(), getRect().getY()
					+ getRect().getHeight());
			glVertex2d(getRect().getX() + getRect().getWidth() - sizeX, getRect().getY()
					+ getRect().getHeight());
		}
		else
		{
			glVertex2d(getRect().getX(), getRect().getY());
			glVertex2d(getRect().getX() + sizeX, getRect().getY());
			glVertex2d(getRect().getX() + sizeX, getRect().getY() + getRect().getHeight());
			glVertex2d(getRect().getX(), getRect().getY() + getRect().getHeight());
		}

		glEnd();
	}

}
