package com.xu.community.dao;

import com.xu.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectById(int id);

    User selectByName(String name);

    User selectByEmail(String email);

    void insertUser(User user);

    void updateStatus(int userId,int status);

    int updateHeader(int userId,String headerUrl);

    void updatePassword(int userId,String password);
}
