package de.mm.spaceinvaders.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Getter;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gamestate.ServerMenu;
import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuActionListener;
import de.mm.spaceinvaders.gui.model.MenuButton;
import de.mm.spaceinvaders.gui.model.MenuText;
import de.mm.spaceinvaders.gui.model.MenuText.TextAlignment;
import de.mm.spaceinvaders.gui.model.MenuTextField;
import de.mm.spaceinvaders.protocol.packets.GameStart;

public class ServerMenuGui extends Menu
{
	private ServerMenu serverMenu;

	private boolean changeName = false;
	private boolean sent = false;

	private MenuButton changeNameButton;
	private MenuTextField changeNameTextField;
	@Getter
	private Map<String, MenuText> players = new HashMap<String, MenuText>();

	public ServerMenuGui(ServerMenu serverMenu)
	{
		this.serverMenu = serverMenu;
	}

	@Override
	public void init()
	{
		super.init();

		MenuButton exit = new MenuButton(new Rectangle(10, 10, 100, 30), "<-", 20);
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
				235, 30), "Name", 20);
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
		changeNameTextField.setText(serverMenu.getOwnName());
		addObject(changeNameButton);
	}

	@Override
	public void draw()
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			SpaceInvaders.getInstance().switchToMainMenu();
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_Q) && !sent && !changeName)
		{
			serverMenu.getConnection().sendPackets(new GameStart());
			//sent = true;
		}
		super.draw();
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
			serverMenu.getOwnName();
			removeObject(changeNameButton);
			addObject(changeNameTextField);
		}
		else
		{
			addObject(changeNameButton);
			removeObject(changeNameTextField);
			if (!serverMenu.getOwnName().equalsIgnoreCase(getName()))
				serverMenu.setOwnName(getName());
		}
	}

	public void addPlayer(String name, String uuid)
	{
		if (players.containsKey(uuid)) return;

		MenuText mt = new MenuText(new Rectangle(0, 100 + players.size() * 50,
				Display.getWidth(), 40), name + " -> " + uuid, 15, Color.blue);
		mt.setAlignment(TextAlignment.CENTER);
		addObject(mt);

		players.put(uuid, mt);
	}

	public void removePlayer(String uuid)
	{
		boolean move = false;
		for (Entry<String, MenuText> e : players.entrySet())
		{
			if (!move && e.getKey().equals(uuid))
				move = true;
			else
			{
				MenuText mt = e.getValue();
				mt.getRect().setY(mt.getRect().getY() - 50);
			}
		}
		removeObject(players.get(uuid));
		players.remove(uuid);
	}
}