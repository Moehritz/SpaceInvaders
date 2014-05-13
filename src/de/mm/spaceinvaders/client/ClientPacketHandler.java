package de.mm.spaceinvaders.client;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gamestate.ServerMenu;
import de.mm.spaceinvaders.io.PacketHandler;
import de.mm.spaceinvaders.protocol.Protocol;
import de.mm.spaceinvaders.protocol.packets.ChatMessage;
import de.mm.spaceinvaders.protocol.packets.GameStart;
import de.mm.spaceinvaders.protocol.packets.Login;
import de.mm.spaceinvaders.protocol.packets.UpdatePosition;
import de.mm.spaceinvaders.protocol.packets.UserJoin;
import de.mm.spaceinvaders.protocol.packets.UserLeave;

public class ClientPacketHandler extends PacketHandler
{
	@Override
	public void connected() throws Exception
	{
		ServerMenu serverMenu = SpaceInvaders.getInstance().switchToServerMenu(
				getConnection());
		getConnection().sendPackets(
				new Login(serverMenu.getOwnName(), serverMenu.getOwnUUID(),
						Protocol.PROTOCOL_VERSION));
	}

	@Override
	public void disconnected() throws Exception
	{
		SpaceInvaders.getInstance().switchToMainMenu();
	}

	@Override
	public void handle(GameStart start) throws Exception
	{

	}

	@Override
	public void handle(UpdatePosition pos) throws Exception
	{

	}

	@Override
	public void handle(ChatMessage chatMessage) throws Exception
	{
		System.out.println("[CHAT] " + chatMessage.getMessage());

	}

	@Override
	public void handle(UserJoin join) throws Exception
	{

	}

	@Override
	public void handle(UserLeave leave) throws Exception
	{

	}
}
