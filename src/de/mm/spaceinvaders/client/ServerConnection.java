package de.mm.spaceinvaders.client;

import lombok.Getter;
import io.netty.channel.Channel;
import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.client.netty.PacketHandler;
import de.mm.spaceinvaders.protocol.Packet;
import de.mm.spaceinvaders.protocol.packets.Login;

public class ServerConnection extends PacketHandler
{

	@Getter
	private Channel ch;

	@Override
	public void connected(final Channel ch) throws Exception
	{
		this.ch = ch;
		SpaceInvaders.getInstance().getClient().setConnection(this);
		write(new Login(SpaceInvaders.getInstance().getServerMenu().getName(), 1));
		System.out.println("send");
	}

	public void write(Packet packet)
	{
		ch.write(packet);
		ch.flush();
	}
}
