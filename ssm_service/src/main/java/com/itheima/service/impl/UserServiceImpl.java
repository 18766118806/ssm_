package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import com.itheima.untils.PasswordEncoderUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/4 11:57
 **/
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    /**
     * UserInfo 到User类型转换
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findUser (username);
        List<Role> roles = userInfo.getRoles ();
        User user = new User (userInfo.getUsername (), userInfo.getPassword (), userInfo.getStatus () == 0 ? false : true, true, true, true, getAuthority (roles));
        return user;
    }

    /**
     * 将角色转换为需要的类型
     *
     * @param roles
     * @return
     */
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authoritys = new ArrayList ();
        for (Role role : roles) {
            authoritys.add (new SimpleGrantedAuthority ("ROLE_" + role.getRoleName ()));
        }
        return authoritys;
    }


    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll ();
    }


    /**
     * 添加用户
     *
     * @param userInfo
     */

    @Override
    public void saveUser(UserInfo userInfo) {
        //加密
        userInfo.setPassword (PasswordEncoderUntil.encode (userInfo.getPassword ()));
        userDao.saveUser (userInfo);
    }


    /**
     * 查询用户详情
     * @param id
     * @return
     */
    public UserInfo findById(String id){
        return userDao.findById (id);
    }


    /**
     * 查询该用户可以添加的角色
     * @return
     */
    @Override
    public List<Role> findUserOthersRoles(String uid) {
        return userDao.findUsersOthersRole (uid);
    }


    /**
     * 为用户添加角色
     * @param uid
     * @param roleId
     */
    @Override
    public void addRoleToUser(String uid, String[] roleId) {
        for (String s : roleId) {
            userDao.addRoleToUser (uid,s);
        }
    }
}
