package com.sy.dsz.service.gouwu.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.dsz.dao.gouwu.GoodsPackDao;
import com.sy.dsz.model.gouwu.GoodsPack;
import com.sy.dsz.service.gouwu.GoodsPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 15:53
 * @Description:
 */
@Service
@Transactional
public class GoodsPackServiceImpl implements GoodsPackService {

    @Autowired
    private GoodsPackDao goodsPackDao;

    /**
     * 查询所有商品套餐
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PageInfo<GoodsPack> findAll(int pageNum,int pageSize) throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        List<GoodsPack> all = goodsPackDao.findAll();
        PageInfo<GoodsPack> of = PageInfo.of(all);
        return of;
    }


    /**
     * 根据id查询套餐
     * @return
     */
    @Override
    public GoodsPack findById(int id) {
        GoodsPack goodsPack = goodsPackDao.findById(id);
        return goodsPack;
    }

    /**
     * 添加商品套餐
     * @param goodsPack
     * @return
     */
    @Override
    public int add(GoodsPack goodsPack) {
        return goodsPackDao.add(goodsPack);
    }

    /**
     * 更改商品套餐
     * @param goodsPack
     * @return
     */
    @Override
    public int modify(GoodsPack goodsPack) {
        return goodsPackDao.modify(goodsPack);
    }

    /**
     * 删除商品套餐
     * @param id
     * @return
     */
    @Override
    public int deleteById(int id) {
        return goodsPackDao.deleteById(id);
    }
}
