package de.mm.spaceinvaders.client;

import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.gfx.Textures;
import de.mm.spaceinvaders.io.PacketHandler;
import de.mm.spaceinvaders.logic.Entity;
import de.mm.spaceinvaders.logic.Player;
import de.mm.spaceinvaders.protocol.Protocol;
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
		SpaceInvaders.getInstance().setClient(getConnection());
		System.out.println(getConnection());
		getConnection().write(
				new Login(SpaceInvaders.getInstance().getServerMenu().getName(),
						SpaceInvaders.getInstance().getThePlayer().getUuid(),
						Protocol.PROTOCOL_VERSION));
	}

	@Override
	public void handle(GameStart start) throws Exception
	{
		System.out.println("Start the game! :)");
		SpaceInvaders.getInstance().startGame();
	}

	@Override
	public void handle(UpdatePosition pos) throws Exception
	{
		Entity e = SpaceInvaders.getInstance().getEntity(pos.getUuid());
		if (e != null)
		{
			e.setX(pos.getX());
			e.setY(pos.getY());
			e.setRotation(pos.getRotation());
		}
	}

	@Override
	public void handle(UserJoin join) throws Exception
	{
		Player p = new Player(Textures.PLAYER.getTexture(), join.getUuid());
		p.setName(join.getName());
		SpaceInvaders.getInstance().getOutstandingSpawns().add(p);
		System.out.println(p.getName() + " joined the fight!");
	}

	@Override
	public void handle(UserLeave leave) throws Exception
	{
		Player p = (Player) SpaceInvaders.getInstance().getEntity(leave.getUuid());
		System.out.println(p.getName() + " left the battle!");
		SpaceInvaders.getInstance().getOutstandingSpawns().add(p);
	}
}
