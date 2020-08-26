package com.sy.dsz.controller.gouwu;

import com.github.pagehelper.PageInfo;
import com.sy.common.model.BaseResult;
import com.sy.dsz.model.gouwu.GoodsInfo;
import com.sy.dsz.model.gouwu.GoodsPack;
import com.sy.dsz.service.gouwu.GoodsPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 15:59
 * @Description:
 */
@RestController
@RequestMapping("/goodsPack")
public class GoodsPackController {

    @Autowired
    private GoodsPackService goodsPackService;
    BaseResult baseResult = new BaseResult();

    /**
     * 查询所有商品套餐
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAll.do")
    public BaseResult findAll(int pageNum,int pageSize)throws Exception{
        PageInfo<GoodsPack> pageInfo = goodsPackService.findAll(pageNum, pageSize);
        List<GoodsPack> all = pageInfo.getList();
        if (all!=null&&all.size()>0){
            baseResult.setCount(pageInfo.getPageNum());
            baseResult.setData(all);
            return baseResult;
        }
        return null;
    }



}
