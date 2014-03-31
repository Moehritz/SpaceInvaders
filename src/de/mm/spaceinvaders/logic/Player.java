package de.mm.spaceinvaders.logic;

import lombok.Getter;
import lombok.Setter;

import org.newdawn.slick.opengl.Texture;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gfx.RocketFire;

@Getter
@Setter
public class Player extends Entity
{
	@Getter
	private static final int shotCooldown = 100, maxAmmo = 100;

	private int ammo = maxAmmo;
	private long lastShot;

	private RocketFire fire;

	public Player(Texture texture)
	{
		super(texture);
		fire = new RocketFire();
	}

	public void setAmmo(int ammo)
	{
		this.ammo = ammo;
		SpaceInvaders.getInstance().getIngameMenu().updateAmmo(ammo, maxAmmo);
	}

	public void shoot()
	{
		setAmmo(ammo - 1);
		SpaceInvaders.getInstance().launchBullet();
	}

	@Override
	public boolean updatePosition()
	{
		fire.update();
		return super.updatePosition();
	}

	@Override
	public void draw()
	{
		fire.draw();
		super.draw();
	}

}
