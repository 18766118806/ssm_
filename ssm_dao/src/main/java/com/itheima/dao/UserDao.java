package com.itheima.dao;

import com.itheima.domain.Member;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/3 21:23
 **/
public interface UserDao {

    /**
     * 登录
     *
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    @Results({@Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "email", property = "email"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.RoleDao.findById"))
    })
    UserInfo findUser(String username);


    /**
     * 查询所有用户
     *
     * @return
     */
    @Select("select * from users")
    @Results({
            @Result(id = true,column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "email", property = "email"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.RoleDao.findById"))

    })
    List<UserInfo> findAll();


    /**
     * 添加用户
     *
     * @param userInfo
     */
    @Insert("insert into users(email,username,password,phoneNum,status) values (#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveUser(UserInfo userInfo);


    /**
     * 查询用户详情
     *
     * @return
     */

    @Select("select * from users where id =#{id} ")
    @Results({
            @Result(id = true,column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "email", property = "email"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.RoleDao.findById"))


    })
    UserInfo findById(String id);


    /**
     * 查询该用户可以添加的角色
     * @param id
     * @return
     */
    @Select ("select * from role where id not in (select roleid from users_role where userid=#{id})")
    List<Role> findUsersOthersRole(String id);


    /**
     * 为用户添加角色
     * @param uid
     * @param rid
     */
    @Insert ("insert into users_role (userid,roleid) values (#{uid},#{rid})")
    void addRoleToUser(@Param ("uid") String uid,@Param ("rid") String rid);

}
