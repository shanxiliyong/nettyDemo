package com.netty.demo.chapter5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;


import java.nio.charset.Charset;

public class Demo4 {
    public static void main(String[] args) {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        System.out.println((char)buf.readByte());
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        System.out.println("readerIndex "+readerIndex  +"writerIndex "+writerIndex);
        buf.writeByte((byte)'?');
        System.out.println("readerIndex "+buf.readerIndex()  +"writerIndex "+buf.writerIndex());
        assert readerIndex == buf.readerIndex();
        assert writerIndex != buf.writerIndex();

        Channel channel = null;
        ByteBufAllocator allocator = channel.alloc();
        ChannelHandlerContext  cxc = null;
        final ByteBufAllocator alloc =
                cxc.alloc();
    }
}
