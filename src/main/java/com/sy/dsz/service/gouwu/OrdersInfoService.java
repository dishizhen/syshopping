package com.sy.dsz.service.gouwu;

import com.github.pagehelper.PageInfo;
import com.sy.dsz.model.gouwu.OrdersInfo;

/**
 * @Author: curtain
 * @Date: 2020/8/25 12:14
 * @Description:
 */

public interface OrdersInfoService {

    PageInfo<OrdersInfo> findAllByPage(int pageNum,int pageSize)throws Exception;

    int deleteById(int id)throws Exception;

    int add(OrdersInfo ordersInfo)throws Exception;


    int modify(OrdersInfo ordersInfo)throws Exception;
}
