package de.mm.spaceinvaders.logic;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;

public class Bullet extends Entity
{

	private Entity sender;

	private Bullet(Texture texture) throws IOException
	{
		super(texture);
	}

	public Bullet(Texture texture, Entity sender) throws IOException
	{
		this(texture);
		this.sender = sender;
	}

	public Entity getSender()
	{
		return sender;
	}

}
