package com.netty.demo.chapter4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class NettyNioServer {

    public void server() throws Exception{
       final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("hello nio server", CharsetUtil.UTF_8));
        EventLoopGroup group  = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(8080))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){


                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                            }
                        }) ;
                        }
                    });
            final ChannelFuture f = b.bind().sync();
            final ChannelFuture sync = f.channel().closeFuture().sync();
        }finally{
            group.shutdownGracefully().sync();
        }


    }
}
