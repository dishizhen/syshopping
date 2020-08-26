package com.sy.zd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.zd.mapper.RoleMapper;
import com.sy.zd.model.Role;
import com.sy.zd.service.RoleService;
import com.sy.zd.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper mapper;

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer save(Role role) throws Exception {

        return mapper.insert(role);
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer removeById(Integer id) throws Exception {
        return mapper.deleteByPrimaryKey(id);
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer modify(Role role) throws Exception {
        return mapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public PageInfo findByPage() throws Exception {
        PageHelper.startPage(1, 10);
        Example example = new Example(Role.class);
        example.createCriteria().andCondition("isStart=", Constant.ROLE_TYPE_VALID);
        List<Role> roles = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(roles);
        return pageInfo;
    }

    @Override
    public Role findById(Integer id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }
}
