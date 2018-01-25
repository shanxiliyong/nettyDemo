package com.netty.demo.chapter5;

import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

public class Demo1 {

    public static void main(String[] args) {
        final CompositeByteBuf byteBuf = Unpooled.compositeBuffer();
        final int readableBytes = byteBuf.readableBytes();
        final byte[] bytes = new byte[readableBytes];
        byteBuf.getBytes(byteBuf.readerIndex(),bytes);



    }
}
