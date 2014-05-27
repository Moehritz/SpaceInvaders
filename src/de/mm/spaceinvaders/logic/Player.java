package de.mm.spaceinvaders.logic;

import lombok.Getter;
import lombok.Setter;

import org.newdawn.slick.opengl.Texture;

import de.mm.spaceinvaders.gfx.RocketFire;

@Getter
@Setter
public class Player extends Entity
{

	private String name;

	private RocketFire fire;

	public Player(Texture texture)
	{
		super(texture);
		fire = new RocketFire();
		setType((byte) 1);
	}

	public Player(Texture texture, String uuid)
	{
		super(texture, uuid);
		fire = new RocketFire();
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
		//fire.draw();
		super.draw();
	}

}
