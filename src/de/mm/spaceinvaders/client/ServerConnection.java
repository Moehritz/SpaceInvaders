package de.mm.spaceinvaders.client;

import lombok.Getter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.client.netty.PacketHandler;
import de.mm.spaceinvaders.protocol.packets.ChatMessage;
import de.mm.spaceinvaders.protocol.packets.Login;

public class ServerConnection extends PacketHandler
{

	@Getter
	private Channel ch;

	@Override
	public void connected(final Channel ch) throws Exception
	{
		System.out.println("Handler A");
		this.ch = ch;
		SpaceInvaders.getInstance().setConnection(this);
		ch.writeAndFlush(new Login("Moehritz", 1));
		ChannelFuture f = ch.writeAndFlush(new ChatMessage("test"));
		f.addListener(new ChannelFutureListener()
		{

			@Override
			public void operationComplete(ChannelFuture future) throws Exception
			{
				ch.close();
			}
		});
		System.out.println("Handler B");
	}
}
