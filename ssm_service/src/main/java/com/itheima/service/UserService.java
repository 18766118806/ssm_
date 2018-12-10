package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/4 11:57
 **/
public interface UserService extends UserDetailsService {
    List<UserInfo> findAll();

    void saveUser(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findUserOthersRoles(String uid);

    void addRoleToUser(String uid,String[] roleId);
}
