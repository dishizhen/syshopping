package com.sy.dsz.model.daili;


import com.sy.zd.model.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


public interface AgentManagerMapper extends Mapper<User> {

    @Select(value = "select id from au_role where roleCode=#{roleCode}")
    Integer findRoleIdByRoleCode(String roleCode) throws Exception;

}
