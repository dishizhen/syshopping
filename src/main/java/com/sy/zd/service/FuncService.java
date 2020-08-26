package com.sy.zd.service;

import com.github.pagehelper.PageInfo;
import com.sy.zd.model.Func;
import com.sy.zd.model.User;

import java.util.List;

public interface FuncService {

    Integer save(Func func, User user) throws Exception;

    Integer removeById(Integer id) throws Exception;

    Integer modify(Func func) throws Exception;

    PageInfo findByPage() throws Exception;

    Func findById(Integer id) throws Exception;

    /**
     * 按roleId查询所有func
     * @param id
     * @return
     * @throws Exception
     */
    List<Func> findByRoleId(Integer id) throws Exception;

    /**
     * 根据角色删除权限
     * @param roleId
     * @return
     * @throws Exception
     */
    Integer removeFuncByRoleId(Integer roleId) throws Exception;
    /**
     * 根所角色增加权限
     * @param roleId
     * @param funcIds
     * @return
     * @throws Exception
     */
    Integer saveFuncByRoleId(
            Integer roleId,
            Integer[] funcIds,
            String createdBy) throws Exception;


}
