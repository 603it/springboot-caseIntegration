package com.halo.javamail.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halo.javamail.pojo.User;
import com.halo.javamail.service.UserService;
import com.halo.javamail.mapper.UserMapper;
import com.halo.javamail.utils.MailServer;
import com.halo.javamail.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {



    @Autowired
    private UserMapper userMapper;

    /**
     * 注入邮件接口
     */
    @Autowired
    private MailServer mailServer;

    /**
     * 用户注册，同时发送一封激活邮件
     *
     * @param user
     * @return
     */
    @Override
    public String register(User user) {
        //简单做了一个参数检验：注册用户名不能相同
        if (userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername())) == null) {
            user.setStatus(0);
            String code = UUIDUtil.getUUID() + UUIDUtil.getUUID();
            user.setCode(code);
            userMapper.insert(user);

            System.out.println("code:" + code);
            //主题
            String subject = "来自halo的激活邮件";
            //上面的激活码发送到用户注册邮箱
            String context = "<a href=\"http://localhost:8088/user/activation?code=" + code + "\">激活请点击:" + code + "</a>";
            //发送激活邮件
            mailServer.sendActivationMail(user.getEmail(), subject, context);
            return "success";
        }else {
            return "register";
        }
    }

    /**
     * 根据激活码code进行查询用户，之后再进行修改状态
     *
     * @param code
     * @return
     */
    @Override
    public void activation(String code) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("code", code));
        if (user != null) {
            //把code验证码清空，已经不需要了
            user.setCode("");
            //用户状态修改为1，表示已激活
            user.setStatus(1);
            userMapper.updateById(user);
        }
    }

    /**
     * 根据用户名，密码进行登陆
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", user.getUsername())
                .eq("password", user.getPassword()));
    }

}




