package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/4 11:16
 **/
public interface PermissionDao {
    @Select ("select * from permission where id in(select permissionid from role_permission where roleid=#{roleid}) ")
    List<Permission> findPermissionById(String roleid);

    @Select ("select * from permission")
    List<Permission> findAll();

    @Insert ("insert into permission (permissionname,url) values (#{permissionName},#{url})")
    void save(Permission permission);

    @Select ("select * from permission where id = #{id}")
    Permission findById(String id);
}
