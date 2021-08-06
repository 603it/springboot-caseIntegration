package com.halo.javamail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 罗铭森
 * @date 2021/8/6 9:31
 * @description TODO(这里用一句话描述这个类的作用)
 */
@SpringBootApplication
@MapperScan("com.halo.javamail.mapper")
@Controller
public class JavamailApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavamailApplication.class, args);
    }

    @RequestMapping("")
    public String register(){
        return "register";
    }
}
