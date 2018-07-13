package com.hulb.java.netty.ch2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author hulb
 * @date 2018/7/11 下午11:42
 */
public class EchoServer {

    public static void main(String[] args) throws Exception{
        new EchoServer().start();
    }

    public void start() throws Exception{
        final EchoServerHander echoServerHander = new EchoServerHander();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(8888)).childHandler(new ChannelInitializer(){
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(echoServerHander);
                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {
            group.shutdownGracefully().sync();
        }

    }



}
