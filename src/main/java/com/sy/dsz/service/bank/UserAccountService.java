package com.sy.dsz.service.bank;

import com.sy.dsz.model.bank.UserAccount;

import java.util.Date;

public interface UserAccountService {
    Integer addUserAccount(UserAccount userAccount) throws Exception;

    UserAccount findByUserId(Integer id) throws Exception;

    Integer findUserSell(Integer userAccountId, Integer type, Integer adminAccountId) throws Exception;

    Integer modifyUserAccount(Double balance, Date lastUpdateTime, Integer id) throws Exception;

    Integer modifyUserBalance(Double balance, Date lastUpdateTime, Integer userId) throws Exception;

}
