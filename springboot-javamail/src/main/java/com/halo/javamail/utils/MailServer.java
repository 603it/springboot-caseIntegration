package com.halo.javamail.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author 罗铭森
 * @date 2021/8/6 10:13
 * @description TODO(这里用一句话描述这个类的作用)
 */
@Component
public class MailServer {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    MailProperties mailProperties;

    /**
     * 发送激活邮件
     * @param to 收件者
     * @param subject 邮件主题
     * @param content 文本内容
     */
    public void sendActivationMail(String to,String subject,String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(to);
            helper.setFrom(mailProperties.getUsername());
            helper.setSubject(subject);
            helper.setSentDate(new Date());

            helper.setText(content, true);
            javaMailSender.send(message);
            //日志信息
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
