package com.sy.zd.controller;

import com.sy.zd.model.Func;
import com.sy.zd.model.User;
import com.sy.zd.model.base.BaseResult;
import com.sy.zd.service.FuncService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 权限操作
 */
@RestController
@RequestMapping("/func")
public class FuncController {

    @Autowired
    private FuncService service;

    @RequiresPermissions("/func/find.do")
    @RequestMapping("/find.do")
    public BaseResult findFunc(Integer id)throws Exception{

        Func byId = service.findById(id);
        BaseResult baseResult = new BaseResult();
        baseResult.setData(byId);
        baseResult.setCode(BaseResult.CODE_SUCCESS);
        baseResult.setMsg(BaseResult.MSG_SUCCESS);
        return baseResult;
    }


    @RequestMapping("/add.do")
    @RequiresPermissions("/func/add.do")
    public BaseResult addFunc(Func func, HttpSession session) throws Exception{
        func.setCreationTime(new Date());
        Integer save = service.save(func,(User)session.getAttribute("sessionUser"));
        BaseResult baseResult = new BaseResult();
        if(save!=null && save !=0){
            baseResult.setMsg(BaseResult.MSG_SUCCESS);
            baseResult.setCode(BaseResult.CODE_SUCCESS);
        }else{
            baseResult.setMsg(BaseResult.MSG_FAILED);
            baseResult.setCode(BaseResult.CODE_FAILED);
        }
        return baseResult;
    }
}
