package com.sy.dsz.controller.bank;

import com.github.pagehelper.PageInfo;

import com.sy.dsz.model.bank.UserAccount;
import com.sy.dsz.service.bank.AccountDetailService;
import com.sy.dsz.service.bank.UserAccountService;
import com.sy.zd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;


@RestController
@RequestMapping("/userAccount")
public class UserAccountController {
   /* @Autowired
    private UserAccountService service;
    @Autowired
    private DataDictionaryService dataDictionaryService;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private AccountDetailService accountDetailService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;

    @RequestMapping("/addUserAccount.do")
    public BaseResp addUserAccount(UserAccount userAccount) throws Exception {
        Integer integer = service.addUserAccount(userAccount);
        System.out.println(userAccount.getId());
        BaseResp baseResp = new BaseResp();
        if (integer > 0) {
            baseResp.setCode(BaseResp.RET_SUCCESS);
            baseResp.setMsg("用户账户添加成功");
        } else {
            baseResp.setCode(BaseResp.RET_FAILED);
            baseResp.setMsg("用户账户添加失败");
        }
        return baseResp;
    }

    *//**
     * 购买会员页面中，查询用户账户余额的
     *
     * @param session
     * @return
     * @throws Exception
     *//*
    @RequestMapping("/findUserAccount.do")
    public BaseResp findUserAccount(HttpSession session) throws Exception {
        User user = (User) session.getAttribute("userSession");
        UserAccount byUserId = service.findByUserId(user.getId());
        byUserId.setUser(user);
        BaseResp baseResp = new BaseResp();
        if (byUserId != null) {
            baseResp.setCode(BaseResp.RET_SUCCESS);
            baseResp.setMsg("用户账户查询成功");
        } else {
            baseResp.setCode(BaseResp.RET_FAILED);
            baseResp.setMsg("用户账户查询失败");
        }
        baseResp.setData(byUserId);
        return baseResp;
    }

    *//**
     * 用户向账号中充钱
     *
     * @param userAccount
     * @param session
     * @return
     * @throws Exception
     *//*
    @RequestMapping("/modifyUserAccount.do")
    public BaseResp modifyUserAccount(UserAccount userAccount, HttpSession session) throws Exception {
        Integer integer = service.modifyUserAccount(userAccount.getBalance(), new Date(), userAccount.getId());

        User user = (User) session.getAttribute("userSession");
        UserAccount byUserId = service.findByUserId(user.getId());

        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setAccountId(byUserId.getId());
        accountDetail.setAccountDate(new Date());
        accountDetail.setMoneyIn(userAccount.getBalance());
        accountDetail.setMoneyOut(0.0);
        accountDetail.setType(0);
        accountDetail.setOtherAccountId(byUserId.getId());
        Integer integer1 = accountDetailService.addAccountDetail(accountDetail);

        BaseResp baseResp = new BaseResp();
        if (integer > 0) {
            baseResp.setCode(BaseResp.RET_SUCCESS);
            baseResp.setMsg("用户账户充值成功");
        } else {
            baseResp.setCode(BaseResp.RET_FAILED);
            baseResp.setMsg("用户账户充值失败");
        }
        return baseResp;
    }

    *//**
     * 计算销售额后购买会员时每种会员打折后的价格
     *
     * @param session
     * @return
     * @throws Exception
     *//*
    @RequestMapping("/findUserSell.do")
    public BaseResp findUserSell(HttpSession session) throws Exception {
        User userSession = (User) session.getAttribute("userSession");
        UserAccount byUserId = service.findByUserId(userSession.getId());
        BaseResp baseResp = new BaseResp();
        //当前登录用户的销售额
        Integer userSell = null;
        try {
            userSell = service.findUserSell(byUserId.getId(), 2, 1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("用户销售额为空");
            baseResp.setCode(BaseResp.RET_SUCCESS);
            baseResp.setMsg("折扣信息查询成功");
            List<DataDictionary> all = dataDictionaryService.findAll();
            List<MemberPrice> list = new ArrayList<>();
            if (all.size() > 0) {
                for (int i = 0; i < all.size(); i++) {
                    MemberPrice memberPrice = new MemberPrice();
                    DataDictionary dataDictionary = all.get(i);
                    memberPrice.setTypeCode(all.get(i).getTypeCode());
                    //查询所有的折扣信息
                    List<Discount> byMemberName = discountService.findByMemberName(dataDictionary.getTypeCode());
                    //将会员名称进行封装
                    memberPrice.setMemberType(all.get(i).getValueName());
                    if (byMemberName.size() > 0) {
                        for (int j = 0; j < byMemberName.size(); j++) {
                            memberPrice.setOriginalPrice(byMemberName.get(j).getOriginalPrice());
                        }
                    }
                    list.add(memberPrice);
                }
            }
            baseResp.setData(list);
            return baseResp;
        }

        if (userSell == null) {
            System.out.println("用户销售额为空");
            baseResp.setCode(BaseResp.RET_SUCCESS);
            baseResp.setMsg("折扣信息查询成功");
            List<DataDictionary> all = dataDictionaryService.findAll();
            List<MemberPrice> list = new ArrayList<>();
            if (all.size() > 0) {
                for (int i = 0; i < all.size(); i++) {
                    MemberPrice memberPrice = new MemberPrice();
                    DataDictionary dataDictionary = all.get(i);
                    memberPrice.setTypeCode(all.get(i).getTypeCode());
                    List<Discount> byMemberName = discountService.findByMemberName(dataDictionary.getTypeCode());
                    memberPrice.setMemberType(all.get(i).getValueName());
                    if (byMemberName.size() > 0) {
                        for (int j = 0; j < byMemberName.size(); j++) {
                            memberPrice.setOriginalPrice(byMemberName.get(j).getOriginalPrice());
                        }
                    }
                    list.add(memberPrice);
                }
            }
            baseResp.setData(list);
            return baseResp;
        }

        //从数据字典中查出来所有的会员sl_role0X
        List<DataDictionary> all = dataDictionaryService.findAll();
        System.out.println("all" + all);
        System.out.println("all.length" + all.size());
        List<MemberPrice> list = new ArrayList<>();
        if (all.size() > 0) {
            for (int i = 0; i < all.size(); i++) {
                MemberPrice memberPrice = new MemberPrice();
                Integer standard1 = 0;
                DataDictionary dataDictionary = all.get(i);
                memberPrice.setTypeCode(all.get(i).getTypeCode());
                System.out.println("dataDictionary " + dataDictionary);
                //查询所有的折扣信息
                List<Discount> byMemberName = discountService.findByMemberName(dataDictionary.getTypeCode());
                System.out.println("byMemberName " + byMemberName);
                //将会员名称进行封装
                memberPrice.setMemberType(all.get(i).getValueName());
                if (byMemberName.size() > 0) {
                    for (int j = 0; j < byMemberName.size(); j++) {
                        Integer sell_standard = byMemberName.get(j).getSell_standard();
                        memberPrice.setOriginalPrice(byMemberName.get(j).getOriginalPrice());
                        if (userSell > sell_standard) {
                            if (standard1 < sell_standard) {
                                standard1 = sell_standard;
                                memberPrice.setCurrentPrice((1 - byMemberName.get(j).getDiscount()) * byMemberName.get(j).getOriginalPrice());
                            }
                        }
                    }
                }
                list.add(memberPrice);
            }
        }
        if (list.size() > 0) {
            baseResp.setCode(BaseResp.RET_SUCCESS);
            baseResp.setMsg("折扣信息查询成功");
        } else {
            baseResp.setCode(BaseResp.RET_FAILED);
            baseResp.setMsg("折扣信息查询失败");
        }
        baseResp.setData(list);
        return baseResp;
    }

    *//**
     * 上级代理商审核下级代理商购买会员不通过
     *//*
    @RequestMapping("/checkNotPass.do")
    public BaseResp checkNotPass(Integer userId) throws Exception {
        OrderInfo byUserId = orderInfoService.findByUserId2(userId);
        byUserId.setStatus(0);
        byUserId.setLastUpdateTime(new Date());
        Integer integer = orderInfoService.modifyOrderInfo(byUserId);
        UserAccount userAccountId = userAccountService.findByUserId(userId);
        Integer modifyUserAccount = userAccountService.modifyUserAccount(byUserId.getOrderPrice(), new Date(), userAccountId.getId());
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setAccountId(userAccountId.getId());
        accountDetail.setAccountDate(new Date());
        accountDetail.setMoneyIn(byUserId.getOrderPrice());
        accountDetail.setMoneyOut(0.0);
        accountDetail.setType(4);
        accountDetail.setOtherAccountId(userId);
        Integer addAccountDetail = accountDetailService.addAccountDetail(accountDetail);

        User user = new User();
        user.setId(userId);
        User byId = userService.findById(user);
        byId.setRoleId(2);
        byId.setRoleName("注册会员");
        byId.setIsStart(2);
        Integer integer1 = userService.modifyUser(byId);


        BaseResp baseResp = new BaseResp();
        if (integer != 0 && modifyUserAccount != 0 && addAccountDetail != 0 && integer1 != 0) {
            baseResp.setCode(BaseResp.RET_SUCCESS);
            baseResp.setMsg("审核不通过，且退款成功");
        } else {
            baseResp.setCode(BaseResp.RET_FAILED);
            baseResp.setMsg("退款失败，请重新审核");
        }
        return baseResp;
    }

    *//**
     * 汇款
     *//*

    @RequestMapping("/remittanceMoney.do")
    public BaseResp remittanceMoney(UserAccount userAccount, Integer toUserId, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("userSession");
        UserAccount userAccountServiceByUserId = userAccountService.findByUserId(user.getId());
        BaseResp baseResp = new BaseResp();
        if (userAccountServiceByUserId.getBalance() > userAccount.getBalance()) {
            Integer integer = userAccountService.modifyUserBalance(userAccount.getBalance(), new Date(), user.getId());
            Integer integer1 = userAccountService.modifyUserAccount(userAccount.getBalance(), new Date(), toUserId);
            UserAccount userAccountServiceByUserId1 = userAccountService.findByUserId(toUserId);
            AccountDetail accountDetail = new AccountDetail();
            accountDetail.setAccountId(userAccountServiceByUserId.getId());
            accountDetail.setAccountDate(new Date());
            accountDetail.setMoneyIn(0.0);
            accountDetail.setMoneyOut(userAccount.getBalance());
            accountDetail.setType(5);
            accountDetail.setOtherAccountId(toUserId);
            Integer integer2 = accountDetailService.addAccountDetail(accountDetail);
            AccountDetail accountDetail2 = new AccountDetail();
            accountDetail2.setAccountId(userAccountServiceByUserId1.getId());
            accountDetail2.setAccountDate(new Date());
            accountDetail2.setMoneyIn(userAccount.getBalance());
            accountDetail2.setMoneyOut(0.0);
            accountDetail2.setType(0);
            accountDetail2.setOtherAccountId(user.getId());
            Integer integer3 = accountDetailService.addAccountDetail(accountDetail2);

            if (integer != 0 && integer1 != 0 && integer2 != 0 && integer3 != 0) {
                baseResp.setCode(BaseResp.RET_SUCCESS);
                baseResp.setMsg("汇款成功");
            } else {
                baseResp.setCode(BaseResp.RET_FAILED);
                baseResp.setMsg("汇款失败");
            }
        } else {
            baseResp.setCode(BaseResp.RET_FAILED);
            baseResp.setMsg("账户余额不足，无法汇款");
        }
        return baseResp;
    }

    *//**
     * 查询所有汇款记录
     *//*
    @RequestMapping("/findAllAccountDetail.do")
    public PageResp findAllAccountDetail(HttpSession session, PageResp pageResp) throws Exception {
        User user = (User) session.getAttribute("userSession");
        UserAccount byUserId = service.findByUserId(user.getId());
        PageInfo byAccountId = accountDetailService.findByAccountId(byUserId.getId(), pageResp);
        if (byAccountId.getList().size() > 0) {
            pageResp.setTotalPage(byAccountId.getPages());
            pageResp.setTotalCount((int) byAccountId.getTotal());
            pageResp.setCode(BaseResp.RET_SUCCESS);
            pageResp.setMsg("汇款信息查询成功");
        } else {
            pageResp.setCode(BaseResp.RET_FAILED);
            pageResp.setMsg("汇款信息查询失败");
        }
        pageResp.setData(byAccountId.getList());
        return pageResp;
    }

    *//**
     * 展示个人账户
     *//*
    @RequestMapping("/findUserAccountAndUsername.do")
    public BaseResp findUserAccountAndUsername(HttpSession session) throws Exception {
        User user = (User) session.getAttribute("userSession");
        UserAccount byUserId = service.findByUserId(user.getId());
        BaseResp baseResp = new BaseResp();
        if (byUserId != null) {
            User findUserById = new User();
            findUserById.setId(user.getId());
            User byId = userService.findById(findUserById);
            byUserId.setUserName(byId.getUserName());
            byUserId.setBankAccount(byId.getBankAccount());
            byUserId.setBankName(byId.getBankName());
            baseResp.setCode(BaseResp.RET_SUCCESS);
            baseResp.setMsg("个人账户查询成功");
        } else {
            baseResp.setCode(BaseResp.RET_FAILED);
            baseResp.setMsg("个人账户查询失败");
        }
        baseResp.setData(byUserId);
        return baseResp;
    }

    *//**
     * 提现功能
     *//*
    @RequestMapping("/withdrawMoney.do")
    public BaseResp withdrawMoney(HttpSession session, Double balance) throws Exception {
        User user = (User) session.getAttribute("userSession");
        UserAccount byUserId = service.findByUserId(user.getId());
        BaseResp baseResp = new BaseResp();
        if (byUserId.getBalance() < balance) {
            baseResp.setCode(BaseResp.RET_FAILED);
            baseResp.setMsg("账户余额不足无法提现");
            return baseResp;
        }
        Integer integer = service.modifyUserBalance(balance, new Date(), user.getId());
        if (integer != 0) {
            AccountDetail accountDetail = new AccountDetail();
            accountDetail.setAccountId(byUserId.getId());
            accountDetail.setAccountDate(new Date());
            accountDetail.setMoneyIn(0.0);
            accountDetail.setMoneyOut(balance);
            accountDetail.setType(1);
            accountDetail.setOtherAccountId(byUserId.getId());
            Integer integer1 = accountDetailService.addAccountDetail(accountDetail);
            baseResp.setCode(BaseResp.RET_SUCCESS);
            baseResp.setMsg("账户提现成功");
        }else {
            baseResp.setCode(BaseResp.RET_FAILED);
            baseResp.setMsg("账户提现失败");
        }
        return baseResp;
    }*/

}
