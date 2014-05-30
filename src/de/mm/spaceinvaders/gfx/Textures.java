package de.mm.spaceinvaders.gfx;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public enum Textures
{

	PLAYER("player"), BULLET("bullet");

	public static void init()
	{
		for (Textures t : Textures.values())
			t.load();
	}

	private String name;
	private Texture texture;

	private Textures(String name)
	{
		this.name = name;
	}
	
	private void load()
	{
		texture = load(name);
	}

	public static Texture load(String name)
	{
		try
		{
			return TextureLoader.getTexture("PNG",
					ResourceLoader.getResourceAsStream("res/" + name + ".png"),
					GL11.GL_NEAREST);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public Texture getTexture()
	{
		return texture;
	}
}
