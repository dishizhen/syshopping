package com.sy.dsz.service.zixun.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.common.model.BaseResult;
import com.sy.dsz.dao.zixun.AfficheMapper;
import com.sy.dsz.model.zixun.Affiche;
import com.sy.dsz.service.zixun.AfficheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/26 13:41
 * @Description:
 */
@Service
@Transactional
public class AfficheServiceImpl implements AfficheService {

    @Autowired
    private AfficheMapper mapper;


    /**
     * 分页查询所有公告
     * @param baseResult
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PageInfo findAllByPage(BaseResult baseResult) throws Exception {
        PageHelper.startPage(baseResult.getPage(),baseResult.getLimit());
        Example example = new Example(Affiche.class);
        example.orderBy("publishTime").desc();
        example.createCriteria().andGreaterThan("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        List<Affiche> affiches = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(affiches);
        return pageInfo;
    }

    /**
     * 按id查询公告
     * @param id
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Affiche findOneById(Integer id) throws Exception {
        Example example = new Example(Affiche.class);
        example.createCriteria().andCondition("id=", id);
        Affiche affiche = mapper.selectOneByExample(example);
        return affiche;
    }

    /**
     * 按标题搜索公告
     * @param affiche
     * @param baseResult
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PageInfo findByTitle(Affiche affiche, BaseResult baseResult) throws Exception {
       PageHelper.startPage(baseResult.getPage(),baseResult.getLimit());
        Example example = new Example(Affiche.class);
        example.createCriteria().andLike("title", "%" + affiche.getTitle() + "%");
        example.createCriteria().andGreaterThan("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        List<Affiche> affiches = mapper.selectByExample(example);
        return PageInfo.of(affiches);
    }


    /**
     * 分页查询所有公告
     * @param  baseResult
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PageInfo findAllByPage2(BaseResult baseResult) throws Exception {
        PageHelper.startPage(baseResult.getPage(), baseResult.getLimit());
        Example example = new Example(Affiche.class);
        example.orderBy("publishTime").desc();
        List<Affiche> affiches = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(affiches);
        return PageInfo.of(affiches);
    }

    /**
     * 添加公告
     * @param affiche
     * @return
     * @throws Exception
     */
    @Override
    public Integer addAffiche(Affiche affiche) throws Exception {
        return mapper.insert(affiche);
    }

    /**
     * 删除公告
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Integer removeAffiche(Integer id) throws Exception {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改公告
     * @param affiche
     * @return
     * @throws Exception
     */
    @Override
    public Integer modifyAffiche(Affiche affiche) throws Exception {
        return mapper.updateByPrimaryKey(affiche);
    }
}
