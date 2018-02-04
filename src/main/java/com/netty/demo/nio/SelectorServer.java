package com.netty.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;


public class SelectorServer {
    public static void main(String[] args) throws IOException {
        SelectorServer server = new SelectorServer();
        Selector sel = server.initServer();
        server.listener(sel);

    }


    public Selector initServer()throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(8080));
        Selector sel = Selector.open();
        channel.register(sel, SelectionKey.OP_ACCEPT);
        return sel;
    }

    public void listener(Selector sel) throws IOException {
        while(true){
            int select = sel.select();
            if(select>0){
                Set<SelectionKey> selectionKeys = sel.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                 while(iterator.hasNext()){
                     SelectionKey key = iterator.next();
                     iterator.remove();
                     if(key.isAcceptable()){
                         ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                         SocketChannel socketChannel = channel.accept();
                         socketChannel.configureBlocking(false);
                         socketChannel.register(key.selector(),SelectionKey.OP_READ);
                     }else{
                         read(key);
                     }
                 }
            }
        }



    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);
        while (read>0){
            buffer.flip();
            byte[] bytes = buffer.array();
            System.out.println(new String(bytes));
            buffer.clear();
            read = channel.read(buffer);
        }
        channel.write(ByteBuffer.wrap("Hello Client".getBytes()));

    }
}
