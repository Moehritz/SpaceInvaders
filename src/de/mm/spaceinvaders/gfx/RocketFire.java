package de.mm.spaceinvaders.gfx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.newdawn.slick.opengl.TextureImpl;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.util.Util;
import de.mm.spaceinvaders.util.Vector;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class RocketFire implements Drawable
{

	private int livingTimeMin = 30, livingTimeMax = 50, maxSpawnsPerTick = 2, rotChangeMax = 100;
	private float speed = 2f;
	private ConcurrentLinkedQueue<SingleRocketFire> fires = new ConcurrentLinkedQueue<SingleRocketFire>();
	private Random rand = new Random();

	public void update()
	{
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
		Vector playerSpeed = SpaceInvaders.getInstance().getThePlayer().getSpeed();
		while (spawns < maxSpawnsPerTick && (playerSpeed.getX() != 0 || playerSpeed.getY() != 0))
		{
			newFire();
			spawns++;
		}
	}

	private void newFire()
	{
		int x = (int) SpaceInvaders.getInstance().getThePlayer().getX();
		int y = (int) SpaceInvaders.getInstance().getThePlayer().getY();
		long time = livingTimeMin + rand.nextInt(livingTimeMax - livingTimeMin);
		fires.add(new SingleRocketFire(x, y, time, SpaceInvaders.getInstance().getThePlayer().getRotation()
				+ (rand.nextInt(rotChangeMax))));
	}

	@Override
	public void draw()
	{
		TextureImpl.bindNone();
		for (SingleRocketFire fire : fires)
		{
			float opacity = fire.getOpacity();
			float x = fire.x;
			float y = fire.y;
			drawPixel((int) x, (int) y, opacity, 10);
		}
	}

	public void drawPixel(int x, int y, float opacity, int size)
	{
		if (opacity == 0) return;
		float sizePerPixel = size / 2;
		glColor4f(1.0f, rand.nextFloat() / 2, 0.0f, opacity / 100);
		glBegin(GL_QUADS);
		{
			glVertex2f(x, y);
			glVertex2f((x + 1) + sizePerPixel, y);
			glVertex2f((x + 1) + sizePerPixel, (y + 1) + sizePerPixel);
			glVertex2f(x, (y + 1) + sizePerPixel);
		}
		glEnd();
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	}

	private class SingleRocketFire
	{
		private SingleRocketFire(int x, int y, long start, double rot)
		{
			this.x = x;
			this.y = y;
			this.start = start;
			this.timeToLive = start;
			move = Util.calcVectorFromDegrees(rot + 135).normalize().multiply(speed);
		}

		private float x, y;
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
