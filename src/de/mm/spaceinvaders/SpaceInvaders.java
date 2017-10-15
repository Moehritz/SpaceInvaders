package de.mm.spaceinvaders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.Getter;

import org.lwjgl.LWJGLException;

import de.mm.spaceinvaders.client.Client;
import de.mm.spaceinvaders.gamestate.GameState;
import de.mm.spaceinvaders.gamestate.Ingame;
import de.mm.spaceinvaders.gamestate.Loading;
import de.mm.spaceinvaders.gamestate.MainMenu;
import de.mm.spaceinvaders.gamestate.ServerConnect;
import de.mm.spaceinvaders.gamestate.ServerMenu;
import de.mm.spaceinvaders.gfx.Frame;
import de.mm.spaceinvaders.gfx.StarBackground;
import de.mm.spaceinvaders.io.ConnectionHandler;
import de.mm.spaceinvaders.protocol.Protocol;
import de.mm.spaceinvaders.protocol.packets.Respawn;

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

	public MainMenu switchToMainMenu()
	{
		gameState.end();
		gameState = new MainMenu();
		return (MainMenu) gameState;
	}

	public ServerConnect switchToServerInfo()
	{
		gameState.end();
		gameState = new ServerConnect();
		return (ServerConnect) gameState;
	}

	public Loading switchToServerConnection(final String host, final int port)
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
		return loadingScreen;
	}

	public ServerMenu switchToServerMenu(ConnectionHandler connection)
	{
		gameState.end();
		gameState = new ServerMenu(connection);
		return (ServerMenu) gameState;
	}

	public Ingame switchToIngame(ConnectionHandler connection, Respawn respawn,
			Map<String, String> players, String uuid)
	{
		Ingame ig = new Ingame(connection, respawn, players, uuid);
		gameState = ig;
		ig.run();
		return ig;
	}
}
