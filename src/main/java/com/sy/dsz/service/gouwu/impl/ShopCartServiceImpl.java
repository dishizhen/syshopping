package com.sy.dsz.service.gouwu.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.dsz.dao.gouwu.ShopCartDao;
import com.sy.dsz.model.gouwu.ShopCart;
import com.sy.dsz.service.gouwu.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 17:07
 * @Description:
 */
@Service
@Transactional
public class ShopCartServiceImpl implements ShopCartService {
    @Autowired
    private ShopCartDao shopCartDao;


    /**
     * 根据id查询购物车
     * @param id
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public ShopCart findById(int id) throws Exception {
        return shopCartDao.findById(id);
    }

    /**
     * 查询购物车
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PageInfo<ShopCart> findAll(int pageNum,int pageSize) throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        List<ShopCart> all = shopCartDao.findAll();
        return PageInfo.of(all);
    }

    /**
     * 添加购物车
     * @param shopCart
     * @return
     * @throws Exception
     */
    @Override
    public int add(ShopCart shopCart) throws Exception {
        return shopCartDao.add(shopCart);
    }

    /**
     * 跟新购物车
     * @param shopCart
     * @return
     * @throws Exception
     */
    @Override
    public int modify(ShopCart shopCart) throws Exception {
        return shopCartDao.modify(shopCart);
    }

    /**
     * 删除购物车
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int deleteById(int id) throws Exception {
        return shopCartDao.deleteById(id);
    }
}
