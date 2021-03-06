package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/2 11:25
 **/
public interface ProductDao {
    @Select("select * from product")
    List<Product> findAll();

    @Select("select * from product where id =#{id}")
    Product findById(String id);

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
