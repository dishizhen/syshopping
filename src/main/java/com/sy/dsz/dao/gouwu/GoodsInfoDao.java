package com.sy.dsz.dao.gouwu;

import com.sy.dsz.model.gouwu.GoodsInfo;
import com.sy.dsz.model.gouwu.OrdersInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 09:08
 * @Description:
 */
public interface GoodsInfoDao {

    @Select("select * from goods_info")
    List<GoodsInfo> findAll();

    @Select("select * from goods_info where id=#{id}")
    GoodsInfo findById(int id);

    @Delete("delete from goods_info where id=#{id}")
    int deleteById(int id);

    @Insert("insert into goods_info (goodsSN,goodsName,goodsFormat,marketPrice," +
            "realPrice,state,note,num,unit,createTime,lastUpdateTime,createdBy)values" +
            "(#{goodsSN},#{goodsName},#{goodsFormat},#{marketPrice},#{realPrice}," +
            "#{state},#{note},#{num},#{unit},#{createTime},#{lastUpdateTime},#{createdBy})")
    int add(GoodsInfo goodsInfo);

    @Update("update goods_info set goodsSN=#{goodsSN}, marketPrice=#{marketPrice},realPrice=#{realPrice},state=#{state},note=#{note}," +
            "num=#{num},unit=#{unit},createTime=#{createTime},lastUpdateTime=#{lastUpdateTime},createdBy=#{createdBy}" +
            " where id=#{id}")
    int modify(GoodsInfo goodsInfo);

}
