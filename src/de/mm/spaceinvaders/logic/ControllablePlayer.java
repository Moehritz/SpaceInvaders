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
		
		double flySpeed = 10.0d;
		
		Vector direction = Util.calcVectorFromDegrees(newRotation).normalize().multiply(flySpeed);
		Vector directionVertical = Util.calcVectorFromDegrees(newRotation + 90).normalize().multiply(flySpeed);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			getSpeed().setX((int) ((direction.getX() / Ticker.tps) * delta));
			getSpeed().setY((int) ((direction.getY() / Ticker.tps) * delta));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			getSpeed().setX((int) ((directionVertical.getX() / Ticker.tps) * delta));
			getSpeed().setY((int) ((directionVertical.getY() / Ticker.tps) * delta));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			getSpeed().setX((int) (-(direction.getX() / Ticker.tps) * delta));
			getSpeed().setY((int) (-(direction.getY() / Ticker.tps) * delta));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			getSpeed().setX((int) (-(directionVertical.getX() / Ticker.tps) * delta));
			getSpeed().setY((int) (-(directionVertical.getY() / Ticker.tps) * delta));
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
