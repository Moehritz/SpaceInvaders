package de.mm.spaceinvaders.client;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gamestate.GameState;
import de.mm.spaceinvaders.gamestate.Ingame;
import de.mm.spaceinvaders.gamestate.ServerMenu;
import de.mm.spaceinvaders.gfx.Textures;
import de.mm.spaceinvaders.io.PacketHandler;
import de.mm.spaceinvaders.logic.Bullet;
import de.mm.spaceinvaders.logic.Entity;
import de.mm.spaceinvaders.logic.Player;
import de.mm.spaceinvaders.protocol.Protocol;
import de.mm.spaceinvaders.protocol.packets.ChatMessage;
import de.mm.spaceinvaders.protocol.packets.DespawnEntity;
import de.mm.spaceinvaders.protocol.packets.Login;
import de.mm.spaceinvaders.protocol.packets.Respawn;
import de.mm.spaceinvaders.protocol.packets.SpawnEntity;
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
	public void handle(Respawn respawn) throws Exception
	{
		SpaceInvaders i = SpaceInvaders.getInstance();
		ServerMenu sm = ((ServerMenu) i.getGameState());
		i.switchToIngame(getConnection(), respawn, sm.getOtherPlayers(), sm.getOwnUUID());
	}

	@Override
	public void handle(UpdatePosition pos) throws Exception
	{
		Ingame ig = (Ingame) SpaceInvaders.getInstance().getGameState();
		Entity e = ig.getEntity(pos.getUuid());
		if (e == null) return;
		e.setX(pos.getX());
		e.setY(pos.getY());
		e.setRotation(pos.getRotation());
		e.setSpeed(pos.getSpeed());
	}

	@Override
	public void handle(UpdatePlayerName update) throws Exception
	{
		GameState gs = SpaceInvaders.getInstance().getGameState();
		System.out.println("[NAME] " + update.getNewName());
		if (gs instanceof ServerMenu)
		{
			ServerMenu sm = (ServerMenu) gs;
			sm.removePlayer(update.getUuid());
			sm.addPlayer(update.getNewName(), update.getUuid());
		}
		else if (gs instanceof Ingame)
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
		System.out.println("[JOIN] " + join.getName() + " " + join.getUuid());
		if (gs instanceof ServerMenu)
		{
			ServerMenu sm = (ServerMenu) gs;
			sm.addPlayer(join.getName(), join.getUuid());
		}
		else if (gs instanceof Ingame)
		{
			Ingame ig = (Ingame) gs;
			ig.prepareSpawn(ig.createInvisiblePlayer(join.getUuid(), join.getName()));
		}
	}

	@Override
	public void handle(UserLeave leave) throws Exception
	{
		GameState gs = SpaceInvaders.getInstance().getGameState();
		System.out.println("[QUIT] " + leave.getUuid());
		if (gs instanceof ServerMenu)
		{
			ServerMenu sm = (ServerMenu) gs;
			sm.getGui().removePlayer(leave.getUuid());
		}
		else if (gs instanceof Ingame)
		{
			Ingame ig = (Ingame) gs;
			ig.prepareSpawn(ig.getEntity(leave.getUuid()));
		}
	}

	@Override
	public void handle(SpawnEntity game) throws Exception
	{
		GameState gs = SpaceInvaders.getInstance().getGameState();
		Ingame ig = (Ingame) gs;
		if (ig.getEntity(game.getUuid()) != null) return;
		Entity e = null;
		switch (game.getType())
		{
		case 1:
			e = new Player(Textures.PLAYER.getTexture(), game.getUuid());
			break;
		case 2:
			e = new Bullet(game.getUuid(), Textures.BULLET.getTexture());
			break;
		default:
			break;
		}
		if (e != null)
		{
			e.setX(game.getX());
			e.setY(game.getY());
			e.setRotation(game.getRotation());
			ig.prepareSpawn(e);
		}
	}

	@Override
	public void handle(DespawnEntity despawn) throws Exception
	{
		GameState gs = SpaceInvaders.getInstance().getGameState();
		Ingame ig = (Ingame) gs;

		Entity e = ig.getEntity(despawn.getUuid());
		ig.prepareDespawn(e);
	}
}
