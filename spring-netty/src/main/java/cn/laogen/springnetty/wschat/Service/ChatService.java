package cn.laogen.springnetty.wschat.Service;

import cn.laogen.springnetty.util.Const;
import cn.laogen.springnetty.vo.Message;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @Description:
 * @author: zhonglianxi
 * @date: 2020-02-23
 */
public class ChatService {

    public  void regist(NioSocketChannel channel, Message message){
        Const.channelMap.put(message.getUser().getName(),channel);

    }
    public  void sendFriend(NioSocketChannel channel, Message message){
        NioSocketChannel channel2 = Const.channelMap.get(message.getToUser().getName());
        NioSocketChannel channel1 = Const.channelMap.get(message.getUser().getName());
        String toMsg = "[" + message.getUser().getName() + "发来消息] :" + "\n" + message.getMsg() + "\n";
        String msg =  "[我] :" + "\n" + message.getMsg() + "\n";


//        账号在其他地方被登录
        if (!channel.id().asShortText().equals(channel1.id().asShortText())){
            channel.writeAndFlush(new TextWebSocketFrame("被迫下线"));
            return;
        }


//        对方不在线
        if (channel2 != null){
            channel2.writeAndFlush(new TextWebSocketFrame(toMsg));
            channel1.writeAndFlush(new TextWebSocketFrame(msg));
        }else {
            channel1.writeAndFlush(new TextWebSocketFrame(msg + "#对方不在线#\n"));
        }

    }
}
