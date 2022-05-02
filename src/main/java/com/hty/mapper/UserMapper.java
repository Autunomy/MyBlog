package com.hty.mapper;

import com.hty.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    //根据ID获取用户
    User queryUserById(String user_id);

    //根据用户名称获取用户
    User queryUserByName(String user_name);

    //根据用户邮箱获取用户
    User queryUserByEmail(String user_email);

    //根据用户电话获取用户
    User queryUserByPhone(String user_phone);

    //获取全部用户
    List<User> queryAllUser();

    //添加一个用户
    int addUser(User user);

    //根据ID删除用户
    int deleteUserById(String user_id);

    //根据用户姓名删除用户
    int deleteUserByName(String user_name);

    //修改用户信息
    int updateUser(User user);
}
