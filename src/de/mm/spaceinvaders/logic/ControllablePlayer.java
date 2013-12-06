package de.mm.spaceinvaders.logic;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import de.mm.spaceinvaders.Util;
import de.mm.spaceinvaders.Vector;

public class ControllablePlayer extends Player
{

	public ControllablePlayer(Texture texture)
	{
		super(texture);
	}

	public void handleInput(long delta)
	{
		int mouseX = Mouse.getX();
		int mouseY = Display.getHeight() - Mouse.getY();

		double newRotation = Util.calcRotationAngleInDegrees(new Vector(getX(), getY()),
				new Vector(mouseX, mouseY));

		if (!Double.isNaN(newRotation)) setRotation(newRotation);

		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			long before = getY();
			setY((long) (before - ((10.0d / Ticker.tps) * delta)));
			if (outOfBounds(2)) setY(before);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			long before = getX();
			setX((int) (before - ((10.0d / Ticker.tps) * delta)));
			if (outOfBounds(3)) setX(before);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			long before = getY();
			setY((int) (before + ((10.0d / Ticker.tps) * delta)));
			if (outOfBounds(0)) setY(before);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			long before = getX();
			setX((int) (before + ((10.0d / Ticker.tps) * delta)));
			if (outOfBounds(1)) setX(before);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			if (getAmmo() != 0 && (System.currentTimeMillis() - getLastShot()) > getShotCooldown())
			{
				shoot();
				setLastShot(System.currentTimeMillis());
			}
		}
	}
}
