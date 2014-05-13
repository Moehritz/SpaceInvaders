package de.mm.spaceinvaders.gamestate;

import java.util.UUID;

import lombok.Getter;
import de.mm.spaceinvaders.gui.ServerMenuGui;
import de.mm.spaceinvaders.io.ConnectionHandler;
import de.mm.spaceinvaders.protocol.packets.ChangeName;

@Getter
public class ServerMenu extends GameState
{
	private String ownName = System.getProperty("user.name");
	private final String ownUUID = UUID.randomUUID().toString();

	private ConnectionHandler connection;

	public ServerMenu(ConnectionHandler connection)
	{
		this.connection = connection;
	}

	@Override
	public void init()
	{
		super.visibleMenu = new ServerMenuGui(this);
	}

	public void setOwnName(String newName)
	{
		this.ownName = newName;
		connection.sendPackets(new ChangeName(newName));
	}

	@Override
	public void end()
	{
		getConnection().closeConnection();
	}

	public ServerMenuGui getGui()
	{
		return (ServerMenuGui) super.visibleMenu;
	}
}