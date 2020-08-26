package com.sy.dsz.service.zixun;

import com.github.pagehelper.PageInfo;
import com.sy.common.model.BaseResult;
import com.sy.dsz.model.zixun.Information;


public interface InformationService {
    Integer addInformation(Information information) throws Exception;

    PageInfo findAll(BaseResult baseResult) throws Exception;

    PageInfo findAll2(BaseResult baseResult) throws Exception;

    Integer removeInformation(Information information) throws Exception;

    Integer modifyInformation(Information information) throws Exception;

    Information findInformationById(Integer id) throws Exception;

    PageInfo findByTitle(Information information, BaseResult baseResult) throws Exception;

    PageInfo findByTitle2(Information information, BaseResult baseResult) throws Exception;
}
