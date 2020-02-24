package cn.laogen.springnetty.controller;

import cn.laogen.springnetty.dao.InitAllUser;
import cn.laogen.springnetty.myentity.MyUser;
import cn.laogen.springnetty.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class Test {
    @Autowired
    InitAllUser initAllUser;
    @RequestMapping(value = "/get_user",method = RequestMethod.GET)
    public Result<List<MyUser>> test1(){
        return Result.success(initAllUser.getUsers()) ;
    }
}
