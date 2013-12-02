package de.mm.spaceinvaders.gfx;

import org.lwjgl.opengl.Display;

public class Frame
{

	private boolean exit = false;

	public void init()
	{
		// OpenGL init Code hier
	}

	public void run()
	{
		while (!exit)
		{
			// Rendern - Hier muss kein Teil der Logik hin

			if (Display.isCloseRequested()) exit = true;
		}
	}
}
