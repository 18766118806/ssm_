package com.itheima.controller;

import com.itheima.service.impl.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/8 19:57
 **/
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;
    public ModelAndView findAll(){
        ModelAndView mv= new ModelAndView ();


        mv.setViewName ("syslog-lisit");
        return mv;
    }

}
