package com.sy.dsz.service.zixun;

import com.github.pagehelper.PageInfo;
import com.sy.common.model.BaseResult;
import com.sy.dsz.model.zixun.LeaveMessage;


public interface LeaveMessageService {
//    PageInfo findAll(PageResp pageResp) throws Exception;

    Integer addLeaveMessage(LeaveMessage leaveMessage) throws Exception;

    Integer removeLeaveMessage(Integer id) throws Exception;

    LeaveMessage findOneById(Integer id) throws Exception;

    PageInfo findOneByCreateBy(String createBy, BaseResult baseResult) throws Exception;

    PageInfo findByTitle(BaseResult baseResult, String title) throws Exception;

    Integer modifyState(LeaveMessage leaveMessage) throws Exception;

}
