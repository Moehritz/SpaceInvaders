package de.mm.spaceinvaders.gui.model;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.awt.geom.Rectangle2D;

import org.newdawn.slick.opengl.TextureImpl;

import de.mm.spaceinvaders.gfx.Drawable;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ObjectBorder implements Drawable
{
	@NonNull
	private Rectangle2D rect;
	private double borderUp = 0.004, borderRight = 0.007, borderDown = 0.004, borderLeft = 0.007;

	@Override
	public void draw()
	{
		TextureImpl.bindNone();
		glBegin(GL_QUADS);

		glVertex2d(getRect().getX(), getRect().getY());
		glVertex2d(getRect().getX() + getRect().getWidth(), getRect().getY());
		glVertex2d(getRect().getX() + getRect().getWidth(), getRect().getY() + borderUp);
		glVertex2d(getRect().getX(), getRect().getY() + borderUp);

		glVertex2d(getRect().getX() + getRect().getWidth() - borderRight, getRect()
				.getY());
		glVertex2d(getRect().getX() + getRect().getWidth(), getRect().getY());
		glVertex2d(getRect().getX() + getRect().getWidth(), getRect().getY()
				+ getRect().getHeight());
		glVertex2d(getRect().getX() + getRect().getWidth() - borderRight, getRect()
				.getY() + getRect().getHeight());

		glVertex2d(getRect().getX(), getRect().getY() + getRect().getHeight()
				- borderDown);
		glVertex2d(getRect().getX() + getRect().getWidth(), getRect().getY()
				+ getRect().getHeight() - borderDown);
		glVertex2d(getRect().getX() + getRect().getWidth(), getRect().getY()
				+ getRect().getHeight());
		glVertex2d(getRect().getX(), getRect().getY() + getRect().getHeight());

		glVertex2d(getRect().getX(), getRect().getY());
		glVertex2d(getRect().getX() + borderRight, getRect().getY());
		glVertex2d(getRect().getX() + borderRight, getRect().getY()
				+ getRect().getHeight());
		glVertex2d(getRect().getX(), getRect().getY() + getRect().getHeight());

		glEnd();
	}
}
