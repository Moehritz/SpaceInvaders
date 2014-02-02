package de.mm.spaceinvaders.logic;

import lombok.Getter;
import lombok.Setter;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;

import de.mm.spaceinvaders.Vector;
import de.mm.spaceinvaders.gfx.Drawable;
import static org.lwjgl.opengl.GL11.*;

@Getter
@Setter
public class Entity implements Drawable
{

	private float x = 200, y = 200, health, width, height;
	private double rotation = 0;
	private Vector speed = new Vector();
	private Texture texture;
	private boolean visible = true;

	public Entity(Texture texture)
	{
		setTexture(texture);
	}

	/**
	 * NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3
	 */
	public boolean outOfBounds(int side)
	{
		switch (side)
		{
		case 0:
			return y + height / 2 > Display.getHeight();
		case 1:
			return x + width / 2 > Display.getWidth();
		case 2:
			return y - height / 2 < 0;
		case 3:
			return x - width / 2 < 0;
		}
		return false;
	}

	public void setTexture(Texture texture)
	{
		this.texture = texture;
		width = texture.getImageWidth() * 3;
		height = texture.getImageHeight() * 3;
	}

	public boolean updatePosition()
	{
		boolean ret = true;
		if (!outOfBounds(1) && !outOfBounds(3))
		{
			x += speed.getX();
		}
		else
		{
			ret = false;
		}
		if (!outOfBounds(0) && !outOfBounds(2))
		{
			y += speed.getY();
		}
		else
		{
			ret = false;
		}
		return ret;
	}

	@Override
	public void draw()
	{
		float rx = x, ry = y;
		double rrot = rotation;
		texture.bind();
		glColor3f(1.0f, 1.0f, 1.0f);
		glTranslated(rx, ry, 0);
		glRotated(rrot, 0, 0, 1);
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0.0f, 0.0f);
			glVertex2f(-width / 2, -height / 2);
			glTexCoord2f(1.0f, 0.0f);
			glVertex2f(width / 2, -height / 2);
			glTexCoord2f(1.0f, 1.0f);
			glVertex2f(width / 2, height / 2);
			glTexCoord2f(0.0f, 1.0f);
			glVertex2f(-width / 2, height / 2);
		}
		glEnd();
		glRotated(-rrot, 0, 0, 1);
		glTranslated(-rx, -ry, 0);
		TextureImpl.bindNone();
	}
}
