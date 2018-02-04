package com.netty.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class SelectorClient {
    public static void main(String[] args) throws IOException {
        SelectorClient client = new SelectorClient();
        Selector sel = client.initClient();
        client.listener(sel);

    }


    public Selector initClient()throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("localhost",8080));
        Selector sel = Selector.open();
        channel.register(sel,SelectionKey.OP_CONNECT);
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
                     if(key.isConnectable()){
                         SocketChannel channel = (SocketChannel) key.channel();
                         if(channel.isConnectionPending()){
                             channel.finishConnect();
                         }
                         channel.write(ByteBuffer.wrap("Hello Server".getBytes()));
                         channel.register(key.selector(),SelectionKey.OP_READ);
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
             while(buffer.hasRemaining()){
                 System.out.print(buffer.get());
             }
            System.out.println();
            buffer.clear();
            read = channel.read(buffer);
        }
    }
}
