package de.mm.spaceinvaders.logic;

import java.util.UUID;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import de.mm.spaceinvaders.gfx.Drawable;
import de.mm.spaceinvaders.util.Vector;
import static org.lwjgl.opengl.GL11.*;

@Getter
@Setter
public class Entity implements Drawable
{

	@NonNull
	private final String uuid;

	private double width, height;
	private double x;
	private double y;
	private double rotation = 0;
	private Vector speed = new Vector();
	private Texture texture;
	private boolean visible = true;
	private byte type = 0;

	public Entity(Texture texture)
	{
		this(texture, UUID.randomUUID().toString());
	}

	public Entity(Texture texture, String uuid)
	{
		setTexture(texture);
		this.uuid = uuid;
	}

	/**
	 * UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3
	 */
	public boolean outOfBounds(int side)
	{
		switch (side)
		{
		case 0:
			return y + height / 2 > 1;
		case 1:
			return x + width / 2 > 1;
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
		width = texture.getImageWidth() * 0.003125;
		height = texture.getImageHeight() * 0.003125;
	}

	public boolean updatePosition(long delta)
	{
		boolean ret = true;
		if ((!outOfBounds(1) && speed.getX() > 0)
				|| (!outOfBounds(3) && speed.getX() < 0))
		{
			x += speed.getX() * (double) delta;
		}
		else
		{
			ret = false;
		}
		if ((!outOfBounds(0) && speed.getY() > 0)
				|| (!outOfBounds(2) && speed.getY() < 0))
		{
			y += speed.getY() * (double) delta;
		}
		else
		{
			ret = false;
		}

		if (speed.getX() < 0.00001 && speed.getX() > -0.00001) speed.setX(0);
		if (speed.getY() < 0.00001 && speed.getY() > -0.00001) speed.setY(0);
		return ret;
	}

	@Override
	public void draw()
	{
		//if (!visible) return;
		double rx = x, ry = y;
		texture.bind();
		double rotation = this.rotation;
		glColor3f(1.0f, 1.0f, 1.0f);
		glTranslated(rx, ry, 0);

		if (Display.getHeight() < Display.getWidth())
		{
			glScaled(1, Display.getWidth() / (double) Display.getHeight(), 1);
		}
		else
		{
			glScaled(Display.getHeight() / (double) Display.getWidth(), 1, 1);
		}

		if (!Double.isNaN(rotation))
		{
			glRotated(rotation, 0, 0, 1);
		}

		glBegin(GL_QUADS);
		{
			glTexCoord2d(0.0f, 0.0f);
			glVertex2d(-width / 2, -height / 2);
			glTexCoord2d(1.0f, 0.0f);
			glVertex2d(width / 2, -height / 2);
			glTexCoord2d(1.0f, 1.0f);
			glVertex2d(width / 2, height / 2);
			glTexCoord2d(0.0f, 1.0f);
			glVertex2d(-width / 2, height / 2);
		}

		glEnd();

		glLoadIdentity();
	}
}
