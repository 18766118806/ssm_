package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/5 11:06
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**查询所有用户
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView ();
            List<UserInfo> users = userService.findAll ();
        mv.addObject ("userList", users);
        mv.setViewName ("user-list");
        return mv;
    }


    /**
     * 添加用户
     * @return ""
     */
    @RequestMapping("/save.do")
    public String save (UserInfo userInfo) {
        userService.saveUser (userInfo);
        return"redirect:/user/findAll.do";
    }

    /**
     * 用户详情
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id") String id){
        ModelAndView mv = new ModelAndView ();
       UserInfo userInfo = userService.findById (id);
        mv.addObject ("user",userInfo);
        mv.setViewName ("user-show");
        return mv;
    }

    /**
     * 查询该用户可添加的角色
     * @param uid
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole (@RequestParam(name = "id") String uid){
        ModelAndView mv= new ModelAndView ();
        //查询用户信息
        UserInfo userInfo = userService.findById (uid);
        mv.addObject ("user",userInfo);
        //查询该用户可以添加的角色
        List<Role> othersRoles = userService.findUserOthersRoles (uid);
        mv.addObject ("roleList",othersRoles);
        mv.setViewName ("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(String userId,String [] roleId){
        userService.addRoleToUser (userId,roleId);
        return "redirect:findAll.do";
    }
}
