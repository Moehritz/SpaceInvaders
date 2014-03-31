package de.mm.spaceinvaders.gfx;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.util.ResourceLoader;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuObject;
import de.mm.spaceinvaders.logic.Entity;
import static org.lwjgl.opengl.GL11.*;

public class Frame
{

	private static int width = 800, height = 600, fps = 40;

	private boolean exit = false;

	private Menu currentMenu;

	public void init() throws LWJGLException
	{
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.setTitle("SpaceInversion");
		Display.setVSyncEnabled(true);
		Display.create();

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		try
		{
			Mouse.setNativeCursor(new Cursor(16, 16, 8, 8, 1, getHandMousePointer(), null));
		}
		catch (MalformedURLException | URISyntaxException e)
		{
			e.printStackTrace();
		}

		MenuObject.initFont();
	}

	public static IntBuffer getHandMousePointer() throws MalformedURLException, URISyntaxException
	{
		Image c = Toolkit.getDefaultToolkit().getImage(ResourceLoader.getResource("res/cursor.png").toURI().toURL());
		BufferedImage biCursor = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		while (!biCursor.createGraphics().drawImage(c, 0, 15, 15, 0, 0, 0, 15, 15, null))
			try
			{
				Thread.sleep(5);
			}
			catch (InterruptedException e)
			{
			}

		int[] data = biCursor.getRaster().getPixels(0, 0, 16, 16, (int[]) null);

		IntBuffer ib = BufferUtils.createIntBuffer(16 * 16);
		for (int i = 0; i < data.length; i += 4)
			ib.put(data[i] | data[i + 1] << 8 | data[i + 2] << 16 | data[i + 3] << 24);
		ib.flip();
		return ib;
	}

	public void setMenu(Menu menu)
	{
		if (menu != null)
		{
			currentMenu = menu;
		}
	}

	public void run()
	{
		while (!exit)
		{
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			StarBackground bg = SpaceInvaders.getInstance().getBackground();

			bg.update();
			bg.draw();

			if (currentMenu != null)
			{
				currentMenu.draw();
			}

			draw();

			TextureImpl.bindNone();

			Display.sync(fps);

			if (Display.isCloseRequested()) exit = true;

			Display.update();
		}
		SpaceInvaders.getInstance().toMainMenu();
		Display.destroy();
	}

	public void draw()
	{
		if (!currentMenu.isInitialized())
		{
			currentMenu.init();
		}

		List<Entity> allEntities = new ArrayList<>(SpaceInvaders.getInstance().getEntities());
		for (Entity e : allEntities)
		{
			if (e.isVisible())
			{
				e.draw();
			}
		}
	}
}
