package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import com.itheima.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/*
 * @Author:  _Xu
 * @Create: 2018/12/5 18:23
 **/

@Controller
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService = new RoleServiceImpl ();


    /**
     * 查询所有角色
     *
     * @return
     */

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView ();
        List<Role> roles = roleService.findAll ();
        mv.addObject ("roleList", roles);
        mv.setViewName ("role-list");
        return mv;
    }

    /**
     * 添加角色
     *
     * @return
     */
    @Secured("ROLE_ADMIN")
    //  @RolesAllowed ("ADMIN")
    @RequestMapping("/save.do")
    private String save(Role role) {
        roleService.saveRole (role);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView ();
        mv.addObject ("role", roleService.findById (id));
        mv.setViewName ("role-show");
        return mv;
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String rid) {
        ModelAndView mv = new ModelAndView ();
        //根据角色id查询角色详情
        Role role = roleService.findById (rid);
        mv.addObject ("role", role);
        //根据角色id查询该角色可以添加的权限
        List<Permission> rolsOthersPermission = roleService.findRolesOthersPermission (rid);
        mv.addObject ("permissionList", rolsOthersPermission);
        mv.setViewName ("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    private String addPermissionToRole(@RequestParam(name = "id", required = true) String rid, String[] pids) {
        roleService.addPermissionToRole (rid, pids);
        return "redirect:findAll.do";
    }
}
