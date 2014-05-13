package de.mm.spaceinvaders.gamestate;

import de.mm.spaceinvaders.gui.LoadingGui;

public class Loading extends GameState
{
	@Override
	public void init()
	{
		super.visibleMenu = new LoadingGui();
	}

	public void updateText(String text)
	{
		((LoadingGui) super.visibleMenu).setText(text);
	}
}