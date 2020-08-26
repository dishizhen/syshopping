package com.sy.dsz.service.daili.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.sy.common.model.BaseResult;
import com.sy.dsz.model.daili.AgentManagerMapper;
import com.sy.dsz.service.daili.AgentManagerService;
import com.sy.zd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AgentManagerServiceImpl implements AgentManagerService {

    @Autowired
    private AgentManagerMapper mapper;

    @Override
    public PageInfo findUserByReferId(User user, String roleCode, BaseResult baseResult) throws Exception {

        PageHelper.startPage(baseResult.getPage(), baseResult.getLimit());
        Integer roleIdByRoleCode = mapper.findRoleIdByRoleCode(roleCode);
        List list = new ArrayList();
        list.add(2);
        list.add(3);
        Example example = new Example(User.class);
        example.createCriteria().andCondition("referId=", user.getId())
                .andNotEqualTo("roleId", roleIdByRoleCode)
                .andNotEqualTo("id", user.getId())
                .andIn("isStart", list);
        List<User> users = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(users);
        return pageInfo;
    }

    @Override
    public PageInfo findAgentManagerBySomeCondition(User currentLoginUser, User searcherUser, String roleCode, BaseResult baseResult) throws Exception {
        PageHelper.startPage(baseResult.getPage(), baseResult.getLimit());
        Example example = new Example(User.class);
        List list = new ArrayList();
        list.add(2);
        list.add(3);
        Integer roleIdByRoleCode = mapper.findRoleIdByRoleCode(roleCode);
        example.createCriteria().andCondition("referId=", currentLoginUser.getId())
                .andNotEqualTo("roleId", roleIdByRoleCode)
                .andNotEqualTo("id", currentLoginUser.getId())
                .andIn("isStart", list);

        if (searcherUser.getUsername() != null && !"".equals(searcherUser.getUsername())) {
            example.and(example.createCriteria().andLike("userName", "%" + searcherUser.getUsername() + "%"));
        }

        if (searcherUser.getSex() != null && !"".equals(searcherUser.getSex())) {
            example.and(example.createCriteria().andEqualTo("sex", searcherUser.getSex()));
        }

        if (searcherUser.getUserAddress() != null && !"".equals(searcherUser.getUserAddress()) && !"-".equals(searcherUser.getUserAddress())) {
            example.and(example.createCriteria().andLike("userAddress", "%" + searcherUser.getUserAddress() + "%"));
        }

        if (searcherUser.getRoleName() != null && !"".equals(searcherUser.getRoleName())) {
            example.and(example.createCriteria().andLike("roleName", "%" + searcherUser.getRoleName() + "%"));
        }
        System.out.println(example);

        List<User> users = mapper.selectByExample(example);

        PageInfo pageInfo = new PageInfo(users);
        return pageInfo;
    }
}
