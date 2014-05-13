package de.mm.spaceinvaders.gamestate;

import java.util.ArrayList;
import java.util.List;

import de.mm.spaceinvaders.gfx.Drawable;
import de.mm.spaceinvaders.gui.model.Menu;

public class GameState
{
	protected Menu visibleMenu;

	public GameState()
	{
		init();
	}

	public void init()
	{
		throw new UnsupportedOperationException(
				"[GameState] init() Methode muss überschrieben werden!");
	}

	public void end()
	{
	}

	public List<Drawable> getThingsToDraw()
	{
		List<Drawable> ret = new ArrayList<>();
		if (visibleMenu != null)
		{
			ret.add(visibleMenu);
		}
		return ret;
	}
}
