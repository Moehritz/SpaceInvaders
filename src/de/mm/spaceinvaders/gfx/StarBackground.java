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

	private int livingTimeMin = 80, livingTimeMax = 300, starsCount = 500;
	private double minSize = 0.001, maxSize = 0.003;
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
		double x = rand.nextDouble();
		double y = rand.nextDouble();
		long time = livingTimeMin + rand.nextInt(livingTimeMax - livingTimeMin);
		double size = minSize + (maxSize - minSize) * rand.nextDouble();
		stars.add(new Star(x, y, time, size));
	}

	@Override
	public void draw()
	{
		for (Star star : stars)
		{
			double sizeX = star.size;
			double sizeY = Display.getWidth() * sizeX / Display.getHeight();
			float opacity = star.getOpacity();
			double x = star.x;
			double y = star.y;
			drawPixel(x, y, opacity, sizeX, sizeY);
			drawPixel(x + sizeX, y, Math.abs(opacity / 2 + (rand.nextInt(10) - 5)),
					sizeX, sizeY);
			drawPixel(x - sizeX, y, Math.abs(opacity / 2 + (rand.nextInt(10) - 5)),
					sizeX, sizeY);
			drawPixel(x, y + sizeY, Math.abs(opacity / 2 + (rand.nextInt(10) - 5)),
					sizeX, sizeY);
			drawPixel(x, y - sizeY, Math.abs(opacity / 2 + (rand.nextInt(10) - 5)),
					sizeX, sizeY);
		}

	}

	private void drawPixel(double x, double y, float opacity, double sizeX, double sizeY)
	{
		if (opacity == 0) return;
		glColor4f(1.0f, 1.0f, 1.0f, opacity / 100);
		glBegin(GL_QUADS);
		{
			glVertex2d(x, y);
			glVertex2d(x + sizeX, y);
			glVertex2d(x + sizeX, y + sizeY);
			glVertex2d(x, y + sizeY);
		}
		glEnd();
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	}

	private class Star
	{

		private double size;
		private double x, y;
		private long timeToLive, start;

		private Star(double x, double y, long start, double size)
		{
			this.x = x;
			this.y = y;
			this.start = start;
			this.timeToLive = start;
			this.size = size;
		}

		private float getOpacity()
		{
			float a = ((float) timeToLive) / start;
			float left = a * 100;
			float dist = 50 - Math.abs(50 - left);
			return dist * 2;
		}
	}

}
