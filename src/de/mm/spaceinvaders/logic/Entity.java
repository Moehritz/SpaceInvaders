package de.mm.spaceinvaders.logic;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Entity
{

	private long x = 200, y = 200, health, width, height;
	private double rotation = 0, speedX, speedY;
	private Texture texture;

	public long getX()
	{
		return x;
	}

	public long getY()
	{
		return y;
	}

	public long getHealth()
	{
		return health;
	}

	public void setX(long x)
	{
		this.x = x;
	}

	public void setY(long y)
	{
		this.y = y;
	}

	public void setHealth(long health)
	{
		this.health = health;
	}

	public double getRotation()
	{
		return rotation;
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

	public void setRotation(double rotation)
	{
		this.rotation = rotation;
	}

	public Texture getTexture()
	{
		return texture;
	}

	public void setTexture(Texture texture)
	{
		this.texture = texture;
		width = texture.getImageWidth() * 3;
		height = texture.getImageHeight() * 3;
	}

	public long getWidth()
	{
		return width;
	}

	public void setWidth(long width)
	{
		this.width = width;
	}

	public long getHeight()
	{
		return height;
	}

	public void setHeight(long height)
	{
		this.height = height;
	}

	public void updatePosition()
	{
		x += speedX;
		y += speedY;
	}

	public void draw()
	{
		texture.bind();
		glTranslated(x, y, 0);
		glRotated(rotation, 0, 0, 1);
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
		glRotated(-rotation, 0, 0, 1);
		glTranslated(-x, -y, 0);

	}

	public double getSpeedX()
	{
		return speedX;
	}

	public void setSpeedX(double speedX)
	{
		this.speedX = speedX;
	}

	public double getSpeedY()
	{
		return speedY;
	}

	public void setSpeedY(double speedY)
	{
		this.speedY = speedY;
	}

}
