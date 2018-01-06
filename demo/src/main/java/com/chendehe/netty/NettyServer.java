package com.chendehe.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {

  public static void main(String[] args) {

    ServerBootstrap server = new ServerBootstrap();
    // 绑定两个线程组分别用来
    // parentGroup: 处理客户端通道的accept事件
    // childGroup: 处理读写事件
    EventLoopGroup parentGroup = new NioEventLoopGroup();
    EventLoopGroup childGroup = new NioEventLoopGroup();
    server.group(parentGroup, childGroup);
    // 绑定服务端通道NioServerSocketChannel
    server.channel(NioServerSocketChannel.class);
    // 给读写事件的线程通道绑定handler去真正处理读写
    server.childHandler(new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel sc) {
        sc.pipeline().addLast(new SimpleServerHandler());
        // 编码
        sc.pipeline().addLast(new StringEncoder());
        sc.pipeline().addLast(new LengthFieldPrepender(4, false));
      }
    });
    // 监听端口
    try {
      ChannelFuture future = server.bind(1234).sync();
      future.channel().closeFuture().sync();
      System.out.println("server end");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
