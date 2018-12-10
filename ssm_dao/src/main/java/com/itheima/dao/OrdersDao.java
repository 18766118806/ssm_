package com.itheima.dao;

import com.itheima.domain.Member;
import com.itheima.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/2 20:59
 **/
public interface OrdersDao {
    @Select("select * from orders")
    @Results({@Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", one = @One(select = "com.itheima.dao.ProductDao.findById"))})
    List<Orders> findAll();


    @Select("select * from orders where id=#{oid}")
    @Results({@Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", one = @One(select = "com.itheima.dao.ProductDao.findById")),
            @Result(column ="id" , property = "member", javaType = Member.class,one = @One(select = "com.itheima.dao.MemberDao.findById")),
            @Result(column ="id" , property = "travellers", javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.TravellerDao.findByOid"))
    })
    Orders findOrdersById(String oid);
}
