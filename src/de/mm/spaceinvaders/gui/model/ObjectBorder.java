package de.mm.spaceinvaders.gui.model;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.lwjgl.util.Rectangle;
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
	private Rectangle rect;
	private int borderUp = 3, borderRight = 5, borderDown = 3, borderLeft = 5;

	@Override
	public void draw()
	{
		TextureImpl.bindNone();
		glBegin(GL_QUADS);

		glVertex2i(getRect().getX(), getRect().getY());
		glVertex2i(getRect().getX() + getRect().getWidth(), getRect().getY());
		glVertex2i(getRect().getX() + getRect().getWidth(), getRect().getY() + borderUp);
		glVertex2i(getRect().getX(), getRect().getY() + borderUp);

		glVertex2i(getRect().getX() + getRect().getWidth() - borderRight, getRect()
				.getY());
		glVertex2i(getRect().getX() + getRect().getWidth(), getRect().getY());
		glVertex2i(getRect().getX() + getRect().getWidth(), getRect().getY()
				+ getRect().getHeight());
		glVertex2i(getRect().getX() + getRect().getWidth() - borderRight, getRect()
				.getY() + getRect().getHeight());

		glVertex2i(getRect().getX(), getRect().getY() + getRect().getHeight()
				- borderDown);
		glVertex2i(getRect().getX() + getRect().getWidth(), getRect().getY()
				+ getRect().getHeight() - borderDown);
		glVertex2i(getRect().getX() + getRect().getWidth(), getRect().getY()
				+ getRect().getHeight());
		glVertex2i(getRect().getX(), getRect().getY() + getRect().getHeight());

		glVertex2i(getRect().getX(), getRect().getY());
		glVertex2i(getRect().getX() + borderRight, getRect().getY());
		glVertex2i(getRect().getX() + borderRight, getRect().getY()
				+ getRect().getHeight());
		glVertex2i(getRect().getX(), getRect().getY() + getRect().getHeight());

		glEnd();
	}
}
