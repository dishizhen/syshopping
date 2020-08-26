package com.sy.dsz.dao.zixun;


import com.sy.dsz.model.zixun.Affiche;

import tk.mybatis.mapper.common.Mapper;



/**
 * @Author: curtain
 * @Date: 2020/8/26 13:38
 * @Description:
 */
public interface AfficheMapper extends Mapper<Affiche> {

   /* @Select("select * from affiche")
    List<Affiche> findAll() throws Exception;
    @Select("select * from affiche")
    List<Affiche> findAll2() throws Exception;
    @Select("select * from affiche where id=#{id}")
    Affiche findOneById(Integer id) throws Exception;
    @Select("select * from affiche where title like concat('%',#{title},'%') ")
    List<Affiche> findByTitle(Affiche affiche) throws Exception;

    @Insert("insert into affiche (title,content,publisher,publishTime,startTime,endTime)" +
            "values(#{title},#{content},#{publisher},#{publishTime},#{startTime},#{endTime})")
    Integer addAffiche(Affiche affiche) throws Exception;
    @Delete("delete from affiche where id=#{id}")
    Integer removeAffiche(Integer id) throws Exception;

    @Update("update affiche set title=#{title},content=#{content},publisher=#{publisher}" +
            ",publishTime=#{publishTime},startTime=#{startTime},endTime=#{endTime} where id=#{id} ")
    Integer modifyAffiche(Affiche affiche) throws Exception;*/


}
