package com.sy.dsz.controller.zixun;

import com.github.pagehelper.PageInfo;

import com.sy.common.model.BaseResult;
import com.sy.dsz.model.zixun.Affiche;
import com.sy.dsz.service.zixun.AfficheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/affiche")
public class AfficheController {

    @Autowired
    private AfficheService service;

    /**
     * 除管理员外其他角色展示所有公告，今天日期大于endTime的
     */
    @RequestMapping("/findAllByPage.do")
    public BaseResult findAllByPage(BaseResult baseResult) throws Exception {
        PageInfo allByPage = service.findAllByPage(baseResult);
        if (allByPage.getList().size() > 0) {
            baseResult.setPage(allByPage.getPages());
            baseResult.setCount((int) allByPage.getTotal());
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("公告查询成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("公告查询失败");
        }
        baseResult.setData(allByPage.getList());
        return baseResult;
    }

    /**
     * 根据Id查询公告
     */
    @RequestMapping("/findOneById.do")
    public BaseResult findOneById(Affiche affiche) throws Exception {
        Affiche oneById = service.findOneById(affiche.getId());
        BaseResult baseResp = new BaseResult();
        if (oneById != null) {
            baseResp.setCode(BaseResult.CODE_SUCCESS);
            baseResp.setMsg("公告查询成功");
        } else {
            baseResp.setCode(BaseResult.CODE_FAILED);
            baseResp.setMsg("公告查询失败");
        }
        baseResp.setData(oneById);
        return baseResp;
    }

    /**
     * 按照标题进行搜索
     */
    @RequestMapping("/findByTitle.do")
    public BaseResult findByTitle(Affiche affiche, BaseResult baseResult) throws Exception {
        PageInfo byTitle = service.findByTitle(affiche, baseResult);
        if (byTitle.getList().size() > 0) {
            baseResult.setPage(byTitle.getPages());
            baseResult.setCount((int) byTitle.getTotal());
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("标题模糊查询成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("暂未查询相关标题");
        }
        baseResult.setData(byTitle.getList());
        return baseResult;
    }

    /**
     * 管理员展示所有公告
     */
    @RequestMapping("/findAllByPage2.do")
    public BaseResult findAllByPage2(BaseResult baseResult) throws Exception {
        PageInfo allByPage = service.findAllByPage2(baseResult);
        if (allByPage.getList().size() > 0) {
            baseResult.setPage(allByPage.getPages());
            baseResult.setCount((int) allByPage.getTotal());
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("公告查询成功2");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("公告查询失败2");
        }
        baseResult.setData(allByPage.getList());
        return  baseResult;
    }

    /**
     * 添加公告
     */
    @RequestMapping("/addAffiche.do")
    public BaseResult addAffiche(Affiche affiche, HttpSession session) throws Exception {
       /* User user = (User) session.getAttribute("userSession");
        affiche.setPublisher(user.getUserName());
        Integer integer = service.addAffiche(affiche);
        BaseResp baseResp = new BaseResp();
        if (integer > 0) {
            baseResp.setCode(BaseResp.RET_SUCCESS);
            baseResp.setMsg("公告添加成功");
        } else {
            baseResp.setCode(BaseResp.RET_FAILED);
            baseResp.setMsg("公告添加失败");
        }
        return baseResp;*/
       return null;
    }

    /**
     * 删除公告
     */
    @RequestMapping("/removeAffiche.do")
    public BaseResult removeAffiche(Integer[] ids) throws Exception {
        Integer integer = 0;
        for (int i = 0; i < ids.length; i++) {
            integer = service.removeAffiche(ids[i]);
            integer = integer * 1;
        }
        BaseResult baseResp = new BaseResult();
        if (integer > 0) {
            baseResp.setCode(BaseResult.CODE_SUCCESS);
            baseResp.setMsg("公告删除成功");
        } else {
            baseResp.setCode(BaseResult.CODE_FAILED);
            baseResp.setMsg("公告删除失败");
        }
        return baseResp;

    }

    /**
     * 修改公告
     */
    @RequestMapping("/modifyAffiche.do")
    public BaseResult modifyAffiche(Affiche affiche, HttpSession session) throws Exception {
       /* User user = (User) session.getAttribute("userSession");
        affiche.setPublisher(user.getUserName());
        Integer integer = service.modifyAffiche(affiche);
        BaseResp baseResp = new BaseResp();
        if (integer > 0) {
            baseResp.setCode(BaseResp.RET_SUCCESS);
            baseResp.setMsg("公告修改成功");
        } else {
            baseResp.setCode(BaseResp.RET_FAILED);
            baseResp.setMsg("公告修改失败");
        }
        return baseResp;*/
       return null;
    }


}
