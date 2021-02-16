package com.xu.community;

import com.xu.community.entity.User;
import com.xu.community.service.UserService;
import com.xu.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private UserService userService;

    @Test
    public void testTextMail(){
        mailClient.sendMail("xu253394699@163.com","Test1","Welcome");
    }

    @Test
    public void testHtmlMail(){
        Context context =new Context();
        context.setVariable("username","德宝");

        String content =templateEngine.process("/mail/demo",context);
        System.out.println(content);

        mailClient.sendMail("xu253394699@163.com","HTML",content);
    }

    @Test
    public void insertUser() {

        userService.register(new User("admin","123456","253394699@qq.com"));
    }
}
