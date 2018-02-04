package com.netty.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo01 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        boolean localhost = socketChannel.connect(new InetSocketAddress("localhost", 8080));

    }
}
