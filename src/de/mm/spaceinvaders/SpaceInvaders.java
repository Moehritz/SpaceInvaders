package de.mm.spaceinvaders;

import java.io.IOException;

import lombok.Getter;

import org.lwjgl.LWJGLException;

import de.mm.spaceinvaders.client.Client;
import de.mm.spaceinvaders.gamestate.GameState;
import de.mm.spaceinvaders.gamestate.Loading;
import de.mm.spaceinvaders.gamestate.MainMenu;
import de.mm.spaceinvaders.gamestate.ServerConnect;
import de.mm.spaceinvaders.gamestate.ServerMenu;
import de.mm.spaceinvaders.gfx.Frame;
import de.mm.spaceinvaders.gfx.StarBackground;
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

	public void switchToMainMenu()
	{
		gameState.end();
		gameState = new MainMenu();
	}

	public void switchToServerInfo()
	{
		gameState.end();
		gameState = new ServerConnect();
	}

	public void switchToSererConnection(final String host, final int port)
	{
		gameState.end();
		Loading loadingScreen = new Loading();
		loadingScreen.updateText("Verbinde ... " + host + " -> " + port);
		gameState = loadingScreen;

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					Client.connect(host, port);
				}
				catch (Exception e)
				{
					switchToMainMenu();
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void switchToServerMenu()
	{
		gameState.end();
		gameState = new ServerMenu();
	}
}
