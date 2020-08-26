package com.sy.dsz.dao.gouwu;

import com.sy.dsz.model.gouwu.CartGoods;
import com.sy.dsz.model.gouwu.GoodsInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 18:18
 * @Description:
 */
public interface CartGoodsDao {

    @Select("select * from cart_goods")
    List<CartGoods> findAll();

    @Select("select * from cart_goods where cartid=#{cartId}")
    List<CartGoods> findByCartId(int cartId);


    @Delete("delete from cart_goods where id=#{id}")
    int deleteById(int id);

    @Insert("insert into cart_goods (goodsId,goodsName,goodsPrice,goodsNum," +
            "cartId,flag,createBy,createTime,lastUpdateTime)values" +
            "(#{goodsId},#{goodsName},#{goodsPrice},#{goodsNum},#{cartId}," +
            "#{flag},#{createBy},#{createTime},#{lastUpdateTime})")
    int add(CartGoods cartGoods);

    @Update("update cart_goods set goodsId=#{goodsId}, goodsName=#{goodsName},goodsPrice=#{goodsPrice},goodsNum=#{goodsNum}" +
            "cartId=#{cartId},flag=#{flag},createBy=#{createBy},createTime=#{createTime},lastUpdateTime=#{lastUpdateTime}")
    int modify(CartGoods cartGoods);



}
