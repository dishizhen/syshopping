package com.sy.zd.controller;

import com.github.pagehelper.PageInfo;
import com.sy.zd.model.Role;
import com.sy.zd.model.base.BaseResult;
import com.sy.zd.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色管理
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;
    @RequiresPermissions("/role/page.do")
    @RequestMapping("/page.do")
    public BaseResult showRoleList() throws Exception{
        PageInfo byPage = service.findByPage();
        List<Role>list = byPage.getList();
        BaseResult baseResult = new BaseResult();
        baseResult.setData(list);
        baseResult.setCode(BaseResult.CODE_SUCCESS);
        baseResult.setMsg(BaseResult.MSG_SUCCESS);
        return baseResult;
    }
}
