package com.itheima.service.impl;

import com.itheima.dao.PermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/5 19:01
 **/
@Service
@Transactional
public class PermissionImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    /**
     * 查询所有权限
     *
     * @return
     */
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll ();
    }

    /**
     * 添加权限
     * @param permission
     */
    @Override
    public void save(Permission permission) {
        permissionDao.save (permission);
    }

    /**
     * 权限详情
     * @param id
     * @return
     */
    @Override
    public Permission findById(String id) {
        return permissionDao.findById (id);
    }
}
