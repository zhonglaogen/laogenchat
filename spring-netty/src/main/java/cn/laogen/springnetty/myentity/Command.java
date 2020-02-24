package cn.laogen.springnetty.myentity;

public enum Command {
    REGIST("1","regist"),
    SEND_MSG("2","sendFriend"),
    CREATE_GROUP("3","createGroup"),
    JOIN_GROUP("4","joinGroup"),
    SEND_MSG_GROUP("5","sendGroup");

    private String optCode;
    private String optEvent;

    Command(String optCode, String optEvent) {
        this.optCode = optCode;
        this.optEvent = optEvent;
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    public String getOptEvent() {
        return optEvent;
    }

    public void setOptEvent(String optEvent) {
        this.optEvent = optEvent;
    }

    public static String getMethod(String optCode){
        Command[] values = Command.values();
        for (Command command: values){
            if(command.optEvent.equals(optCode)){
                return command.optEvent;
            }
        }
        return null;
    }

}
