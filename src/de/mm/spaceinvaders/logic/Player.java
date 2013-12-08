package de.mm.spaceinvaders.logic;

import lombok.Getter;
import lombok.Setter;

import org.newdawn.slick.opengl.Texture;

import de.mm.spaceinvaders.SpaceInvaders;

@Getter
@Setter
public class Player extends Entity
{
	@Getter
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

}
