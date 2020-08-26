package com.sy.dsz.service.gouwu;

import com.github.pagehelper.PageInfo;
import com.sy.dsz.model.gouwu.GoodsInfo;
import com.sy.dsz.model.gouwu.ShopCart;



/**
 * @Author: curtain
 * @Date: 2020/8/25 17:05
 * @Description:
 */
public interface ShopCartService {
    ShopCart findById(int id)throws Exception;
    PageInfo<ShopCart> findAll(int pageNum,int pageSize)throws Exception;
    int add(ShopCart shopCart)throws Exception;
    int modify(ShopCart shopCart)throws Exception;
    int deleteById(int id)throws Exception;

}
