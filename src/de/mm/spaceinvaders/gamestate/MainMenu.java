package de.mm.spaceinvaders.gamestate;

import de.mm.spaceinvaders.gui.MainMenuGui;

public class MainMenu extends GameState
{
	@Override
	public void init()
	{
		super.visibleMenu = new MainMenuGui();
	}
}