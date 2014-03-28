package de.mm.spaceinvaders.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.Util;
import de.mm.spaceinvaders.gfx.StarBackground;
import de.mm.spaceinvaders.gfx.Textures;

@Getter
public class Level
{

	@Setter
	private float speed = 0.0f;
	private StarBackground background = new StarBackground(this);
	private ScoreManager scoreManager;
	private List<Entity> entities, outstandingSpawns;
	private String name;
	@Setter
	private int scoreGoal = -1;

	public Level(String name, float speed)
	{
		this.name = name;
		this.speed = speed;

		entities = new ArrayList<Entity>();
		outstandingSpawns = new ArrayList<Entity>();

		scoreManager = new ScoreManager(this);
	}

	public void onScoreChange()
	{
		if (scoreGoal != -1 && scoreManager.getCurrentScore() >= scoreGoal)
		{
			SpaceInvaders.getInstance().nextLevel();
		}
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

}
