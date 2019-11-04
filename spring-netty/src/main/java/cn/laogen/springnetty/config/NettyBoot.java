package cn.laogen.springnetty.config;

import cn.laogen.springnetty.wschat.ChatServer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 负责：所有的Bean被成功装载，后处理Bean被检测并激活，所有Singleton Bean 被预实例化，ApplicationContext容器已就绪可用
 * @Description: 启动netty服务器的
 * @author: zhonglianxi
 * @date: 2019-10-26
 */
@Component
public class NettyBoot implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if (contextRefreshedEvent.getApplicationContext().getParent()==null){
            ChatServer.getServer().start();
            System.out.println(Thread.currentThread().getName());
        }
    }
}
