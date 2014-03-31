package de.mm.spaceinvaders.logic;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;

public class Bullet extends Entity
{

	private Entity sender;

	public Bullet(Texture texture) throws IOException
	{
		super(texture);
	}

	public Entity getSender()
	{
		return sender;
	}

}
