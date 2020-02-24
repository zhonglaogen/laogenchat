package cn.laogen.springnetty.util;

import cn.laogen.springnetty.wschat.Service.ChatService;
import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @author: zhonglianxi
 * @date: 2020-02-23
 */
public class Const {

    public static ChatService chatService = new ChatService();

    public static Map<String,NioSocketChannel> channelMap = new ConcurrentHashMap<>();
}
