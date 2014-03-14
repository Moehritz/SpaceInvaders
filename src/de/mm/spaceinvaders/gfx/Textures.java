package de.mm.spaceinvaders.gfx;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public enum Textures
{

	PLAYER("player"), BULLET("bullet");

	private Texture texture;

	private Textures(String name)
	{
		try
		{
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/" + name + ".png"),
					GL11.GL_NEAREST);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Texture getTexture()
	{
		return texture;
	}
}
