package de.mm.spaceinvaders.gui;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuActionListener;
import de.mm.spaceinvaders.gui.model.MenuButton;
import de.mm.spaceinvaders.gui.model.MenuText;
import de.mm.spaceinvaders.gui.model.MenuTextField;

public class ServerConnectGui extends Menu
{
	private MenuText title;
	private MenuTextField input;

	@Override
	public void init()
	{
		super.init();

		title = new MenuText(new Rectangle(160, 200, Display.getWidth() - 320, 50),
				"Verbinde zu ...", 20f, Color.white);
		addObject(title);

		input = new MenuTextField(new Rectangle(160, 270, Display.getWidth() - 320, 50));
		addObject(input);

		MenuButton button = new MenuButton(new Rectangle(200, 340,
				Display.getWidth() - 400, 50), "Los", 20f);
		button.setListener(new MenuActionListener()
		{

			@Override
			public void onHover()
			{
			}

			@Override
			public void onClick()
			{
				String[] i = getText().split(":");
				String host = i[0];
				int port = 8888;
				if (i.length >= 2)
				{
					try
					{
						port = Integer.parseInt(i[1]);
					}
					catch (NumberFormatException ex)
					{
						setTitle("Error");
						return;
					}
				}
				SpaceInvaders.getInstance().switchToSererConnection(host, port);
			}
		});
		addObject(button);
	}

	public void setTitle(String text)
	{
		title.setText(text);
	}

	public String getTitle()
	{
		return title.getText();
	}

	public void setText(String text)
	{
		input.setText(text);
	}

	public String getText()
	{
		return input.getText();
	}
}
