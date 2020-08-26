package com.sy.dsz.dao.gouwu;

import com.sy.dsz.model.gouwu.GoodsPack;
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
public interface GoodsPackDao {

    @Select("select * from goods_pack ")
    List<GoodsPack> findAll();

    @Select("select * from goods_pack where id=#{id}")
    GoodsPack findById(int id);

    @Insert("insert into goods_pack(goodsPackName,goodsPackCode,typeId," +
            "typeName,totalPrice,state,note,num,createdBy,createTime," +
            "lastUpdateTime)values(#{goodsPackName},#{goodsPackCode}," +
            "#{typeId},#{typeName},#{totalPrice},#{state},#{note},#{num}" +
            ",#{createdBy},#{createTime},#{lastUpdateTime})")
    int add(GoodsPack goodsPack);

    @Update("update goods_pack set goodsPackName=#{goodsPackName},goodsPackCode=#{goodsPackCode}" +
            ",typeId=#{typeId},typeName=#{typeName},totalPrice=#{totalPrice}," +
            "state=#{state},note=#{note},num=#{num},createdBy=#{createdBy}," +
            "lastUpdateTime=#{lastUpdateTime} where id=#{id}")
    int modify(GoodsPack goodsPack);

    @Delete("delete from goods_pack where id=#{id}")
    int deleteById(int id);

}
