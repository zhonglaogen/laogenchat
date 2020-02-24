package cn.laogen.springnetty.myentity;

/**
 * @Description:
 * @author: zhonglianxi
 * @date: 2020-02-21
 */
public class MyUser {
    private String id;
    private String name;

    public MyUser() {
    }

    public MyUser(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
