package de.mm.spaceinvaders.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import de.mm.spaceinvaders.client.netty.SpaceClientHandler;
import de.mm.spaceinvaders.client.netty.SpaceDecoder;
import de.mm.spaceinvaders.client.netty.SpaceEncoder;
import de.mm.spaceinvaders.protocol.Protocol;

public class Client
{

	private String target = "localhost:8765";

	public void run() throws InterruptedException
	{
		new Protocol();

		// TODO Falsche Eingaben handlen
		String host = target.split(":")[0];
		int port = Integer.parseInt(target.split(":")[1]);

		EventLoopGroup eventLoops = new NioEventLoopGroup();

		try
		{
			Bootstrap b = new Bootstrap();
			b.group(eventLoops).channel(NioSocketChannel.class)
					.option(ChannelOption.SO_KEEPALIVE, true)
					.handler(new ChannelInitializer<SocketChannel>()
					{

						@Override
						protected void initChannel(SocketChannel ch) throws Exception
						{
							ch.pipeline().addLast(new SpaceEncoder());
							ch.pipeline().addLast(new SpaceDecoder());
							ch.pipeline().addLast(new SpaceClientHandler());
						}
					});
			ChannelFuture f = b.connect(host, port).sync();

			f.channel().closeFuture().sync();
		}
		finally
		{
			eventLoops.shutdownGracefully();
		}
	}

}
