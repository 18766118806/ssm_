package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/5 18:57
 **/
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限
     * @return
     */
    @RequestMapping("findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView ();
        List<Permission> permissionList = permissionService.findAll ();
        mv.addObject ("permissionList",permissionList);
        mv.setViewName ("permission-list");
        return mv;
    }

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save (permission);
        return "redirect:/permission/findAll.do";
    }


    /**
     * 权限详情
     * @param pid
     * @return
     */
    @RequestMapping("findById.do")
    public ModelAndView findAllById(@RequestParam(name = "id") String pid) {
        ModelAndView mv = new ModelAndView ();
        Permission permission = permissionService.findById (pid);
        mv.addObject ("permission",permission);
        mv.setViewName ("permission-show");
        return mv;
    }
}
