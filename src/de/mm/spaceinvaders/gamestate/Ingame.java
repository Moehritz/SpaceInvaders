package de.mm.spaceinvaders.gamestate;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import de.mm.spaceinvaders.gfx.Drawable;
import de.mm.spaceinvaders.gfx.Textures;
import de.mm.spaceinvaders.gui.IngameGui;
import de.mm.spaceinvaders.io.ConnectionHandler;
import de.mm.spaceinvaders.logic.ControllablePlayer;
import de.mm.spaceinvaders.logic.Entity;
import de.mm.spaceinvaders.logic.GameTicker;

public class Ingame extends GameState
{
	@Getter
	private List<Entity> entities = new ArrayList<>();
	private List<Entity> outstandingSpawns = new ArrayList<>();
	@Getter
	private ControllablePlayer thePlayer;
	private GameTicker ticker;
	@Getter
	private ConnectionHandler connection;

	public Ingame(ConnectionHandler connection)
	{
		this.connection = connection;
	}

	@Override
	public void init()
	{
		super.visibleMenu = new IngameGui(this);

		thePlayer = new ControllablePlayer(Textures.PLAYER.getTexture());
		ticker = new GameTicker();
	}

	public void run()
	{
		prepareSpawn(thePlayer);

		ticker.start();
	}

	@Override
	public List<Drawable> getThingsToDraw()
	{
		List<Drawable> ret = super.getThingsToDraw();
		ret.addAll(entities);
		return ret;
	}

	public void spawnEntities()
	{
		synchronized (outstandingSpawns)
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

	public void prepareSpawn(Entity e)
	{
		if (!outstandingSpawns.contains(e)) outstandingSpawns.add(e);
	}

	@Override
	public void end()
	{
		getConnection().closeConnection();
		ticker.stop();
	}
}