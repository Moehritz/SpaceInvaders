package de.mm.spaceinvaders.logic;

import org.newdawn.slick.opengl.Texture;

import de.mm.spaceinvaders.SpaceInvaders;

public class Player extends Entity
{
	private static final int shotCooldown = 500, maxAmmo = 100;

	private int ammo = maxAmmo;
	private long lastShot;

	public Player(Texture texture)
	{
		super(texture);
	}

	public void shoot()
	{
		ammo--;
		SpaceInvaders.getInstance().launchBullet(this);
	}

	public int getAmmo()
	{
		return ammo;
	}

	public void setAmmo(int ammo)
	{
		this.ammo = ammo;
	}

	public long getLastShot()
	{
		return lastShot;
	}

	public void setLastShot(long lastShot)
	{
		this.lastShot = lastShot;
	}

	public static int getShotCooldown()
	{
		return shotCooldown;
	}

	public static int getMaxAmmo()
	{
		return maxAmmo;
	}

}
