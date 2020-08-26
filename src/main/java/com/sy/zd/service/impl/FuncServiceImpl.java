package com.sy.zd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.zd.mapper.FuncMapper;
import com.sy.zd.model.Func;
import com.sy.zd.model.User;
import com.sy.zd.service.FuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FuncServiceImpl implements FuncService {

    @Autowired
    private FuncMapper mapper;

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer save(Func func, User admin) throws Exception {
        int insert = mapper.insert(func);
        //授权给管理员
        Integer integer = mapper.insertFuncByRoleId(admin.getRoleId(), func.getId(), new Date(), admin.getUsername());

        return insert+integer;
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer removeById(Integer id) throws Exception {
        return mapper.deleteByPrimaryKey(id);
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer modify(Func func) throws Exception {
        return mapper.updateByPrimaryKeySelective(func);
    }

    @Override
    public PageInfo findByPage() throws Exception {
        PageHelper.startPage(1, 50);
        Example example = new Example(Func.class);
        example.setOrderByClause("id asc");
        List<Func> funcs = mapper.selectByExample(example);
        PageInfo info = new PageInfo(funcs);
        return info;
    }

    @Override
    public Func findById(Integer id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Func> findByRoleId(Integer id) throws Exception {
        return mapper.selectByRoleId(id);
    }



    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer removeFuncByRoleId(Integer roleId) throws Exception {
        return mapper.deleteFuncByRoleId(roleId);
    }
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer saveFuncByRoleId(Integer roleId, Integer[] funcIds, String createdBy) throws Exception {
        Integer i = 0;
        if(funcIds==null || funcIds.length==0){
            return i;
        }
        for(Integer funcId:funcIds){
            i = mapper.insertFuncByRoleId(roleId, funcId, new Date(), createdBy);
        }
        return i;
    }


}
