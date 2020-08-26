package com.sy.dsz.service.gouwu.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.dsz.dao.gouwu.CartGoodsDao;
import com.sy.dsz.model.gouwu.CartGoods;
import com.sy.dsz.service.gouwu.CartGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 18:33
 * @Description:
 */
@Service
@Transactional
public class CartGoodsServiceImpl implements CartGoodsService {
    @Autowired
    private CartGoodsDao cartGoodsDao;


    /**
     * 查询所有cartGoods
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PageInfo<CartGoods> findAll(int pageNum,int pageSize)throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        List<CartGoods> all = cartGoodsDao.findAll();

        return PageInfo.of(all);
    }

    /**
     * 通过购物车id查找
     * @param cartId
     * @return
     */
    @Override
    public List<CartGoods> findByCartId(int cartId)throws Exception {
        return cartGoodsDao.findByCartId(cartId);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(int id) throws Exception {
        return cartGoodsDao.deleteById(id);
    }

    /**
     * 添加cartGoods
     * @param cartGoods
     * @return
     */
    @Override
    public int add(CartGoods cartGoods)throws Exception  {
        return cartGoodsDao.add(cartGoods);
    }

    /**
     * 修改cartGoods
     * @param cartGoods
     * @return
     */
    @Override
    public int modify(CartGoods cartGoods) throws Exception {
        return cartGoodsDao.modify(cartGoods);
    }
}
