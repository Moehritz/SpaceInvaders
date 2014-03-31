package de.mm.spaceinvaders.gfx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class StarBackground implements Drawable
{

	private int livingTimeMin = 80, livingTimeMax = 300, starsCount = 1000, minSize = 2, maxSize = 10;
	private List<Star> stars = new ArrayList<>();
	private Random rand = new Random();
	@Getter
	@Setter
	private float speed;

	public StarBackground()
	{
	}

	public void update()
	{
		List<Star> ended = new ArrayList<>();
		for (Star star : stars)
		{
			star.timeToLive--;
			if (star.timeToLive <= 0)
			{
				ended.add(star);
			}
			star.x -= speed;
			if (star.x <= 0 || star.x >= Display.getWidth())
			{
				ended.add(star);
			}
		}
		for (Star star : ended)
			stars.remove(star);
		while (stars.size() < starsCount)
		{
			newStar();
		}
	}

	private void newStar()
	{
		int x = rand.nextInt(Display.getWidth());
		int y = rand.nextInt(Display.getHeight());
		long time = livingTimeMin + rand.nextInt(livingTimeMax - livingTimeMin);
		int size = rand.nextInt(maxSize - minSize) + minSize;
		stars.add(new Star(x, y, time, size));
	}

	@Override
	public void draw()
	{
		for (Star star : stars)
		{
			float opacity = star.getOpacity();
			float x = star.x;
			float y = star.y;
			drawPixel(x, y, opacity, star.size);
			drawPixel(x + 1, y, Math.abs(opacity / 2 + (rand.nextInt(10) - 5)), star.size);
			drawPixel(x - 1, y, Math.abs(opacity / 2 + (rand.nextInt(10) - 5)), star.size);
			drawPixel(x, y + 1, Math.abs(opacity / 2 + (rand.nextInt(10) - 5)), star.size);
			drawPixel(x, y - 1, Math.abs(opacity / 2 + (rand.nextInt(10) - 5)), star.size);
		}
	}

	private void drawPixel(float x, float y, float opacity, float size)
	{
		if (opacity == 0) return;
		float sizePerPixel = size / 2;
		glColor4f(1.0f, 1.0f, 1.0f, opacity / 100);
		glBegin(GL_QUADS);
		{
			glVertex2f(x * sizePerPixel, y * sizePerPixel);
			glVertex2f((x + 1) * sizePerPixel, y * sizePerPixel);
			glVertex2f((x + 1) * sizePerPixel, (y + 1) * sizePerPixel);
			glVertex2f(x * sizePerPixel, (y + 1) * sizePerPixel);
		}
		glEnd();
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	}

	private class Star
	{
		private Star(float x, float y, long start, float size)
		{
			this.x = x;
			this.y = y;
			this.start = start;
			this.timeToLive = start;
			this.size = size;
		}

		private float x, y, size;
		private long timeToLive, start;

		private float getOpacity()
		{
			float a = ((float) timeToLive) / start;
			float left = a * 100;
			float dist = 50 - Math.abs(50 - left);
			return dist * 2;
		}
	}

}
