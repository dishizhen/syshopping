package com.sy.dsz.service.gouwu;

import com.github.pagehelper.PageInfo;
import com.sy.dsz.model.gouwu.GoodsInfo;


/**
 * @Author: curtain
 * @Date: 2020/8/25 14:45
 * @Description:
 */
public interface GoodsInfoService {
    GoodsInfo findById(int id);

    PageInfo<GoodsInfo> findAll(int pageNum,int pageSize)throws Exception;

    int deleteById(int id)throws Exception;

    int add(GoodsInfo goodsInfo)throws Exception ;

    int modify(GoodsInfo goodsInfo)throws Exception;
}
