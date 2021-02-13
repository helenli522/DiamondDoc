package com.example.test.service;

import com.example.test.bean.User;

public interface UserService {

    //注册
    User Register(User user);

    //登录校验信息
    User loginIn(String name, String password);

    //根据用户id查找用户
    User getUserById(Integer id);

    //根据用户名查找用户
    User getUserByName(String username);

    //根据邮箱查找用户
    User getUserByEmail(String email);

    //删除用户
    int deleteUser(Integer id);



    //修改密码
    public int updateUserPwd(User user);

    //修改性别
    public int updateGen(User user);

    //修改生日
    public int updateBir(User user);

    //修改头像
    public int updatePro(User user);

}
