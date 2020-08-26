package com.sy.dsz.service.zixun.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.sy.common.model.BaseResult;
import com.sy.dsz.dao.zixun.InformationMapper;
import com.sy.dsz.model.zixun.Information;
import com.sy.dsz.service.zixun.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationMapper mapper;


    /**
     * 添加咨询
     * @param information
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Integer addInformation(Information information) throws Exception {
        return mapper.insert(information);
    }

    /**
     * 查询所有咨询
     * @param baseResult
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo findAll(BaseResult baseResult) throws Exception {

        PageHelper.startPage(baseResult.getPage(), baseResult.getLimit());
        Example example = new Example(Information.class);
        example.orderBy("publishTime").desc();
//        example.createCriteria().andNotEqualTo("state", 0);
        List<Information> information = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(information);
        return pageInfo;
    }

    /**
     * 删除咨询
     * @param information
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Integer removeInformation(Information information) throws Exception {
        return mapper.deleteByPrimaryKey(information);
    }

    /**
     * 修改咨询
     * @param information
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Integer modifyInformation(Information information) throws Exception {
        return mapper.updateByPrimaryKey(information);
    }

    /**
     * 按id查询咨询
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Information findInformationById(Integer id) throws Exception {
        Example example = new Example(Information.class);
        example.createCriteria().andCondition("id=", id);
        Information information = mapper.selectOneByExample(example);
        return information;
    }

    /**
     * 按标题查找咨询
     * @param information
     * @param baseResult
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo findByTitle(Information information, BaseResult baseResult) throws Exception {

        PageHelper.startPage(baseResult.getPage(), baseResult.getLimit());
        Example example = new Example(Information.class);
        example.createCriteria().andLike("title", "%" + information.getTitle() + "%");
        List<Information> result = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(result);
        return pageInfo;
    }

    /**
     * 按标题查找咨询
     * @param information
     * @param baseResult
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo findByTitle2(Information information, BaseResult baseResult) throws Exception {

        PageHelper.startPage(baseResult.getPage(), baseResult.getLimit());
        Example example = new Example(Information.class);
        example.createCriteria().andLike("title", "%" + information.getTitle() + "%").andNotEqualTo("state", 0);
        List<Information> result = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(result);
        return pageInfo;
    }

    /**
     * 查询所有咨询
     * @param baseResult
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo findAll2(BaseResult baseResult) throws Exception {

        PageHelper.startPage(baseResult.getPage(), baseResult.getLimit());
        Example example = new Example(Information.class);
        example.orderBy("publishTime").desc();
        example.createCriteria().andNotEqualTo("state", 0);
        List<Information> information = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(information);
        return pageInfo;

    }
}
