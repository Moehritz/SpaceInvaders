package de.mm.spaceinvaders.gui;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuActionListener;
import de.mm.spaceinvaders.gui.model.MenuButton;
import de.mm.spaceinvaders.gui.model.MenuText;

public class MainMenu extends Menu
{

	@Override
	public void init()
	{
		addObject(new MenuText(new Rectangle(40, 40, Display.getWidth() - 80, 80),
				"SPACE INVERSION", 30, Color.green));
		MenuButton button = new MenuButton(new Rectangle(160, 270, Display.getWidth() - 320, 50),
				"Start Game", 25);
		button.setListener(new MenuActionListener()
		{

			@Override
			public void onHover()
			{
			}

			@Override
			public void onClick()
			{
				SpaceInvaders.getInstance().startNewGame();
			}
		});
		addObject(button);

		MenuButton exit = new MenuButton(new Rectangle(10, 10, 100, 30), "Exit", 20);
		exit.setListener(new MenuActionListener()
		{

			@Override
			public void onHover()
			{
			}

			@Override
			public void onClick()
			{
				System.exit(0);
			}
		});
		addObject(exit);
	}

}
