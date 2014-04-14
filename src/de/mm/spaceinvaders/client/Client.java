package de.mm.spaceinvaders.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import de.mm.spaceinvaders.io.ConnectionHandler;
import de.mm.spaceinvaders.io.FrameDecoder;
import de.mm.spaceinvaders.io.FramePrepender;
import de.mm.spaceinvaders.io.SpaceDecoder;
import de.mm.spaceinvaders.io.SpaceEncoder;

public class Client
{

	public static void connect(String host, int port) throws InterruptedException
	{
		EventLoopGroup eventLoops = new NioEventLoopGroup();

		Bootstrap b = new Bootstrap();
		b.group(eventLoops).channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>()
				{

					@Override
					protected void initChannel(SocketChannel channel) throws Exception
					{
						channel.pipeline().addLast(new FrameDecoder());
						channel.pipeline().addLast(new FramePrepender());
						channel.pipeline().addLast(new SpaceEncoder());
						channel.pipeline().addLast(new SpaceDecoder());
						channel.pipeline()
								.addLast(
										new ConnectionHandler(channel,
												new ClientPacketHandler()));
					}
				});
		b.connect(host, port).await();
	}

}
