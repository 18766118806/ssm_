package com.itheima.dao;

import com.itheima.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/3 19:12
 **/
public interface TravellerDao {
    @Select("select * from traveller where id in (select travellerid from order_traveller where orderid = #{oid})")
    List<Traveller> findByOid(String oid);
}
