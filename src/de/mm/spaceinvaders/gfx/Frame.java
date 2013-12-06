package de.mm.spaceinvaders.gfx;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.logic.Entity;
import static org.lwjgl.opengl.GL11.*;

public class Frame
{

	private static int width = 800, height = 600, fps = 40;
	
	private boolean exit = false;
	
	private BackgroundCreator background;

	public void init() throws LWJGLException
	{
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.setTitle("SpaceInversion");
		Display.create();
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		background = new BackgroundCreator();
	}

	public void run()
	{
		while (!exit)
		{
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			background.update();
			background.draw();
			
			draw();
			
			Display.sync(fps);

			if (Display.isCloseRequested()) exit = true;
			
			Display.update();
		}
		SpaceInvaders.getInstance().stop();
		Display.destroy();
	}
	
	public void draw() {
		for (Entity e : SpaceInvaders.getInstance().loadedEntities()) e.draw();
	}
}
