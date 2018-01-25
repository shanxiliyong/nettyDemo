package com.netty.demo.chapter5;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class Demo2 {

    public static void main(String[] args) {
        final ByteBuf byteBuf = Unpooled.copiedBuffer("Netty in action rocks", CharsetUtil.UTF_8);
        final ByteBuf slice = byteBuf.copy(0, 3);
        System.out.println(slice.toString(CharsetUtil.UTF_8));
        byteBuf.setByte(1,(byte)'L');
        System.out.println(slice.toString(CharsetUtil.UTF_8));


    }
}
