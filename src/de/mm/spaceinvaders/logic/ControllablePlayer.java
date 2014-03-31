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
		if (ammoRegain >= Ticker.tps / 3 && getAmmo() < Player.getMaxAmmo())
		{
			ammoRegain = 0;
			setAmmo(getAmmo() + 1);
		}

		getSpeed().setX(getSpeed().getX() * 0.9);
		getSpeed().setY(getSpeed().getY() * 0.9);

		double flySpeed = 0.3f;

		Vector direction = Util.calcVectorFromDegrees(newRotation).normalize().multiply(flySpeed);
		Vector directionVertical = Util.calcVectorFromDegrees(newRotation + 90).normalize().multiply(flySpeed);

		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			setSpeed(direction);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			setSpeed(directionVertical);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			setSpeed(direction.multiply(-1));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			setSpeed(directionVertical.multiply(-1));
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
