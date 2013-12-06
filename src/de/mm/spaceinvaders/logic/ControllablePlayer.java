package de.mm.spaceinvaders.logic;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import de.mm.spaceinvaders.Util;

public class ControllablePlayer extends Player
{

	public void handleInput(long delta)
	{
		int mouseX = Mouse.getX();
		int mouseY = Display.getHeight() - Mouse.getY();

		Double newRotation = Util.calcRotationAngleInDegrees(getX(), getY(), mouseX,
				mouseY);

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

	}
}
