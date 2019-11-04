package cn.laogen.springnetty.wschat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

@Component
public class ChatServer {
    private static class SingletionWSServer {
        static final ChatServer instance = new ChatServer();
    }

    public static ChatServer getServer() {
        return SingletionWSServer.instance;
    }

    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public ChatServer() {
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(bossGroup, workGroup);
        server.channel(NioServerSocketChannel.class);
        server.childHandler(new WSServerInitializer());

    }

    public void start() {
        //因为是通过springboot的方式启动，springboot自动创建bean，bean继承监听器，会自动执行监听器执行操作，执行完成后才将是完全启动
        //在main方法里执行sync是因为害怕bind异步的，还没有完全初始化完成就执行下面的代码
        //没有关闭的逻辑是因为sping管理了整个java进程，不会结束掉，就不用异步的等待

        //因为close会阻塞spring的main方法，所以不能手动的调用close。sync会阻塞住当前主线程
        //因为不能判断何时关闭seever，所以也没必要优雅的关闭两个线程组了
        //或者可以专门启动一根线程管理server
        this.future = server.bind(8081);

        System.err.println("netty webServer 启动完毕");
    }

}
