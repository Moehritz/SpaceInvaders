package de.mm.spaceinvaders.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.Sys;

import de.mm.spaceinvaders.SpaceInvaders;

public class Ticker extends TimerTask
{

	public static int tps = 500;

	private Timer timer;
	private long last = (Sys.getTime() * 1000) / Sys.getTimerResolution();

	public void start()
	{
		timer = new Timer();
		timer.schedule(this, 0, 1000 / tps);
	}

	public void stop()
	{
		if (timer != null) timer.cancel();
	}

	@Override
	public void run()
	{
		long thisFrame = (Sys.getTime() * 1000) / Sys.getTimerResolution();
		long delta = thisFrame - last;
		last = thisFrame;
		List<Entity> toRemove = new ArrayList<>();
		List<Entity> allEntities = new ArrayList<>(SpaceInvaders.getInstance()
				.getEntities());
		for (Entity e : allEntities)
		{
			if (e instanceof ControllablePlayer)
				((ControllablePlayer) e).handleInput(delta);
			boolean out = e.updatePosition(delta);
			if (!out && e instanceof Bullet)
			{
				toRemove.add(e);
			}
		}
		SpaceInvaders i = SpaceInvaders.getInstance();
		i.getEntities().removeAll(toRemove);
		i.spawn();
		i.getScoreManager().tick();
	}

}
