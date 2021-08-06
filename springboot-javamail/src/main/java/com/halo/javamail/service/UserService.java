package com.halo.javamail.service;

import com.halo.javamail.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UserService extends IService<User> {

    String register(User user);

    void activation(String code);

    User login(User user);
}
