package de.mm.spaceinvaders.logic;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;

public class Bullet extends Entity
{

	private Entity sender;

	public Bullet(String uuid, Texture texture) throws IOException
	{
		super(texture, uuid);
		setType((byte) 2);
	}

	public Entity getSender()
	{
		return sender;
	}

}
