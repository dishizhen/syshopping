package com.sy.dsz.service.zixun.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.sy.common.model.BaseResult;
import com.sy.dsz.dao.zixun.LeaveMessageMapper;
import com.sy.dsz.model.zixun.LeaveMessage;
import com.sy.dsz.service.zixun.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LeaveMessageServiceImpl implements LeaveMessageService {

    @Autowired
    private LeaveMessageMapper mapper;

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Integer addLeaveMessage(LeaveMessage leaveMessage) throws Exception {
        return mapper.insert(leaveMessage);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Integer removeLeaveMessage(Integer id) throws Exception {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public LeaveMessage findOneById(Integer id) throws Exception {
        Example example = new Example(LeaveMessage.class);
        example.createCriteria().andCondition("id=", id);
        LeaveMessage leaveMessage = mapper.selectOneByExample(example);
        return leaveMessage;
    }

    @Override
    public PageInfo findOneByCreateBy(String createBy, BaseResult baseResult) throws Exception {

        PageHelper.startPage(baseResult.getPage(), baseResult.getLimit());
        Example example = new Example(LeaveMessage.class);
        example.createCriteria().andCondition("createBy=", createBy);
        List<LeaveMessage> leaveMessages = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(leaveMessages);
        return pageInfo;
    }

    @Override
    public PageInfo findByTitle(BaseResult baseResult, String title) throws Exception {

        PageHelper.startPage(baseResult.getPage(), baseResult.getLimit());
        Example example = new Example(LeaveMessage.class);
        example.createCriteria().andLike("messageTitle", "%" + title + "%");
        List<LeaveMessage> leaveMessages = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(leaveMessages);
        return pageInfo;
    }

    @Override
    public Integer modifyState(LeaveMessage leaveMessage) throws Exception {
        return mapper.updateByPrimaryKey(leaveMessage);
    }
}
