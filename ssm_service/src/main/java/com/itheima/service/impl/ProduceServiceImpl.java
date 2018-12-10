package com.itheima.service.impl;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/2 11:29
 **/
@Service
@Transactional
public class ProduceServiceImpl implements ProduceService{
    @Autowired
    private ProductDao produceDao;
    @Override
    public List<Product> findAll() {
        return produceDao.findAll ();
    }

    @Override
    public void save(Product product) {
        produceDao.save (product);
    }



}
