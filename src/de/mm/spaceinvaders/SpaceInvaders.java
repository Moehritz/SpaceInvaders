package de.mm.spaceinvaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.lwjgl.LWJGLException;

import de.mm.spaceinvaders.client.Client;
import de.mm.spaceinvaders.gfx.Frame;
import de.mm.spaceinvaders.gfx.StarBackground;
import de.mm.spaceinvaders.gfx.Textures;
import de.mm.spaceinvaders.gui.IngameGui;
import de.mm.spaceinvaders.gui.MainMenu;
import de.mm.spaceinvaders.gui.ServerMenu;
import de.mm.spaceinvaders.io.ConnectionHandler;
import de.mm.spaceinvaders.logic.Bullet;
import de.mm.spaceinvaders.logic.ControllablePlayer;
import de.mm.spaceinvaders.logic.Entity;
import de.mm.spaceinvaders.logic.ScoreManager;
import de.mm.spaceinvaders.logic.Ticker;
import de.mm.spaceinvaders.protocol.Protocol;
import de.mm.spaceinvaders.util.Util;

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
	private ServerMenu serverMenu = new ServerMenu();
	private ControllablePlayer thePlayer;
	private StarBackground background = new StarBackground();
	private List<Entity> entities = new ArrayList<>();
	private List<Entity> outstandingSpawns = new ArrayList<>();
	private ScoreManager scoreManager;
	@Setter
	private ConnectionHandler client;

	private void start() throws IOException
	{
		new Protocol();

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

		ticker = new Ticker();

		frame.run();
	}

	public void toMainMenu()
	{
		ticker.stop();

		frame.setMenu(new MainMenu());

		entities.clear();
	}

	public void connect()
	{
		thePlayer = new ControllablePlayer(Textures.PLAYER.getTexture());

		frame.setMenu(serverMenu);

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					Client.connect("127.0.0.1", 7654);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void launchBullet()
	{
		try
		{
			Bullet bullet = new Bullet(Textures.BULLET.getTexture());
			bullet.setRotation(thePlayer.getRotation());
			bullet.setX(thePlayer.getX());
			bullet.setY(thePlayer.getY());
			bullet.setSpeed(Util.calcVectorFromDegrees(bullet.getRotation()).normalize()
					.multiply(0.6f));
			outstandingSpawns.add(bullet);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Entity getEntity(String uuid)
	{
		for (Entity e : entities)
		{
			if (e.getUuid() == uuid)
			{
				return e;
			}
		}
		return null;
	}

	public void startGame()
	{
		outstandingSpawns.add(thePlayer);
		frame.setMenu(ingameMenu);
		scoreManager = new ScoreManager();
		ticker = new Ticker();
		ticker.start();
	}

	public void spawn()
	{
		for (Entity e : outstandingSpawns)
		{
			if (entities.contains(e))
			{
				entities.remove(e);
			}
			else
			{
				entities.add(e);
			}
		}
		outstandingSpawns.clear();
	}
}
