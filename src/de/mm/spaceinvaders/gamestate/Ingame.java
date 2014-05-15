package de.mm.spaceinvaders.gamestate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Getter;
import de.mm.spaceinvaders.gfx.Drawable;
import de.mm.spaceinvaders.gfx.Textures;
import de.mm.spaceinvaders.gui.IngameGui;
import de.mm.spaceinvaders.io.ConnectionHandler;
import de.mm.spaceinvaders.logic.ControllablePlayer;
import de.mm.spaceinvaders.logic.Entity;
import de.mm.spaceinvaders.logic.GameTicker;
import de.mm.spaceinvaders.logic.Player;
import de.mm.spaceinvaders.protocol.packets.Respawn;

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

	public Ingame(ConnectionHandler connection, Respawn respawn,
			Map<String, String> players, String ownUuid)
	{
		this.connection = connection;

		thePlayer = new ControllablePlayer(Textures.PLAYER.getTexture(), ownUuid);
		thePlayer.setX(respawn.getX());
		thePlayer.setY(respawn.getY());
		thePlayer.setRotation(respawn.getRotation());

		for (Entry<String, String> e : players.entrySet())
		{
			if (e.getKey().equalsIgnoreCase(ownUuid)) continue;
			Player p = createInvisiblePlayer(e.getKey(), e.getValue());
			entities.add(p);
		}
	}

	public Player createInvisiblePlayer(String uuid, String name)
	{
		Player p = new Player(Textures.PLAYER.getTexture(), uuid);
		p.setName(name);
		p.setVisible(false);
		return p;
	}

	@Override
	public void init()
	{
		super.visibleMenu = new IngameGui(this);
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
				if (e == null) continue;
				if (entities.contains(e))
				{
					entities.remove(e);
				}
				else
				{
					entities.add(e);
				}
			}
			if (outstandingSpawns.size() >= 1) outstandingSpawns.clear();
		}

	}

	public Entity getEntity(String uuid)
	{
		for (Entity e : entities)
		{
			if (e.getUuid().equals(uuid))
			{
				return e;
			}
		}
		return null;
	}

	public void prepareSpawn(Entity e)
	{
		if (!outstandingSpawns.contains(e) && e != null) outstandingSpawns.add(e);
	}

	@Override
	public void end()
	{
		getConnection().closeConnection();
		ticker.stop();
	}
}