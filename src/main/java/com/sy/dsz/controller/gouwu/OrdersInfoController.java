package com.sy.dsz.controller.gouwu;

import com.github.pagehelper.PageInfo;
import com.sy.common.model.BaseResult;
import com.sy.dsz.model.gouwu.OrdersInfo;
import com.sy.dsz.service.gouwu.OrdersInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 12:15
 * @Description:
 */
@RestController
@RequestMapping("/ordersInfo")
public class OrdersInfoController {

    @Autowired
    private OrdersInfoService ordersInfoService;

    BaseResult baseResult = new BaseResult();

    /**
     * 查询所有的商品信息
     * @return
     */
    @RequestMapping("/findAllByPage.do")
    public BaseResult  findAllByPage(int pageNum,int pageSize)throws Exception{
        PageInfo<OrdersInfo> pageInfo = ordersInfoService.findAllByPage(pageNum, pageSize);
        List<OrdersInfo> all = pageInfo.getList();
        if (all!=null&&all.size()>0){
            baseResult.setCount(pageInfo.getPageNum());
           baseResult.setData(all);
           return baseResult;
        }
        return null;
    }


    /**
     * 按照id删除商品信息
     * @return
     */
    @RequestMapping("/deleteById.do")
    public BaseResult deleteById(int id)throws Exception{
        int i = ordersInfoService.deleteById(id);
        if (i>0){
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("删除成功");
            return baseResult;
        }else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("删除失败");
            return baseResult;
        }
    }


    /**
     * 添加商品
     * @param ordersInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/add.do")
    public BaseResult add(OrdersInfo ordersInfo)throws Exception{
        int add = ordersInfoService.add(ordersInfo);
        if (add>0){
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("增加成功");
            return baseResult;
        }else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("增加失败");
            return baseResult;
        }
    }

    /**
     * 修改商品
     * @param ordersInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/modify.do")
    public BaseResult modify(OrdersInfo ordersInfo)throws Exception{
        int modify = ordersInfoService.modify(ordersInfo);
        if (modify>0){
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("修改成功");
            return baseResult;
        }else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("修改失败");
            return baseResult;
        }
    }

}
