package cn.laogen.springnetty.wschat;

import cn.laogen.springnetty.myentity.Command;
import cn.laogen.springnetty.util.Const;
import cn.laogen.springnetty.vo.Message;
import cn.laogen.springnetty.wschat.Service.ChatService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import javafx.beans.binding.ObjectExpression;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * SimpleChannelInboundHandler会自动释放bytebuf内存,ChannelInboundHandlerAdapter不会释放
 * 处理消息的handler
 * TextWebSocketFrame：在netty中是专门用于处理文本的对象，frame是消息载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用于记录和管理所有客户端的Channel
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 当客户端连接服务端之后（打开连接）
     * 获取客户端的Channel，并且放到Channelgroup中进行管理
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.name());
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发handlerRemoved，ChannelGroup会自动移除对应客户端的Channel
//        clients.remove(ctx.channel());
        System.out.println("客户端断开，Channel对应的长id" + ctx.channel().id().asLongText());
        System.out.println("客户端断开，Channel对应的短id" + ctx.channel().id().asShortText());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        //获取客户端传来的数据
        String text = msg.text();
        JSON parse = (JSON)JSONObject.parse(msg.text());
        Message omsg = JSONObject.toJavaObject(parse, Message.class);
        if (omsg instanceof Message){
            Message jsonMsg = (Message)omsg;
            System.out.println("接收到的数据：" + text);
            if(StringUtils.isNotBlank(jsonMsg.getCommand())){
                String methodName = Command.getMethod(jsonMsg.getCommand());
                if (StringUtils.isNotBlank(methodName)){
                    //根据不同的操作码调用不同的方法
                    Class<ChatService> chatServiceClass = ChatService.class;
                    Method declaredMethod =
                            chatServiceClass.getMethod(methodName,ctx.channel().getClass(),jsonMsg.getClass());
                    declaredMethod.setAccessible(true);

                    declaredMethod.invoke(Const.chatService,ctx.channel(),jsonMsg);

                }
            }
        }

        //两种方法效果一样
//        clients.writeAndFlush( new TextWebSocketFrame(
//                "服务器收到消息" + LocalDateTime.now()
//                        + "消息为" + text));
//
    }
}
