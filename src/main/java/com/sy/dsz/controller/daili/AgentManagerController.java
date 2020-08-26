package com.sy.dsz.controller.daili;

import com.github.pagehelper.PageInfo;

import com.sy.common.model.BaseResult;
import com.sy.dsz.service.daili.AgentManagerService;
import com.sy.zd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/agent")
public class AgentManagerController {

    @Autowired
    private AgentManagerService service;

    @RequestMapping("/findUserByReferId.do")
    public BaseResult findUserByReferId(String roleCode, BaseResult baseResult, HttpSession session) throws Exception {
        User use = (User) session.getAttribute("userSession");
        PageInfo userByReferId = service.findUserByReferId(use, roleCode, baseResult);
        if (userByReferId.getList().size() > 0) {
            baseResult.setTotalPage(userByReferId.getPages());
            baseResult.setCount((int) userByReferId.getTotal());
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("查询代理商成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("暂无下级代理商");
        }
        baseResult.setData(userByReferId.getList());
        return baseResult;
    }

    @RequestMapping("/findAgentManagerBySomeCondition.do")
    public BaseResult findAgentManagerBySomeCondition(User searcherUser, HttpSession session, String roleCode, BaseResult baseResult) throws Exception {
        User use = (User) session.getAttribute("userSession");
        PageInfo agentManagerBySomeCondition = service.findAgentManagerBySomeCondition(use, searcherUser, roleCode, baseResult);
        if (agentManagerBySomeCondition.getList().size() > 0) {
            baseResult.setTotalPage(agentManagerBySomeCondition.getPages());
            baseResult.setCount((int) agentManagerBySomeCondition.getTotal());
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("代理商查询成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("暂无代理商信息");
        }
        baseResult.setData(agentManagerBySomeCondition.getList());
        return baseResult;
    }
}
