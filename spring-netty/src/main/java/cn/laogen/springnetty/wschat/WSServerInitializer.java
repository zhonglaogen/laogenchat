package cn.laogen.springnetty.wschat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WSServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //websocket基于http，需要http的编解码器
        pipeline.addLast(new HttpServerCodec());
        //负责向客户端发送html的页面文件,对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        //对httpmessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
        //几乎在netty的编程中都会用到此handler
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        //=========================以上用于支持http协议===

        /**
         * websocket 服务器处理协议，用于指定给客户端连接访问的路由“/wschat”
         * 本Handler会帮你处理一些繁重复杂的事
         * 会帮你处理握手动作：handShaking（close，ping，pang）ping+pang=心跳
         * 对于websocket来讲，都是以frams进行传输，不同的数据类型对应的frames也不同
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/wschat"));

        //自定义handler
        pipeline.addLast(new ChatHandler());
    }
}
