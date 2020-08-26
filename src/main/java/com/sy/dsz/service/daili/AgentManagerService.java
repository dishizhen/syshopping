package com.sy.dsz.service.daili;

import com.github.pagehelper.PageInfo;
import com.sy.common.model.BaseResult;
import com.sy.zd.model.User;


public interface AgentManagerService {
    PageInfo findUserByReferId(User user, String roleCode, BaseResult baseResult) throws Exception;

    PageInfo findAgentManagerBySomeCondition(User currentLoginUser, User searcherUser, String roleCode, BaseResult baseResult) throws Exception;

}
