package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/5 18:20
 **/
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll ();
    }


    /**
     * 添加角色
     *
     * @param role
     */
    @Override
    public void saveRole(Role role) {
        roleDao.saveRole (role);
    }


    /**
     * 根据角色id查询详情
     * @param id
     * @return
     */
    @Override
    public Role findById(String id) {
        return roleDao.findByrid (id);
    }


    /**
     * 根据角色id查询该角色可以添加的权限
     * @param rid
     * @return
     */
    @Override
    public List<Permission> findRolesOthersPermission(String rid) {
        return roleDao.findRoilsOthersPermission (rid);
    }


    /**
     * 为角色添加权限
     * @param rid
     * @param pids
     */
    @Override
    public void addPermissionToRole(String rid, String[] pids) {
        for (String pid : pids) {
            roleDao.addPermissionToRole (rid,pid);
        }
    }
}
