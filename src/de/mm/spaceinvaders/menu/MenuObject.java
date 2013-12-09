package de.mm.spaceinvaders.menu;

import java.awt.Font;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.TrueTypeFont;

import de.mm.spaceinvaders.gfx.Drawable;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class MenuObject implements Drawable
{
	protected static TrueTypeFont ttf;

	public static void initFont()
	{
		ttf = new TrueTypeFont(new Font("Arial", 1, 20), false);
	}
	
	@NonNull
	private Rectangle rect;
	private boolean hover = false;

	public void update()
	{
		if (getRect().contains(Mouse.getX(), Display.getHeight() - Mouse.getY()))
		{
			hover = true;
		}
		else
		{
			hover = false;
		}
	}
}
