package de.mm.spaceinvaders.gui.model;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import de.mm.spaceinvaders.gfx.Drawable;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class MenuObject implements Drawable
{
	private static boolean mouseDown = false;
	protected static Font font;
	protected static TrueTypeFont fontx;

	public static void initFont()
	{
		font = new Font("Times New Roman", Font.PLAIN, 10);
		fontx = new TrueTypeFont(font, false);
	}

	@NonNull
	private Rectangle2D.Double rect;
	private boolean hover = false, clicked = false;
	private MenuActionListener listener;

	public void update()
	{
		double mX = Mouse.getX() == 0 ? 0 : 1 / (Display.getWidth() / (double) Mouse
				.getX());
		double mY = 1 - (Mouse.getY() == 0 ? 0
				: 1 / (Display.getHeight() / (double) Mouse.getY()));
		if (getRect().contains(mX, mY))
		{
			if (hover == false && listener != null)
			{
				listener.onHover();
			}
			hover = true;
			if (Mouse.isButtonDown(0))
			{
				if (!mouseDown)
				{
					if (clicked == false && listener != null)
					{
						listener.onClick();
					}
					clicked = true;
				}
				mouseDown = true;
			}
			else
			{
				mouseDown = false;
				clicked = false;
			}
		}
		else
		{
			hover = false;
		}

	}
}
