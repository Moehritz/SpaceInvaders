package de.mm.spaceinvaders.client.netty;

import lombok.NonNull;
import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.client.ServerConnection;
import de.mm.spaceinvaders.protocol.PacketWrapper;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SpaceClientHandler extends ChannelHandlerAdapter
{

	@NonNull
	private PacketHandler handler;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception
	{
		System.out.println("Connected");
		handler = new ServerConnection();
		handler.connected(ctx.channel());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception
	{
		handler.disconnected(ctx.channel());
		SpaceInvaders.getInstance().toMainMenu();
		System.out.println("Disconnected");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{
		System.out.println("Packet empfangen");
		PacketWrapper p = (PacketWrapper) msg;
		try
		{
			p.getPacket().handle(handler);
		}
		finally
		{
			p.release();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception
	{
		cause.printStackTrace();
		handler.disconnected(ctx.channel());
		ctx.close();
	}
}
