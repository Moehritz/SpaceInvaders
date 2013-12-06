package de.mm.spaceinvaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import de.mm.spaceinvaders.gfx.Frame;
import de.mm.spaceinvaders.logic.ControllablePlayer;
import de.mm.spaceinvaders.logic.Entity;
import de.mm.spaceinvaders.logic.Ticker;

public class SpaceInvaders
{

	private static SpaceInvaders instance;

	public static void main(String[] args)
	{
		instance = new SpaceInvaders();
		try
		{
			instance.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static SpaceInvaders getInstance()
	{
		return instance;
	}

	private Frame frame;
	private List<Entity> entities;
	private Ticker ticker;

	private void start() throws IOException
	{
		entities = new ArrayList<>();

		frame = new Frame();
		try
		{
			frame.init();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}

		ControllablePlayer player = new ControllablePlayer();
		player.setTexture(TextureLoader.getTexture("PNG",
				ResourceLoader.getResourceAsStream("res/player.png")));

		entities.add(player);

		ticker = new Ticker();
		ticker.start();

		frame.run();
	}

	public List<Entity> loadedEntities()
	{
		return entities;
	}

	public void stop()
	{
		ticker.stop();
	}

}
