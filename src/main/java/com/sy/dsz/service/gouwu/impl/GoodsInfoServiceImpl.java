package com.sy.dsz.service.gouwu.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.dsz.dao.gouwu.GoodsInfoDao;
import com.sy.dsz.model.gouwu.GoodsInfo;
import com.sy.dsz.service.gouwu.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 14:45
 * @Description:
 */
@Service
@Transactional
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Autowired
    private GoodsInfoDao goodsInfoDao;

    /**
     * 根据id查商品
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public GoodsInfo findById(int id) {
        return goodsInfoDao.findById(id);
    }

    /**
     *查询所有的商品信息
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PageInfo<GoodsInfo> findAll(int pageNum,int pageSize)throws Exception{
        PageHelper.startPage(pageNum,pageSize);
        List<GoodsInfo> all = goodsInfoDao.findAll();
        return PageInfo.of(all);
    }

    /**
     * 按照id删除商品信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int deleteById(int id) throws Exception {

        return goodsInfoDao.deleteById(id);
    }

    /**
     * 增加商品
     * @param goodsInfo
     * @return
     * @throws Exception
     */
    @Override
    public int add(GoodsInfo goodsInfo) throws Exception {
        return goodsInfoDao.add(goodsInfo);
    }

    /**
     * 修改商品
     * @param goodsInfo
     * @return
     * @throws Exception
     */
    @Override
    public int modify(GoodsInfo goodsInfo) throws Exception {
        return goodsInfoDao.modify(goodsInfo);
    }
}
