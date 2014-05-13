package de.mm.spaceinvaders.gamestate;

import de.mm.spaceinvaders.gui.ServerMenuGui;

public class ServerMenu extends GameState
{
	@Override
	public void init()
	{
		super.visibleMenu = new ServerMenuGui();
	}
}