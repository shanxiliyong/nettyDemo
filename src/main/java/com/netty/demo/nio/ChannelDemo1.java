package com.netty.demo.nio;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelDemo1 {
    public static void main(String[] args) throws IOException {
        String path = ChannelDemo1.class.getResource("").getPath();
        System.out.println(path);
        RandomAccessFile r = new RandomAccessFile( "E:\\work_oxygen\\netty3\\src\\main\\java\\com\\lee\\nio\\ChannelDemo1", "rw");
        read(r);
        write(r);
        r.close();
    }

    private static void write(RandomAccessFile r) throws IOException {
        FileChannel channel = r.getChannel();
        String addStr = "hello writer\r\n";
        ByteBuffer buffer = ByteBuffer.allocate(1044);
        buffer.clear();

         buffer.put(addStr.getBytes());
        buffer.flip();
        while (buffer.hasRemaining()){
            channel.write(buffer);
        }


    }

    private static void read(RandomAccessFile r) throws IOException {
        FileChannel fileChannel = r.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        int read = fileChannel.read(byteBuffer);
        while (read!=-1){
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                byte b = byteBuffer.get();
                System.out.print((char)b);
            }
            System.out.println();
            byteBuffer.clear();
            read = fileChannel.read(byteBuffer);

        }

    }
}
