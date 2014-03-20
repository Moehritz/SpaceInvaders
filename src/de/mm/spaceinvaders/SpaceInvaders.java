package de.mm.spaceinvaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.lwjgl.LWJGLException;

import de.mm.spaceinvaders.gfx.Frame;
import de.mm.spaceinvaders.gfx.Textures;
import de.mm.spaceinvaders.gui.IngameGui;
import de.mm.spaceinvaders.gui.MainMenu;
import de.mm.spaceinvaders.logic.Bullet;
import de.mm.spaceinvaders.logic.ControllablePlayer;
import de.mm.spaceinvaders.logic.Enemy;
import de.mm.spaceinvaders.logic.Entity;
import de.mm.spaceinvaders.logic.ScoreManager;
import de.mm.spaceinvaders.logic.Ticker;

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
	private ScoreManager scoreManager;
	@Setter
	private boolean gameActive;
	private MainMenu mainMenu = new MainMenu();
	private IngameGui ingameMenu = new IngameGui();
	private ControllablePlayer thePlayer;

	private void start() throws IOException
	{
		gameActive = false;
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
		frame.setMenu(new MainMenu());

		ticker = new Ticker();

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
			bullet.setSpeed(Util.calcVectorFromDegrees(bullet.getRotation()).multiply(8.0f));
			outstandingSpawns.add(bullet);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void spawnEnemy()
	{
		// TODO Gegnertextur
		Enemy e = new Enemy(Textures.PLAYER.getTexture());
		outstandingSpawns.add(e);
	}

	public void spawn()
	{
		for (Entity e : outstandingSpawns)
		{
			getEntities().add(e);
		}
		outstandingSpawns.clear();
	}

	public void startNewGame()
	{
		frame.setMenu(ingameMenu);
		scoreManager = new ScoreManager();

		ControllablePlayer player = new ControllablePlayer(Textures.PLAYER.getTexture());

		outstandingSpawns.add(player);

		thePlayer = player;

		ticker.start();

		ingameMenu.updateScore(0);

	}

}
