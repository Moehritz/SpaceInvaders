package de.mm.spaceinvaders.gfx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class BackgroundCreator
{

	private int pxlX = Display.getWidth() / 4, pxlY = Display.getHeight() / 4,
			livingTimeMin = 100, livingTimeMax = 200, starsCount = 80;
	private List<Star> stars = new ArrayList<>();
	private Random rand = new Random();

	public void update()
	{
		List<Star> ended = new ArrayList<>();
		for (Star star : stars)
		{
			star.timeToLive--;
			if (star.timeToLive <= 0) ended.add(star);
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
		int x = rand.nextInt(pxlX);
		int y = rand.nextInt(pxlY);
		long time = livingTimeMin + rand.nextInt(livingTimeMax - livingTimeMin);
		stars.add(new Star(x, y, time));
	}

	public void draw()
	{
		for (Star star : stars)
		{
			float opacity = star.getOpacity();
			int x = star.x;
			int y = star.y;
			drawPixel(x, y, opacity);
			drawPixel(x + 1, y, Math.abs(opacity / 2 + (rand.nextInt(10) - 5)));
			drawPixel(x - 1, y, Math.abs(opacity / 2 + (rand.nextInt(10) - 5)));
			drawPixel(x, y + 1, Math.abs(opacity / 2 + (rand.nextInt(10) - 5)));
			drawPixel(x, y - 1, Math.abs(opacity / 2 + (rand.nextInt(10) - 5)));
		}
	}

	private void drawPixel(int x, int y, float opacity)
	{
		if (opacity == 0) return;
		float sizePerPixelX = Display.getWidth() / pxlX;
		float sizePerPixelY = Display.getHeight() / pxlY;
		glColor4f(1.0f, 1.0f, 1.0f, opacity / 100);
		glBegin(GL_QUADS);
		{
			glVertex2f(x * sizePerPixelX, y * sizePerPixelY);
			glVertex2f((x + 1) * sizePerPixelX, y * sizePerPixelY);
			glVertex2f((x + 1) * sizePerPixelX, (y + 1) * sizePerPixelY);
			glVertex2f(x * sizePerPixelX, (y + 1) * sizePerPixelY);
		}
		glEnd();
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	}

	private class Star
	{
		private Star(int x, int y, long start)
		{
			this.x = x;
			this.y = y;
			this.start = start;
			this.timeToLive = start;
		}

		private int x, y;
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
