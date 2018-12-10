package com.itheima.service;

import com.itheima.domain.Product;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/2 11:28
 **/
public interface ProduceService {
    List<Product> findAll();

    void save(Product product);
}
