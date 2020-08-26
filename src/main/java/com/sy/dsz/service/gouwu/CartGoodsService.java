package com.sy.dsz.service.gouwu;

import com.github.pagehelper.PageInfo;
import com.sy.dsz.model.gouwu.CartGoods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 18:32
 * @Description:
 */
public interface CartGoodsService {

    PageInfo<CartGoods> findAll(int pageNum,int pageSize)throws Exception;

    List<CartGoods> findByCartId(int cartId)throws Exception;

    int deleteById(int id)throws Exception;

    int add(CartGoods cartGoods)throws Exception;

   int modify(CartGoods cartGoods)throws Exception;
}
