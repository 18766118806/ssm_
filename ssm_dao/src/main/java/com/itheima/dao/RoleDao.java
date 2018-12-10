package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/4 10:43
 **/
public interface RoleDao {
    /**
     * 根据用户id查询角色
     *
     * @param uid
     * @return
     */
    @Select("select * from role where id in (select roleid from users_role where userid=#{uid})")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionById"))
    })
    List<Role> findById(String uid);


    /**
     * 查询所有角色
     *
     * @return
     */
    @Select("select * from role")
    List<Role> findAll();


    /**
     * 添加角色
     *
     * @param role
     */
    @Insert("insert into role (rolename,roledesc) values (#{roleName},#{roleDesc}) ")
    void saveRole(Role role);

    /**
     * 根据角色id查询角色详情
     *
     * @param rid
     * @return
     */
    @Select("select * from role where id = #{rid}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionById"))
    })
    Role findByrid(String rid);


    /**
     * 根据角色id查询该角色可以添加的权限
     * @param rid
     * @return
     */
    @Select ("select * from permission where id not in (select permissionid from role_permission where roleid=#{rid})")
    List<Permission> findRoilsOthersPermission(String rid);


    @Insert ("insert into role_permission (roleid,permissionid) values (#{rid},#{pid})")
    void addPermissionToRole(@Param ("rid") String rid,@Param ("pid") String pid);
}
