package com.itheima.service;

import com.itheima.domain.Orders;
import org.springframework.core.annotation.Order;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/2 21:13
 **/
public interface OrdersService {
    List<Orders> findAll(int page,int size);

    Orders findOrdersById(String oid);
}
