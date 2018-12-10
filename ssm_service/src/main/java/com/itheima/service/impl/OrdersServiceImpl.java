package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrdersDao;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/2 21:15
 **/
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) {
        PageHelper.startPage (page, size);
        return ordersDao.findAll ();
    }

    @Override
    public Orders findOrdersById(String oid) {
        return ordersDao.findOrdersById (oid);
    }
}
