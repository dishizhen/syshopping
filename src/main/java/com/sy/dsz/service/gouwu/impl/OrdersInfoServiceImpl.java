package com.sy.dsz.service.gouwu.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.dsz.dao.gouwu.OrdersInfoDao;
import com.sy.dsz.model.gouwu.OrdersInfo;
import com.sy.dsz.service.gouwu.OrdersInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 12:14
 * @Description:
 */

@Service
@Transactional
public class OrdersInfoServiceImpl implements OrdersInfoService {

    @Autowired
    private OrdersInfoDao ordersInfoDao;

    /**
     * 查询所有的商品信息
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public PageInfo<OrdersInfo> findAllByPage(int pageNum,int pageSize) throws Exception {
        PageHelper.startPage(pageNum,pageSize);
        List<OrdersInfo> all = ordersInfoDao.findAll();
        return PageInfo.of(all);
    }

    /**
     * 按照id删除商品信息
     * @param id
     * @return
     */
    @Override
    public int deleteById(int id) throws Exception{
        return ordersInfoDao.deleteById(id);
    }

    /**
     * 增加商品
     * @param ordersInfo
     * @return
     * @throws Exception
     */
    @Override
    public int add(OrdersInfo ordersInfo) throws Exception {
        return ordersInfoDao.add(ordersInfo);
    }

    /**
     * 修改商品
     * @param ordersInfo
     * @return
     * @throws Exception
     */
    @Override
    public int modify(OrdersInfo ordersInfo) throws Exception {
        return ordersInfoDao.modify(ordersInfo);
    }
}
