package de.mm.spaceinvaders.client;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gamestate.GameState;
import de.mm.spaceinvaders.gamestate.Ingame;
import de.mm.spaceinvaders.gamestate.ServerMenu;
import de.mm.spaceinvaders.gfx.Textures;
import de.mm.spaceinvaders.io.PacketHandler;
import de.mm.spaceinvaders.logic.Entity;
import de.mm.spaceinvaders.logic.Player;
import de.mm.spaceinvaders.protocol.Protocol;
import de.mm.spaceinvaders.protocol.packets.ChatMessage;
import de.mm.spaceinvaders.protocol.packets.GameStart;
import de.mm.spaceinvaders.protocol.packets.Login;
import de.mm.spaceinvaders.protocol.packets.UpdatePlayerName;
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
		SpaceInvaders.getInstance().switchToIngame(getConnection());
	}

	@Override
	public void handle(UpdatePosition pos) throws Exception
	{
		Ingame ig = (Ingame) SpaceInvaders.getInstance().getGameState();
		Entity e = ig.getEntity(pos.getUuid());
		e.setX(pos.getX());
		e.setY(pos.getY());
		e.setRotation(pos.getRotation());
		e.setSpeed(pos.getSpeed());
	}

	@Override
	public void handle(UpdatePlayerName update) throws Exception
	{
		GameState gs = SpaceInvaders.getInstance().getGameState();
		if (gs instanceof ServerMenu)
		{
			ServerMenu sm = (ServerMenu) gs;
			sm.getGui().removePlayer(update.getUuid());
			sm.getGui().addPlayer(update.getNewName(), update.getUuid());
		}
		else
		{
			Ingame ig = (Ingame) gs;
			Player p = (Player) ig.getEntity(update.getUuid());
			p.setName(update.getNewName());
		}
	}

	@Override
	public void handle(ChatMessage chatMessage) throws Exception
	{
		System.out.println("[CHAT] " + chatMessage.getMessage());
	}

	@Override
	public void handle(UserJoin join) throws Exception
	{
		GameState gs = SpaceInvaders.getInstance().getGameState();
		if (gs instanceof ServerMenu)
		{
			ServerMenu sm = (ServerMenu) gs;
			sm.getGui().addPlayer(join.getName(), join.getUuid());
		}
		else
		{
			Ingame ig = (Ingame) gs;
			Player p = new Player(Textures.PLAYER.getTexture(), join.getUuid());
			p.setName(join.getName());
			p.setVisible(false);
			ig.prepareSpawn(p);
		}
	}

	@Override
	public void handle(UserLeave leave) throws Exception
	{
		GameState gs = SpaceInvaders.getInstance().getGameState();
		if (gs instanceof ServerMenu)
		{
			ServerMenu sm = (ServerMenu) gs;
			sm.getGui().removePlayer(leave.getUuid());
		}
		else
		{
			Ingame ig = (Ingame) gs;
			ig.prepareSpawn(ig.getEntity(leave.getUuid()));
		}
	}
}
