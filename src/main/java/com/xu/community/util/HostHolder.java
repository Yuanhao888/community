package com.xu.community.util;

import com.xu.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * 持有用户信息，用于替代session对象
 */
@Component
public class HostHolder {
    private ThreadLocal<User> users=new ThreadLocal<User>();
    public void setUser(User user){
        users.set(user);
    }
    public User getUser(){
        return users.get();
    }
    //请求结束，清理
    public void clear(){
        users.remove();
    }
}
