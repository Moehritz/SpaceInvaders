package de.mm.spaceinvaders.gui;

import org.lwjgl.util.Rectangle;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuActionListener;
import de.mm.spaceinvaders.gui.model.MenuButton;

public class ServerMenu extends Menu
{
	@Override
	public void init()
	{
		MenuButton exit = new MenuButton(new Rectangle(10, 10, 100, 30), "Back", 20);
		exit.setListener(new MenuActionListener()
		{
			@Override
			public void onHover()
			{
			}
			@Override
			public void onClick()
			{
				SpaceInvaders.getInstance().toMainMenu();
			}
		});
		addObject(exit);
	}

}
