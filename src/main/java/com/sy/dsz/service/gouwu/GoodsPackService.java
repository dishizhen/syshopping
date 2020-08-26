package com.sy.dsz.service.gouwu;

import com.github.pagehelper.PageInfo;
import com.sy.dsz.model.gouwu.GoodsPack;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 15:52
 * @Description:
 */
public interface GoodsPackService {


    PageInfo<GoodsPack> findAll(int pageNum,int pageSize)throws Exception;

    GoodsPack findById(int id)throws Exception;

    int add(GoodsPack goodsPack)throws Exception;

    int modify(GoodsPack goodsPack)throws Exception;

    int deleteById(int id)throws Exception;

}
