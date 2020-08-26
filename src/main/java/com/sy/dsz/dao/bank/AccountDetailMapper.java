package com.sy.dsz.dao.bank;

import com.sy.dsz.model.bank.AccountDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 19:15
 * @Description:
 */

public interface AccountDetailMapper extends Mapper<AccountDetail> {


   /* @Insert("insert into account_detail (accountId,accountDate,moneyIn,moneyOut," +
            "type,otherAccountId)values" +
            "(#{accountId},#{accountDate},#{moneyIn},#{moneyOut},#{type}," +
            "#{otherAccountId})")
    int add(AccountDetail account);

    @Update("update account_detail set accountId=#{accountId}, accountDate=#{accountDate}," +
            "moneyIn=#{moneyIn},moneyOut=#{moneyOut},type=#{type},otherAccountId=#{otherAccountId}")
    int modify(AccountDetail account);

    @Update("update account_detail set moneyOut=#{money} where accountId=#{fromId} ")
    int out(Integer fromId, Double money);

    @Update("update account_detail set moneyIn=#{money} where accountId=#{toId} ")
    int in(Integer toId, Double money);


    @Select("select * from account_detail where accountId=#{accountId}")
    List<AccountDetail> findByAccountId(Integer accountId);

    @Select("select * from account_detail where accountId=#{accountId}")
    List<AccountDetail> findUserWithdraw(Integer accountId) throws Exception;*/



}
