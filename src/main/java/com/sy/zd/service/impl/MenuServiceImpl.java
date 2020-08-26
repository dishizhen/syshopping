package com.sy.zd.service.impl;

import com.github.pagehelper.PageInfo;
import com.sy.zd.model.Func;
import com.sy.zd.model.Menu;
import com.sy.zd.service.FuncService;
import com.sy.zd.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {


    @Autowired
    private FuncService funcService;


    @Override
    public List<Menu> findByRoleId(Integer roleId) throws Exception {

        //所有权限
        PageInfo byPage = funcService.findByPage();
        List<Func> funcs = byPage.getList();
        //角色拥有的权限
        List<Func> funcs2 = funcService.findByRoleId(roleId);

        //整合成List<Menu>,这里边的每个func就是单个Menu
        List<Menu> menus = new ArrayList<>();
        int i = 1;
        for(Func func:funcs){
            Menu menu = new Menu();
            int j = 0;
            j = i++;
            //父子关系
            menu.setAuthority(func.getFuncCode());
            menu.setAuthorityId(func.getId());
            menu.setAuthorityName(func.getFuncName());
            menu.setCreateTime(func.getCreationTime());
            menu.setIsMenu(1);
            menu.setMenuUrl(func.getFuncUrl());
            menu.setOrderNumber(j);
            menu.setUpdateTime(new Date());
            menu.setParentId(func.getParentId());
            //标识出选中的菜单
            for(Func checkedFunc:funcs2){
                if(func.getId().intValue()==checkedFunc.getId().intValue()){
                    menu.setChecked(true);
                    break;
                }
            }
            menus.add(menu);
        }
        return menus;

    }
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer modifyFuncByRole(Integer roleId, Integer[] funcId,String createBy) throws Exception {
        //1.先根据roleId删除权限
        Integer remove = funcService.removeFuncByRoleId(roleId);
        //2.再根据roleId增加权限
        Integer integer = funcService.saveFuncByRoleId(roleId, funcId, createBy);
        return remove+integer;
    }
}
