package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/5 19:00
 **/
public interface PermissionService {

    List<Permission> findAll();

    void save(Permission permission);

    Permission findById(String id);
}
