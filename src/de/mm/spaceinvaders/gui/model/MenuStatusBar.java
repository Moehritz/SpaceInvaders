package de.mm.spaceinvaders.gui.model;

import lombok.Getter;
import lombok.Setter;

import org.lwjgl.util.Rectangle;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

public class MenuStatusBar extends MenuObject
{
	@Getter
	@Setter
	private float percent;

	@Getter
	@Setter
	private boolean right;

	private ObjectBorder border;

	public MenuStatusBar(Rectangle rect)
	{
		super(rect);
		border = new ObjectBorder(rect);
		border.setBorderLeft(1);
		border.setBorderRight(1);
		border.setBorderDown(2);
		border.setBorderUp(2);

	}

	@Override
	public void draw()
	{
		border.draw();

		glBegin(GL_QUADS);
		int sizeX = (int) (getRect().getWidth() * percent / 100);

		if (right)
		{
			glVertex2i(getRect().getX() + getRect().getWidth() - sizeX, getRect().getY());
			glVertex2i(getRect().getX() + getRect().getWidth(), getRect().getY());
			glVertex2i(getRect().getX() + getRect().getWidth(), getRect().getY() + getRect().getHeight());
			glVertex2i(getRect().getX() + getRect().getWidth() - sizeX, getRect().getY() + getRect().getHeight());
		}
		else
		{
			glVertex2i(getRect().getX(), getRect().getY());
			glVertex2i(getRect().getX() + sizeX, getRect().getY());
			glVertex2i(getRect().getX() + sizeX, getRect().getY() + getRect().getHeight());
			glVertex2i(getRect().getX(), getRect().getY() + getRect().getHeight());
		}

		glEnd();
	}

}
