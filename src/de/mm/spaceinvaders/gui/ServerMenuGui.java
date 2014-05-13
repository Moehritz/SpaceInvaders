package de.mm.spaceinvaders.gui;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuActionListener;
import de.mm.spaceinvaders.gui.model.MenuButton;
import de.mm.spaceinvaders.gui.model.MenuTextField;

public class ServerMenuGui extends Menu
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

		changeNameButton = new MenuButton(new Rectangle(Display.getWidth() - 245, 10,
				235, 30), "Change Name", 20);
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

		changeNameTextField = new MenuTextField(new Rectangle(160, 400,
				Display.getWidth() - 320, 50));
		// changeNameTextField.setText(SpaceInvaders.getInstance().getThePlayer().getName());
		addObject(changeNameButton);
	}

	private void back()
	{
		if (changeName)
		{
			changeNameMode(false);
		}
		else
		{
			SpaceInvaders.getInstance().switchToMainMenu();
		}
	}

	public String getName()
	{
		if (changeNameTextField == null)
		{
			return "";
		}
		return changeNameTextField.getText();
	}

	public void changeNameMode(boolean use)
	{
		changeName = use;
		if (changeName)
		{
			// changeNameTextField.setText(SpaceInvaders.getInstance().getThePlayer()
			// .getName());
			removeObject(changeNameButton);
			addObject(changeNameTextField);
		}
		else
		{
			addObject(changeNameButton);
			removeObject(changeNameTextField);
			// SpaceInvaders.getInstance().getClient().write(new ChangeName(getName()));
		}
	}
}