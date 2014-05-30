package de.mm.spaceinvaders.logic;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gamestate.Ingame;
import de.mm.spaceinvaders.protocol.packets.ShootProjectile;
import de.mm.spaceinvaders.protocol.packets.UpdatePosition;
import de.mm.spaceinvaders.util.Util;
import de.mm.spaceinvaders.util.Vector;

public class ControllablePlayer extends Player
{

	private int ammoRegain = 0;
	@Getter
	private static final int shotCooldown = 100, maxAmmo = 50;

	@Getter
	@Setter
	private int ammo = maxAmmo;
	private long lastShot;

	public ControllablePlayer(Texture texture, String uuid)
	{
		super(texture, uuid);
		setX(0.5);
		setY(0.5);
		setName("User" + new Random().nextInt(Integer.MAX_VALUE));
	}

	public void handleInput(long delta)
	{
		boolean moved = false;

		double mouseX = (double) Mouse.getX() / (double) Display.getWidth();
		double mouseY = 1 - ((double) Mouse.getY() / (double) Display.getHeight());

		double newRotation = Util.calcRotationAngleInDegrees(new Vector(getX(), getY()),
				new Vector(mouseX, mouseY));

		if (!Double.isNaN(newRotation) && newRotation != getRotation())
		{
			setRotation(newRotation);
			moved = true;
		}

		ammoRegain++;
		if (ammoRegain >= GameTicker.tps / 3 && getAmmo() < getMaxAmmo())
		{
			ammoRegain = 0;
			setAmmo(getAmmo() + 1);
		}

		getSpeed().setX(getSpeed().getX() * 0.9);
		getSpeed().setY(getSpeed().getY() * 0.9);

		double flySpeed = 0.0005d;

		Vector direction = Util.calcVectorFromDegrees(newRotation).normalize()
				.multiply(flySpeed);
		Vector directionVertical = Util.calcVectorFromDegrees(newRotation + 90)
				.normalize().multiply(flySpeed);

		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			moved = true;
			setSpeed(direction);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			moved = true;
			setSpeed(directionVertical);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			moved = true;
			setSpeed(direction.multiply(-1));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			moved = true;
			setSpeed(directionVertical.multiply(-1));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			if (getAmmo() != 0
					&& (System.currentTimeMillis() - lastShot) > getShotCooldown())
			{
				ammo--;
				lastShot = System.currentTimeMillis();
				Ingame ig = (Ingame) SpaceInvaders.getInstance().getGameState();
				ig.getConnection().sendPackets(new ShootProjectile(getX(), getY(), getRotation()));
			}
		}
		if (moved)
		{
			Ingame ig = (Ingame) SpaceInvaders.getInstance().getGameState();
			ig.getConnection().sendPackets(
					new UpdatePosition(getUuid(), getX(), getY(), getRotation(),
							getSpeed()));
		}
	}
}
