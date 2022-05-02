package com.hty.service;

import com.hty.mapper.UserMapper;
import com.hty.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserById(String user_id) {
        return userMapper.queryUserById(user_id);
    }

    @Override
    public User queryUserByName(String user_name) {
        return userMapper.queryUserByName(user_name);
    }

    @Override
    public User queryUserByEmail(String user_email) {
        return userMapper.queryUserByEmail(user_email);
    }

    @Override
    public User queryUserByPhone(String user_phone) {
        return userMapper.queryUserByPhone(user_phone);
    }

    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int deleteUserById(String user_id) {
        return userMapper.deleteUserById(user_id);
    }

    @Override
    public int deleteUserByName(String user_name) {
        return userMapper.deleteUserByName(user_name);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
