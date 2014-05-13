package de.mm.spaceinvaders.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import de.mm.spaceinvaders.SpaceInvaders;
import de.mm.spaceinvaders.io.ConnectionHandler;
import de.mm.spaceinvaders.io.PipelineInitiator;

public class Client
{

	public static void connect(String host, int port) throws Exception
	{
		final EventLoopGroup eventLoops = new NioEventLoopGroup();

		Bootstrap b = new Bootstrap();
		b.group(eventLoops);
		b.channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<SocketChannel>()
		{

			@Override
			protected void initChannel(SocketChannel channel) throws Exception
			{
				PipelineInitiator.initPipeline(channel.pipeline());
				channel.pipeline().addLast(
						new ConnectionHandler(channel, new ClientPacketHandler()));
			}
		});
		ChannelFuture f = b.connect(host, port);
		f.addListener(new ChannelFutureListener()
		{

			@Override
			public void operationComplete(ChannelFuture future) throws Exception
			{
				if (!future.isSuccess())
				{
					SpaceInvaders.getInstance().switchToMainMenu();
				}
			}
		});
		f.channel().closeFuture().sync();
		eventLoops.shutdownGracefully();
		System.out.println("Netty shutdown - COMPLETE");
	}

}
