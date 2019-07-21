package com.chendehe.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.Charset;

public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

  /**
   * 读取服务端通道数据.
   *
   * @param ctx 消息
   * @param msg 来获取通道
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    if (msg instanceof ByteBuf) {
      System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));
    }

    ctx.channel().close();
  }
}
