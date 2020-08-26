package com.sy.dsz.controller.zixun;

import com.github.pagehelper.PageInfo;

import com.sy.common.model.BaseResult;
import com.sy.dsz.model.zixun.LeaveMessage;
import com.sy.dsz.service.zixun.LeaveMessageService;
import com.sy.zd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/leaveMessage")
public class LeaveMessageController {
    @Autowired
    private LeaveMessageService service;

    @RequestMapping("/findByCreateBy.do")
    public BaseResult findByCreateBy(BaseResult baseResult, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("userSession");
        PageInfo oneByCreateBy = service.findOneByCreateBy(user.getReferCode(), baseResult);
        if (oneByCreateBy.getList().size() > 0) {
            baseResult.setCount((int) oneByCreateBy.getTotal());
            baseResult.setTotalPage(oneByCreateBy.getPages());
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("留言查询成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("暂无给他人的留言");
        }
        baseResult.setData(oneByCreateBy.getList());
        return baseResult;
    }

    @RequestMapping("/addLeaveMessage.do")
    public BaseResult addLeaveMessage(LeaveMessage leaveMessage, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("userSession");
        leaveMessage.setCreateBy(user.getReferCode());
        leaveMessage.setMessageCode(String.valueOf(System.currentTimeMillis()));
        leaveMessage.setState(0);
        leaveMessage.setCreateTime(new Date());
        System.out.println(leaveMessage);
        Integer integer = service.addLeaveMessage(leaveMessage);
        BaseResult baseResult = new BaseResult();
        if (integer != 0) {
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("留言添加成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("留言添加失败");
        }
        return baseResult;
    }

    @RequestMapping("/removeLeaveMessage.do")
    public BaseResult removeLeaveMessage(Integer[] ids) throws Exception {
        Integer integer = 0;
        for (int i = 0; i < ids.length; i++) {
            integer = service.removeLeaveMessage(ids[i]);
            integer = integer * 1;
        }
        BaseResult baseResult = new BaseResult();
        if (integer != 0) {
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("留言删除成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("留言删除失败");
        }
        return baseResult;
    }

    @RequestMapping("/findByTitle.do")
    public BaseResult findByTitle(String title, BaseResult baseResult) throws Exception {
        PageInfo byTitle = service.findByTitle(baseResult, title);
        if (byTitle.getList().size() > 0) {
            baseResult.setCount((int) byTitle.getTotal());
            baseResult.setTotalPage(byTitle.getPages());
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("标题查询成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("暂无满足此条件的留言");
        }
        baseResult.setData(byTitle.getList());
        return baseResult;
    }

    @RequestMapping("/findById.do")
    private BaseResult findById(Integer id) throws Exception {
        LeaveMessage oneById = service.findOneById(id);
        BaseResult baseResult = new BaseResult();
        if (oneById != null) {
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("单个留言查询成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("单个留言查询失败");
        }
        baseResult.setData(oneById);
        return baseResult;
    }


}
