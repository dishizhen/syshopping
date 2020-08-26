package com.sy.dsz.controller.gouwu;

import com.github.pagehelper.PageInfo;
import com.sy.common.model.BaseResult;
import com.sy.dsz.model.gouwu.GoodsInfo;
import com.sy.dsz.model.gouwu.OrdersInfo;
import com.sy.dsz.service.gouwu.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 15:06
 * @Description:
 */
@RestController
@RequestMapping("/goodsInfo")
public class GoodsInfoController {
    @Autowired
    private GoodsInfoService goodsInfoService;


    BaseResult baseResult = new BaseResult();


    /**
     * 根据id查商品
     * @return
     */
    @RequestMapping("/findById.do")
    public BaseResult  findById(int id)throws Exception{
        GoodsInfo goodsInfo = goodsInfoService.findById(id);

        if (goodsInfo!=null){
            baseResult.setData(goodsInfo);
            return baseResult;
        }
        return null;
    }


    /**
     * 查询所有的商品信息
     * @return
     */
    @RequestMapping("/findAllByPage.do")
    public BaseResult  findAllByPage(int pageNum,int pageSize)throws Exception{
        PageInfo<GoodsInfo> pageInfo = goodsInfoService.findAll(pageNum, pageSize);
        List<GoodsInfo> all = pageInfo.getList();
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
        int i = goodsInfoService.deleteById(id);
        if (i>0){
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("商品删除成功");
            return baseResult;
        }else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("商品删除失败");
            return baseResult;
        }
    }


    /**
     * 添加商品
     * @param goodsInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/add.do")
    public BaseResult add(GoodsInfo goodsInfo)throws Exception{
        int add = goodsInfoService.add(goodsInfo);
        if (add>0){
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("商品增加成功");
            return baseResult;
        }else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("商品增加失败");
            return baseResult;
        }
    }

    /**
     * 修改商品
     * @param goodsInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/modify.do")
    public BaseResult modify(GoodsInfo goodsInfo)throws Exception{
        int modify = goodsInfoService.modify(goodsInfo);
        if (modify>0){
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("商品修改成功");
            return baseResult;
        }else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("商品修改失败");
            return baseResult;
        }
    }
}
