package de.mm.spaceinvaders.logic;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import de.mm.spaceinvaders.Util;
import de.mm.spaceinvaders.Vector;

public class ControllablePlayer extends Player
{

	private int ammoRegain = 0;

	public ControllablePlayer(Texture texture)
	{
		super(texture);
	}

	public void handleInput(long delta)
	{
		int mouseX = Mouse.getX();
		int mouseY = Display.getHeight() - Mouse.getY();

		double newRotation = Util.calcRotationAngleInDegrees(new Vector(getX(), getY()), new Vector(mouseX, mouseY));

		if (!Double.isNaN(newRotation)) setRotation(newRotation);
		
		ammoRegain++;
		if (ammoRegain >= Ticker.tps && getAmmo() < Player.getMaxAmmo())
		{
			ammoRegain = 0;
			setAmmo(getAmmo() + 1);
		}

		getSpeed().setX(getSpeed().getX() * 0.9);
		getSpeed().setY(getSpeed().getY() * 0.9);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			float before = getY();
			getSpeed().setY((int) (-(10.0d / Ticker.tps) * delta));
			if (outOfBounds(2))
			{
				setY(before);
				getSpeed().setY(0);
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			float before = getX();
			getSpeed().setX((int) (-(10.0d / Ticker.tps) * delta));
			if (outOfBounds(3))
			{
				setX(before);
				getSpeed().setX(0);
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			float before = getY();
			getSpeed().setY((int) ((10.0d / Ticker.tps) * delta));
			if (outOfBounds(0))
			{
				setY(before);
				getSpeed().setY(0);
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			float before = getX();
			getSpeed().setX((int) ((10.0d / Ticker.tps) * delta));
			if (outOfBounds(1))
			{
				setX(before);
				getSpeed().setX(0);
			}
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
