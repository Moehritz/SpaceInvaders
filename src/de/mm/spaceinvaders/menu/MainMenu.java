package de.mm.spaceinvaders.menu;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;

public class MainMenu extends Menu
{

	@Override
	public void init()
	{
		addObject(new MenuText(new Rectangle(20, 20, Display.getWidth() - 40, 100),
				"SPACE INVERSION", 50));
	}

}
