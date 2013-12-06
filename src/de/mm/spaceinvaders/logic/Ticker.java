package de.mm.spaceinvaders.logic;

import java.util.Timer;
import java.util.TimerTask;

import de.mm.spaceinvaders.SpaceInvaders;

public class Ticker extends TimerTask
{

	public static int tps = 50;

	private Timer timer;
	private long last = System.currentTimeMillis();

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
		long thisFrame = System.currentTimeMillis();
		long delta = thisFrame - last;
		last = thisFrame;
		for (Entity e : SpaceInvaders.getInstance().loadedEntities())
		{
			if (e instanceof ControllablePlayer) ((ControllablePlayer) e).handleInput(delta);
			e.updatePosition();
		}
	}

}
