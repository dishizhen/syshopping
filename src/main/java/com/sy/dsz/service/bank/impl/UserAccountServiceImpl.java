package com.sy.dsz.service.bank.impl;

import com.sy.dsz.dao.bank.UserAccountMapper;

import com.sy.dsz.model.bank.UserAccount;
import com.sy.dsz.service.bank.UserAccountService;
import com.sy.zd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountMapper mapper;

    /**
     * 添加账户
     * @param userAccount
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Integer addUserAccount(UserAccount userAccount) throws Exception {
        return mapper.insert(userAccount);
    }

    /**
     * 根据userId查询账户
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public UserAccount findByUserId(Integer id) throws Exception {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("id=", id);
        UserAccount userAccount = mapper.selectOneByExample(example);
        return userAccount;
    }

    /**
     * 查看销售数量
     * @param userAccountId
     * @param type
     * @param adminAccountId
     * @return
     * @throws Exception
     */
    @Override
    public Integer findUserSell(Integer userAccountId, Integer type, Integer adminAccountId) throws Exception {
        Integer integer = mapper.selectUserSell(userAccountId, type, adminAccountId);
        return integer;
    }

    /**
     * 更新账户
     * @param balance
     * @param lastUpdateTime
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Integer modifyUserAccount(Double balance, Date lastUpdateTime, Integer id) throws Exception {
        return mapper.updateUserAccount(balance, lastUpdateTime, id);
    }

    /**
     * 更该余额
     * @param balance
     * @param lastUpdateTime
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Integer modifyUserBalance(Double balance, Date lastUpdateTime, Integer userId) throws Exception {
        return mapper.updateUserBalance(balance, lastUpdateTime, userId);
    }
}
