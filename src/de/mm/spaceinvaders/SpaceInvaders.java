package de.mm.spaceinvaders;

import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

import org.lwjgl.LWJGLException;

import de.mm.spaceinvaders.gamestate.GameState;
import de.mm.spaceinvaders.gamestate.MainMenu;
import de.mm.spaceinvaders.gfx.Frame;
import de.mm.spaceinvaders.gfx.StarBackground;
import de.mm.spaceinvaders.io.ConnectionHandler;
import de.mm.spaceinvaders.protocol.Protocol;

@Getter
public class SpaceInvaders
{

	@Getter
	private static SpaceInvaders instance;

	public static void main(String[] args)
	{
		instance = new SpaceInvaders();
		try
		{
			instance.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private Frame frame;
	private StarBackground background = new StarBackground();

	@Getter
	private GameState gameState;
	@Setter
	private ConnectionHandler client;

	private void start() throws IOException
	{
		new Protocol();
		
		gameState = new MainMenu();

		frame = new Frame();
		try
		{
			frame.init();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}

		frame.run();
	}
}
