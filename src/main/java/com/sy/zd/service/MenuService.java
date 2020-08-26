package com.sy.zd.service;


import com.sy.zd.model.Menu;

import java.util.List;

public interface MenuService {



    /**
     * 返回客户端
     * @return
     * @throws Exception
     */
    List<Menu> findByRoleId(Integer roleId) throws Exception;


    /**
     * 根据角色修改权限
     * @param roleId
     * @param funcId
     * @return
     * @throws Exception
     */
    Integer modifyFuncByRole(Integer roleId, Integer[] funcId, String createBy) throws Exception;


}
