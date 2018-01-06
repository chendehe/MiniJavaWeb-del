package com.chendehe.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {

  public static void main(String[] args) {

    Bootstrap client = new Bootstrap();
    // 绑定线程组，处理读写和连接事件
    EventLoopGroup group = new NioEventLoopGroup();
    client.group(group);
    // 绑定客户端通道
    client.channel(NioSocketChannel.class);
    // 给NioSocketChannel初始化handler
    client.handler(new ChannelInitializer<NioSocketChannel>() {
      @Override
      protected void initChannel(NioSocketChannel nsc) {
        nsc.pipeline().addLast(new SimpleClientHandler());
        // 编码
        nsc.pipeline().addLast(new StringEncoder());
        nsc.pipeline().addLast(new LengthFieldPrepender(4, false));
      }
    });

    try {
      ChannelFuture future = client.connect("localhost", 1234).sync();
      // 发送
      String msg = "Hello netty";
      future.channel().writeAndFlush(msg);

      future.channel().closeFuture().sync();
      System.out.println("client end");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
