package de.mm.spaceinvaders.gfx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.lwjgl.opengl.Display;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gamestate.Ingame;
import de.mm.spaceinvaders.logic.Entity;
import de.mm.spaceinvaders.util.Util;
import de.mm.spaceinvaders.util.Vector;

public class RocketFire implements Drawable
{

	private int livingTimeMin = 30, livingTimeMax = 50, maxSpawnsPerTick = 2,
			rotChangeMax = 100;
	private float speed = 0.004f;
	private ConcurrentLinkedQueue<SingleRocketFire> fires = new ConcurrentLinkedQueue<SingleRocketFire>();
	private Random rand = new Random();

	public void update()
	{
		if (!(SpaceInvaders.getInstance().getGameState() instanceof Ingame)) return;
		List<SingleRocketFire> ended = new ArrayList<>();
		for (SingleRocketFire fire : fires)
		{
			fire.timeToLive--;
			if (fire.timeToLive <= 0)
			{
				ended.add(fire);
				continue;
			}
			fire.updatePosition();
		}
		for (SingleRocketFire fire : ended)
			fires.remove(fire);
		int spawns = 0;
		Vector playerSpeed = ((Ingame) SpaceInvaders.getInstance().getGameState())
				.getThePlayer().getSpeed();
		while (spawns < maxSpawnsPerTick
				&& (playerSpeed.getX() != 0 || playerSpeed.getY() != 0))
		{
			newFire();
			spawns++;
		}
	}

	private void newFire()
	{
		Entity p = ((Ingame) SpaceInvaders.getInstance().getGameState()).getThePlayer();
		double x = p.getX();
		double y = p.getY();
		long time = livingTimeMin + rand.nextInt(livingTimeMax - livingTimeMin);
		fires.add(new SingleRocketFire(x, y, time, p.getRotation()
				+ (rand.nextInt(rotChangeMax))));
	}

	@Override
	public void draw()
	{
		for (SingleRocketFire fire : fires)
		{
			float opacity = fire.getOpacity();
			double x = fire.x;
			double y = fire.y;
			double sizeX = 0.01;
			double sizeY = Display.getWidth() * sizeX / Display.getHeight();
			StarBackground.drawPixel(x, y, 1, rand.nextFloat() / 2, 0, opacity, sizeX,
					sizeY);
		}
	}

	private class SingleRocketFire
	{
		private SingleRocketFire(double x2, double y2, long start, double rot)
		{
			this.x = x2;
			this.y = y2;
			this.start = start;
			this.timeToLive = start;
			move = Util.calcVectorFromDegrees(rot - (rotChangeMax / 2) - 180).normalize()
					.multiply(speed);
		}

		private double x, y;
		private Vector move;
		private long timeToLive, start;

		private float getOpacity()
		{
			float a = ((float) timeToLive) / start;
			float left = a * 100;
			float dist = Math.abs(left);
			return dist;
		}

		private void updatePosition()
		{
			x += move.getX();
			y += move.getY();
		}

	}

}
