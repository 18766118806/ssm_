package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/2 21:17
 **/
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
  @RolesAllowed ("ADMIN")
   // @PermitAll()
    @RequestMapping("/findOrdersByPage.do")
    public ModelAndView findOrdersByPage(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size){
        ModelAndView mv = new ModelAndView ();
        List<Orders> ordersList = ordersService.findAll (page,size);
        PageInfo pageInfo = new PageInfo (ordersList);
        mv.addObject ("ordersList",pageInfo);
        mv.setViewName ("orders-list");
        return mv;
    }

    @RequestMapping("/ordersShowDetails.do")
    public ModelAndView ordersShowDetails(@RequestParam(name = "id",required = true)String id){
        ModelAndView mv = new ModelAndView ();
        Orders ordersById = ordersService.findOrdersById (id);
        mv.addObject ("orders",ordersById);
        mv.setViewName ("orders-show");
        return mv;
    }
}
