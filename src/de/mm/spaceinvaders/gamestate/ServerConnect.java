package de.mm.spaceinvaders.gamestate;

import de.mm.spaceinvaders.gui.ServerConnectGui;

public class ServerConnect extends GameState
{
	@Override
	public void init()
	{
		super.visibleMenu = new ServerConnectGui();
	}
}
