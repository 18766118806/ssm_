package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/5 18:19
 **/
public interface RoleService {
    List<Role> findAll();

    void saveRole(Role role);

    Role findById(String id);

    List<Permission> findRolesOthersPermission(String rid);

    void addPermissionToRole(String rid,String[] pid);
}
