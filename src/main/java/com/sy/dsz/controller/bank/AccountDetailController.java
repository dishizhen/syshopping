package com.sy.dsz.controller.bank;

import com.github.pagehelper.PageInfo;

import com.sy.common.model.BaseResult;
import com.sy.dsz.model.bank.UserAccount;
import com.sy.dsz.service.bank.AccountDetailService;
import com.sy.dsz.service.bank.UserAccountService;
import com.sy.zd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/accountDetail")
public class AccountDetailController {
    @Autowired
    private AccountDetailService service;
    @Autowired
    private UserAccountService userAccountService;

    /**
     * 
     * @param session
     * @param baseResult
     * @return
     * @throws Exception
     */
    @RequestMapping("/findUserWithdraw.do")
    public BaseResult findUserWithdraw(HttpSession session, BaseResult baseResult) throws Exception {
        User user = (User) session.getAttribute("userSession");
        UserAccount byUserId = userAccountService.findByUserId(user.getId());
        PageInfo byAccountId = service.findUserWithdraw(byUserId.getId(), baseResult);
        if (byAccountId.getList().size() > 0) {
            baseResult.setTotalPage(byAccountId.getPages());
            baseResult.setCount((int) byAccountId.getTotal());
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("提现信息查询成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("提现信息查询失败");
        }
        baseResult.setData(byAccountId.getList());
        return baseResult;
    }

}
