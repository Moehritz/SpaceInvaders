package de.mm.spaceinvaders.gui;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Getter;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gamestate.ServerMenu;
import de.mm.spaceinvaders.gui.model.Menu;
import de.mm.spaceinvaders.gui.model.MenuActionListener;
import de.mm.spaceinvaders.gui.model.MenuButton;
import de.mm.spaceinvaders.gui.model.MenuText;
import de.mm.spaceinvaders.gui.model.MenuTextField;
import de.mm.spaceinvaders.gui.model.Text;
import de.mm.spaceinvaders.gui.model.Text.TextAlignment;
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

		MenuButton exit = new MenuButton(new Rectangle2D.Double(10, 10, 100, 30), new Text("<-"));
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

		changeNameButton = new MenuButton(new Rectangle2D.Double(
				Display.getWidth() - 245, 10, 235, 30), new Text("name"));
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

		changeNameTextField = new MenuTextField(new Rectangle2D.Double(160, 400,
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
			serverMenu.getConnection()
					.sendPackets(new GameStart(serverMenu.getOwnUUID()));
			sent = true;
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

	public void addPlayer(String name)
	{
		if (players.containsKey(name)) return;

		MenuText mt = new MenuText(new Rectangle2D.Double(0, 100 + players.size() * 50,
				Display.getWidth(), 40), new Text(name));
		mt.getText().setAlign(TextAlignment.CENTER);
		addObject(mt);

		players.put(name, mt);
	}

	public void removePlayer(String name)
	{
		removeObject(players.get(name));
		players.remove(name);
		// Rearrange List
		int i = 0;
		for (Entry<String, MenuText> e : players.entrySet())
		{
			e.getValue().getRect().y = 100 + i * 50;
			i++;
		}
	}
}