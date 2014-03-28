package de.mm.spaceinvaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;

import org.lwjgl.LWJGLException;

import de.mm.spaceinvaders.gfx.Frame;
import de.mm.spaceinvaders.gfx.Textures;
import de.mm.spaceinvaders.gui.IngameGui;
import de.mm.spaceinvaders.gui.MainMenu;
import de.mm.spaceinvaders.logic.ControllablePlayer;
import de.mm.spaceinvaders.logic.Level;
import de.mm.spaceinvaders.logic.Ticker;
import de.mm.spaceinvaders.logic.levels.*;

@Getter
public class SpaceInvaders
{

	@Getter
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

	private Frame frame;
	private Ticker ticker;
	private MainMenu mainMenu = new MainMenu();
	private IngameGui ingameMenu = new IngameGui();
	private ControllablePlayer thePlayer;
	private Level currLevel;
	private List<Class<? extends Level>> levels;
	private Iterator<Class<? extends Level>> levelIterator;

	private void start() throws IOException
	{
		levels = new ArrayList<Class<? extends Level>>();

		levels.add(Level01.class);
		levels.add(Level02.class);
		levels.add(Level03.class);
		levels.add(Level04.class);
		// TODO More levels ;)

		levelIterator = levels.iterator();

		frame = new Frame();
		try
		{
			frame.init();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
		frame.setMenu(new MainMenu());

		currLevel = new MenuLevel();

		ticker = new Ticker();

		frame.run();
	}

	public void stop()
	{
		ticker.stop();
		
		frame.setMenu(new MainMenu());

		currLevel = new MenuLevel();
	}

	public void startNewGame()
	{
		frame.setMenu(ingameMenu);

		levelIterator = levels.iterator();

		nextLevel();

		ticker.start();
	}

	public void nextLevel()
	{
		try
		{
			if (levelIterator.hasNext())
			{
				currLevel = levelIterator.next().newInstance();
			}
			else
			{
				stop();
			}
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			System.err.println("Please send this to the author");
			e.printStackTrace();
		}

		ControllablePlayer player = new ControllablePlayer(Textures.PLAYER.getTexture());

		currLevel.getOutstandingSpawns().add(player);

		thePlayer = player;
	}

}
