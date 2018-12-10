package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/2 13:08
 **/
@RequestMapping("/product")
@Controller
public class ProduceConrroller {
    @Autowired
    private ProduceService produceService;

    @RolesAllowed ("ADMIN")
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView ();
        List<Product> products = produceService.findAll ();
        mv.addObject ("productList", products);
        mv.setViewName ("product-list");
        return mv;
    }

    @RequestMapping("/saveProduce")
    public String saveProduce(Product product) {
        produceService.save (product);
        return "redirect:findAll.do";
    }
}
