package cn.laogen.springnetty.vo;

import cn.laogen.springnetty.myentity.MyUser;

/**
 * @Description:
 * @author: zhonglianxi
 * @date: 2020-02-23
 */
public class Message {
    private MyUser user;
    private MyUser toUser;
    private String msg;
//    指令
    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Message(MyUser user, MyUser toUser, String msg, String command) {
        this.user = user;
        this.toUser = toUser;
        this.msg = msg;
        this.command = command;
    }

    public Message() {
    }


    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public MyUser getToUser() {
        return toUser;
    }

    public void setToUser(MyUser toUser) {
        this.toUser = toUser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
