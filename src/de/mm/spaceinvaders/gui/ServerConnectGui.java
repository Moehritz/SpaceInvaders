package de.mm.spaceinvaders.gui;

import java.awt.geom.Rectangle2D;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuActionListener;
import de.mm.spaceinvaders.gui.model.MenuButton;
import de.mm.spaceinvaders.gui.model.MenuText;
import de.mm.spaceinvaders.gui.model.MenuTextField;
import de.mm.spaceinvaders.gui.model.Text;

public class ServerConnectGui extends Menu
{
	private MenuText title;
	private MenuTextField input;

	@Override
	public void init()
	{
		super.init();

		title = new MenuText(new Rectangle2D.Double(0.3, 0.3, 0.4, 0.1),
				new Text("Verbinde zu..."));
		addObject(title);

		input = new MenuTextField(new Rectangle2D.Double(0.2, 0.45, 0.6, 0.1));
		addObject(input);

		MenuButton button = new MenuButton(new Rectangle2D.Double(0.35, 0.6, 0.3, 0.07),
				new Text("LOS"));
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
				SpaceInvaders.getInstance().switchToServerConnection(host, port);
			}
		});
		addObject(button);
	}

	public void setTitle(String text)
	{
		title.getText().setContent(text);
	}

	public String getTitle()
	{
		return title.getText().getContent();
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
