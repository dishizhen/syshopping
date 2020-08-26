package com.sy.dsz.service.zixun;

import com.github.pagehelper.PageInfo;
import com.sy.common.model.BaseResult;

import com.sy.dsz.model.zixun.Affiche;

/**
 * @Author: curtain
 * @Date: 2020/8/26 13:40
 * @Description:
 */
public interface AfficheService {


    PageInfo findAllByPage(BaseResult baseResult) throws Exception;

    PageInfo findAllByPage2(BaseResult baseResult) throws Exception;

    Affiche findOneById(Integer id) throws Exception;

    PageInfo findByTitle(Affiche affiche, BaseResult baseResult) throws Exception;

    Integer addAffiche(Affiche affiche) throws Exception;

    Integer removeAffiche(Integer id) throws Exception;

    Integer modifyAffiche(Affiche affiche) throws Exception;

}
