package de.mm.spaceinvaders.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.Sys;

import de.mm.spaceinvaders.gamestate.Ingame;
import de.mm.spaceinvaders.SpaceInvaders;

public class GameTicker
{

	public static int tps = 60;

	private Timer timer;
	private long last = (Sys.getTime() * 1000) / Sys.getTimerResolution();

	public void start()
	{
		timer = new Timer();
		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				runTick();
			}
		}, 0, 1000 / tps);
	}

	public void stop()
	{
		if (timer != null) timer.cancel();
	}

	public void runTick()
	{
		long thisFrame = (Sys.getTime() * 1000) / Sys.getTimerResolution();
		long delta = thisFrame - last;
		last = thisFrame;
		Ingame ing = (Ingame) SpaceInvaders.getInstance().getGameState();
		List<Entity> allEntities = new ArrayList<>(ing.getEntities());
		for (Entity e : allEntities)
		{
			if (e == null) continue;
			if (e instanceof ControllablePlayer)
				((ControllablePlayer) e).handleInput(delta);
			e.updatePosition(delta);
		}
	}
}