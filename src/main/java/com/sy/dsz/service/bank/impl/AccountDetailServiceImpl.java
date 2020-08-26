package com.sy.dsz.service.bank.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.sy.common.model.BaseResult;
import com.sy.dsz.dao.bank.AccountDetailMapper;
import com.sy.dsz.dao.bank.UserAccountMapper;
import com.sy.dsz.model.bank.AccountDetail;
import com.sy.dsz.model.bank.UserAccount;
import com.sy.dsz.service.bank.AccountDetailService;
import com.sy.zd.mapper.UserMapper;
import com.sy.zd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountDetailServiceImpl implements AccountDetailService {

    @Autowired
    private AccountDetailMapper mapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加账户信息
     * @param accountDetail
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Integer addAccountDetail(AccountDetail accountDetail) throws Exception {
        return mapper.insert(accountDetail);
    }

    /**
     * 根据accountId 查询账户
     * @param accountId
     * @param baseResult
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo findByAccountId(Integer accountId, BaseResult baseResult) throws Exception {

        PageHelper.startPage(baseResult.getPage(), baseResult.getLimit());
        Example example = new Example(AccountDetail.class);
        List list = new ArrayList();
        list.add(0);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        example.createCriteria().andCondition("accountId=", accountId).andIn("type",list);
        List<AccountDetail> accountDetails = mapper.selectByExample(example);
        if (accountDetails != null) {
            List<UserAccount> userAccounts = null;
            User user = null;
            for (int i = 0; i < accountDetails.size(); i++) {
                Example example1 = new Example(UserAccount.class);
                example1.createCriteria().andCondition("id=", accountDetails.get(i).getOtherAccountId());
                userAccounts = userAccountMapper.selectByExample(example1);
                if (userAccounts != null) {
                    for (int j = 0; j < userAccounts.size(); j++) {
                        System.out.println(userAccounts.get(j).getUserId());
                        Example example3 = new Example(User.class);
                        example3.createCriteria().andCondition("id=", userAccounts.get(j).getUserId());
                        user = userMapper.selectOneByExample(example3);
                        accountDetails.get(i).setUserName(user.getUsername());
                    }
                }
            }
        }
        PageInfo pageInfo = new PageInfo(accountDetails);
        return pageInfo;
    }

    /**
     *
     * @param accountId
     * @param baseResult
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo findUserWithdraw(Integer accountId, BaseResult baseResult) throws Exception {

        PageHelper.startPage(baseResult.getPage(), baseResult.getLimit());
        Example example = new Example(AccountDetail.class);
        example.createCriteria().andCondition("accountId=", accountId).andEqualTo("type",1);
        List<AccountDetail> accountDetails = mapper.selectByExample(example);
        if (accountDetails != null) {
            List<UserAccount> userAccounts = null;
            User user = null;
            for (int i = 0; i < accountDetails.size(); i++) {
                Example example1 = new Example(UserAccount.class);
                example1.createCriteria().andCondition("id=", accountDetails.get(i).getOtherAccountId());
                userAccounts = userAccountMapper.selectByExample(example1);
                if (userAccounts != null) {
                    for (int j = 0; j < userAccounts.size(); j++) {
                        System.out.println(userAccounts.get(j).getUserId());
                        Example example3 = new Example(User.class);
                        example3.createCriteria().andCondition("id=", userAccounts.get(j).getUserId());
                        user = userMapper.selectOneByExample(example3);
                        accountDetails.get(i).setUserName(user.getUsername());
                        System.out.println();
                    }
                }
            }
        }
        PageInfo pageInfo = new PageInfo(accountDetails);
        return pageInfo;
    }
}
