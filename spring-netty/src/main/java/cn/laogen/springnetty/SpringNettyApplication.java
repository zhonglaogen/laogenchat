package cn.laogen.springnetty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "cn.laogen.springnetty.mapper")
@SpringBootApplication
public class SpringNettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringNettyApplication.class, args);
    }

}
