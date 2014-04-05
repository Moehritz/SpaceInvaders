package de.mm.spaceinvaders.gui;

import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuActionListener;
import de.mm.spaceinvaders.gui.model.MenuButton;
import de.mm.spaceinvaders.gui.model.MenuTextField;

public class ServerMenu extends Menu
{
	private boolean changeName = false;

	private MenuButton changeNameButton;
	private MenuTextField changeNameTextField;

	@Override
	public void init()
	{
		super.init();

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
				back();
			}
		});
		addObject(exit);

		changeNameButton = new MenuButton(new Rectangle(Display.getWidth() - 245, 10, 235, 30), "Change Name", 20);
		changeNameButton.setListener(new MenuActionListener()
		{
			@Override
			public void onHover()
			{
			}

			@Override
			public void onClick()
			{
				changeNameMode(true);
			}
		});
		changeNameMode(false);

		changeNameTextField = new MenuTextField(new Rectangle(160, 400, Display.getWidth() - 320, 50));
		changeNameTextField.setText("User" + new Random().nextInt(Integer.MAX_VALUE));
	}

	private void back()
	{
		if (changeName)
		{
			changeNameMode(false);
		}
		else
		{
			SpaceInvaders.getInstance().toMainMenu();
		}
	}

	public void changeNameMode(boolean use)
	{
		changeName = use;
		if (changeName)
		{
			removeObject(changeNameButton);
			addObject(changeNameTextField);
		} else {
			addObject(changeNameButton);
			removeObject(changeNameTextField);
		}
	}
}