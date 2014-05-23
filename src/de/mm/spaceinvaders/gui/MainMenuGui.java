package de.mm.spaceinvaders.gui;

import java.awt.geom.Rectangle2D;

import org.newdawn.slick.Color;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuActionListener;
import de.mm.spaceinvaders.gui.model.MenuButton;
import de.mm.spaceinvaders.gui.model.MenuText;

public class MainMenuGui extends Menu
{

	@Override
	public void init()
	{
		super.init();

		addObject(new MenuText(new Rectangle2D.Double(0.1, 0.1, 0.8, 0.3),
				"SPACE INVERSION", 30, Color.green));
		MenuButton button = new MenuButton(new Rectangle2D.Double(0.2, 0.4, 0.6, 0.15),
				"Spiel starten!", 25);
		button.setListener(new MenuActionListener()
		{

			@Override
			public void onHover()
			{
			}

			@Override
			public void onClick()
			{
				SpaceInvaders.getInstance().switchToServerInfo();
			}
		});
		addObject(button);

		MenuButton exit = new MenuButton(new Rectangle2D.Double(0.2, 0.65, 0.6, 0.1),
				"Exit", 20);
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
