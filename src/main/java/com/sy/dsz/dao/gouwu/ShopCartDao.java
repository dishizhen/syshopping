package com.sy.dsz.dao.gouwu;

import com.sy.dsz.model.gouwu.GoodsInfo;
import com.sy.dsz.model.gouwu.ShopCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 09:09
 * @Description:
 */

public interface ShopCartDao {
    @Select("select * from shopcart where id=#{id}")
    ShopCart findById(int id);

    @Select("select * from shopcart")
    List<ShopCart> findAll();

    @Insert("insert into shopcart(userId,totalPrice,createBy,lastUpdateTime,createTime)values" +
            "(#{userId},#{totalPrice},#{createBy},#{lastUpdateTime},#{createTime})")
    int add(ShopCart shopCart);

    @Update("update shopcart set userId=#{userId},totalPrice=#{totalPrice},createBy=#{createBy}," +
            "lastUpdateTime=#{lastUpdateTime},createTime=#{createTime} where id=#{id}")
    int modify(ShopCart shopCart);

    @Delete("delete from shopcart where id=#{id}")
    int deleteById(int id);
}
