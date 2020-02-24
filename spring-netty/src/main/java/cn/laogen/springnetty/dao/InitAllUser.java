package cn.laogen.springnetty.dao;

import cn.laogen.springnetty.myentity.MyUser;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: zhonglianxi
 * @date: 2020-02-21
 */
@Repository
public class InitAllUser {

    private List<MyUser> users = new ArrayList<>();

    @PostConstruct
    public void init(){
        for (int i = 0; i < 10; i++) {
             users.add(new MyUser(String.valueOf(i), "name" + i));
        }
    }

    public List<MyUser> getUsers() {
        return users;
    }
}
