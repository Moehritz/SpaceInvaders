package de.mm.spaceinvaders.menu;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.TrueTypeFont;

public class MenuButton
{
	private static TrueTypeFont ttf;
	
	public static void initFont() {
		ttf = new TrueTypeFont(new Font("Arial", 1, 20), false);
	}

	private int borderUp = 3, borderRight = 5, borderDown = 3, borderLeft = 5;

	private Rectangle rect;
	private boolean isHover;
	private String text;

	public MenuButton(int x, int y, int width, int height, String text)
	{
		rect = new Rectangle(x, y, width, height);
		this.text = text;
	}

	public MenuButton(Rectangle rect, String text)
	{
		this.rect = rect;
		this.text = text;
	}

	public Rectangle getRectangle()
	{
		return rect;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}

	public void update()
	{
		if (rect.contains(Mouse.getX(), Display.getHeight() - Mouse.getY()))
		{
			isHover = true;
		}
		else
		{
			isHover = false;
		}
	}

	public void draw()
	{
		if (isHover)
		{
			glColor4f(0.7f, 0.7f, 1.0f, 1.0f);
		}
		else
		{
			glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		}
		glBegin(GL_QUADS);
		{
			glVertex2i(rect.getX(), rect.getY());
			glVertex2i(rect.getX() + rect.getWidth(), rect.getY());
			glVertex2i(rect.getX() + rect.getWidth(), rect.getY() + borderUp);
			glVertex2i(rect.getX(), rect.getY() + borderUp);

			glVertex2i(rect.getX() + rect.getWidth() - borderRight, rect.getY());
			glVertex2i(rect.getX() + rect.getWidth(), rect.getY());
			glVertex2i(rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight());
			glVertex2i(rect.getX() + rect.getWidth() - borderRight, rect.getY() + rect.getHeight());

			glVertex2i(rect.getX(), rect.getY() + rect.getHeight() - borderDown);
			glVertex2i(rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight() - borderDown);
			glVertex2i(rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight());
			glVertex2i(rect.getX(), rect.getY() + rect.getHeight());

			glVertex2i(rect.getX(), rect.getY());
			glVertex2i(rect.getX() + borderRight, rect.getY());
			glVertex2i(rect.getX() + borderRight, rect.getY() + rect.getHeight());
			glVertex2i(rect.getX(), rect.getY() + rect.getHeight());

		}
		glEnd();

		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		int fx = rect.getX() + (rect.getWidth() / 2) - (ttf.getWidth(text) / 2);
		int fy = rect.getY() + (rect.getHeight() / 2) - (ttf.getHeight(text) / 2);
		ttf.drawString(fx, fy, text);
	}

	public int getBorderUp()
	{
		return borderUp;
	}

	public void setBorderUp(int borderUp)
	{
		this.borderUp = borderUp;
	}

	public int getBorderRight()
	{
		return borderRight;
	}

	public void setBorderRight(int borderRight)
	{
		this.borderRight = borderRight;
	}

	public int getBorderDown()
	{
		return borderDown;
	}

	public void setBorderDown(int borderDown)
	{
		this.borderDown = borderDown;
	}

	public int getBorderLeft()
	{
		return borderLeft;
	}

	public void setBorderLeft(int borderLeft)
	{
		this.borderLeft = borderLeft;
	}

}
