package com.sy.dsz.dao.bank;

import com.sy.dsz.model.bank.UserAccount;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @Author: curtain
 * @Date: 2020/8/25 19:41
 * @Description:
 */
public interface UserAccountMapper extends Mapper<UserAccount> {
/*

    List<UserAccount> findAll()throws Exception;


    List<UserAccount> findAllByDate(Date startDate,Date endDate)throws Exception;


    @Insert("insert into user_account (userId,balance,createTime,lastUpdateTime," +
            "createBy,status)values" +
            "(#{userId},#{balance},#{createTime},#{lastUpdateTime},#{createBy},#{status})" )
    int add(UserAccount userAccount)throws Exception;


    @Update("update user_account set userId=#{userId}, balance=#{balance},createTime=#{createTime}" +
            ",lastUpdateTime=#{lastUpdateTime}" +
            "createBy=#{createBy},status=#{status}")
    int modify(UserAccount userAccount)throws Exception;

    @Select("select * from user_account where userId=#{userId}")
    UserAccount findByUserId(int userId) throws Exception;

    @Select(value = "select sum(moneyOut) from account_detail where accountId=#{userAccountId} and type=#{type} and otherAcountId=#{adminAccountId} ")
    Integer findUserSell(@Param("userAccountId") Integer userAccountId,@Param("type") Integer type,@Param("adminAccountId") Integer adminAccountId) throws Exception;

    @Update("update user_account set balance=#{balance}+balance ,lastUpdateTime=#{lastUpdateTime} where userId=#{toUserId}")
    Integer modifyUserAccountIn(@Param("balance")Double balance,@Param("lastUpdateTime")  Date lastUpdateTime,@Param("toUserId")  Integer toUserId) throws Exception;


    @Update("update user_account set balance=balance-#{balance} ,lastUpdateTime=#{lastUpdateTime} where userId=#{userId}")
    Integer modifyUserAccountOut(@Param("balance")Double balance,@Param("lastUpdateTime") Date lastUpdateTime,@Param("userId") Integer userId) throws Exception;
*/


    @Select(value = "select sum(moneyOut) from account_detail where accountId=#{userAccountId} and type=#{type} and otherAccountId=#{adminAccountId} ")
    Integer selectUserSell(@Param("userAccountId") Integer userAccountId, @Param("type") Integer type, @Param("adminAccountId") Integer adminAccountId) throws Exception;

    @Update(value = "update user_account set balance=#{balance}+balance , lastUpdateTime=#{lastUpdateTime} where id=#{id}")
    Integer updateUserAccount(@Param("balance") Double balance, @Param("lastUpdateTime") Date lastUpdateTime, @Param("id") Integer id) throws Exception;

    @Update(value = "update user_account set balance=balance-#{balance} ,lastUpdateTime=#{lastUpdateTime} where userId=#{userId}")
    Integer updateUserBalance(@Param("balance") Double balance, @Param("lastUpdateTime") Date lastUpdateTime, @Param("userId") Integer userId) throws Exception;



}
