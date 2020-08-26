package com.sy.dsz.service.bank;

import com.github.pagehelper.PageInfo;

import com.sy.common.model.BaseResult;
import com.sy.dsz.model.bank.AccountDetail;


public interface AccountDetailService {
    Integer addAccountDetail(AccountDetail accountDetail) throws Exception;

    PageInfo findByAccountId(Integer accountId, BaseResult baseResult) throws Exception;

    PageInfo findUserWithdraw(Integer accountId, BaseResult baseResult) throws Exception;

}
