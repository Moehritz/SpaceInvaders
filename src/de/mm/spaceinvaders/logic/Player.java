package de.mm.spaceinvaders.logic;

import lombok.Getter;
import lombok.Setter;

import org.newdawn.slick.opengl.Texture;

import de.mm.spaceinvaders.gfx.RocketFire;

@Getter
@Setter
public class Player extends Entity
{
	@Getter
	private static final int shotCooldown = 100, maxAmmo = 50;

	private int ammo = maxAmmo;
	private long lastShot;

	private String name;

	private RocketFire fire;

	public Player(Texture texture)
	{
		super(texture);
		fire = new RocketFire();
	}

	public Player(Texture texture, String uuid)
	{
		super(texture, uuid);
		fire = new RocketFire();
	}

	public void setAmmo(int ammo)
	{
		this.ammo = ammo;
	}

	public void shoot()
	{
		setAmmo(ammo - 1);
		// TODO
	}

	@Override
	public boolean updatePosition(long delta)
	{
		return super.updatePosition(delta);
	}

	@Override
	public void draw()
	{
		fire.update();
		fire.draw();
		super.draw();
	}

}
