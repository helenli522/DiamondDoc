package com.example.test.service;

import com.example.test.bean.User;
import com.example.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    //将DAO注入Service层
    @Autowired
    UserMapper userMapper;

    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public User Register(User user){
        User user1 = userMapper.getUserByName(user.getUserName());
        User user2 = userMapper.getUserByEmail(user.getEmail());
        if(user1==null && user2==null){
            userMapper.insertUser(user);
            return userMapper.getUserById(user.getUserID());
        }
        else
            return null;
    }

    /**
     * 登录验证
     * @param name
     * @param password
     * @return
     * //登录信息校对
     *     @Select("select * from User where UserName=#{name},Password=#{password}")
     *     User getInfo(String name, String password);
     */
    @Override
    public User loginIn(String name, String password) {
        User user = userMapper.getInfo(name, password);
        if(user != null)
            return user;
        else
            return null;
    }

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        User user = userMapper.getUserById(id);
        if(user != null)
            return user;
        else
            return null;

    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Override
    public User getUserByName(String username){
        User user = userMapper.getUserByName(username);
        if(user != null)
            return user;
        else
            return null;
    }

    /**
     * 根据邮箱查找用户
     * @param email
     * @return
     */
    @Override
    public User getUserByEmail(String email){
        User user = userMapper.getUserByEmail(email);
        if(user != null)
            return user;
        else
            return null;
    }

    /**
     * 根据ID删除用户
     * @param id
     * @return
     */
    @Override
    public int deleteUser(Integer id) {

        return userMapper.deleteUserById(id);

    }


    /**
     * 修改用户密码
     * @param user
     * @return
     */
    @Override
    public int updateUserPwd(User user) {

        return userMapper.updatePwd(user);

    }

    /**
     * 修改用户性别
     * @param user
     * @return
     */
    public int updateGen(User user){

        return userMapper.updateGen(user);

    }

    public int updateBir(User user){
        return userMapper.updateBir(user);
    }

    public int updatePro(User user){
        return userMapper.updatePro(user);
    }





    /**
     * 根据ID退出用户
     * @param user
     * @return
     */
//    @Override
//    public void loginOut(User user){
//        userMapper.exitUser(user);
//
//    }
}
