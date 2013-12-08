package de.mm.spaceinvaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import org.lwjgl.LWJGLException;

import de.mm.spaceinvaders.gfx.Frame;
import de.mm.spaceinvaders.gfx.Textures;
import de.mm.spaceinvaders.logic.Bullet;
import de.mm.spaceinvaders.logic.ControllablePlayer;
import de.mm.spaceinvaders.logic.Entity;
import de.mm.spaceinvaders.logic.Ticker;
import de.mm.spaceinvaders.menu.MenuButton;

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
	private List<Entity> entities, outstandingSpawns;
	private Ticker ticker;

	private void start() throws IOException
	{
		entities = new ArrayList<>();
		outstandingSpawns = new ArrayList<>();

		frame = new Frame();
		try
		{
			frame.init();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
		
		MenuButton.initFont();

		ControllablePlayer player = new ControllablePlayer(Textures.PLAYER.getTexture());

		outstandingSpawns.add(player);

		ticker = new Ticker();
		ticker.start();

		frame.run();
	}

	public void stop()
	{
		ticker.stop();
	}

	public void launchBullet(Entity entity)
	{
		try
		{
			Bullet bullet = new Bullet(Textures.BULLET.getTexture(), entity);
			bullet.setRotation(entity.getRotation());
			bullet.setX(entity.getX());
			bullet.setY(entity.getY());
			bullet.setSpeed(Util.calcVectorFromDegrees(bullet.getRotation()).multiply(10));
			outstandingSpawns.add(bullet);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void spawn()
	{
		for (Entity e : outstandingSpawns)
		{
			getEntities().add(e);
		}
		outstandingSpawns.clear();
	}

}
