package com.sy.dsz.dao.gouwu;

import com.sy.dsz.model.gouwu.OrdersInfo;
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
public interface OrdersInfoDao {

    @Select("select * from order_info")
    List<OrdersInfo> findAll();


    @Delete("delete from order_info where id=#{id}")
    int deleteById(int id);

    @Insert("insert into order_info (orderCode,orderPrice,createTime,createBy,lastUpdateTime,status,userId)values(#{orderCode},#{orderPrice},#{createTime},#{createBy},#{lastUpdateTime},#{status},#{userId})")
    int add(OrdersInfo ordersInfo);

    @Update("update order_info set orderPrice=?,status=? where id=?")
    int modify(OrdersInfo ordersInfo);




}
