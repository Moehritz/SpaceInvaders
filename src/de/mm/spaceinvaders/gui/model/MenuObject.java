package de.mm.spaceinvaders.gui.model;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.util.ResourceLoader;

import de.mm.spaceinvaders.gfx.Drawable;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class MenuObject implements Drawable
{
	private static boolean mouseDown = false;
	protected static Font font;

	public static void initFont()
	{
		try
		{
			font = Font.createFont(Font.TRUETYPE_FONT,
					ResourceLoader.getResourceAsStream("res/arcade.ttf"));
		}
		catch (FontFormatException | IOException e)
		{
			e.printStackTrace();
		}
	}

	@NonNull
	private Rectangle rect;
	private boolean hover = false, clicked = false;
	private MenuActionListener listener;

	public void update()
	{
		if (getRect().contains(Mouse.getX(), Display.getHeight() - Mouse.getY()))
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
