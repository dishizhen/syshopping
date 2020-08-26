package com.sy.dsz.controller.zixun;

import com.github.pagehelper.PageInfo;

import com.sy.common.model.BaseResult;
import com.sy.dsz.model.zixun.Information;
import com.sy.dsz.service.zixun.InformationService;
import com.sy.zd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

@RestController
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InformationService service;

    @RequestMapping("/upload.do")
    public BaseResult uploadAndAddInformation(@RequestParam("file") MultipartFile file, Information information, HttpSession session, HttpServletResponse response) throws Exception {
        User userSession = (User) session.getAttribute("userSession");
        information.setState(1);
        information.setPublisher(userSession.getUsername());
        information.setPublishTime(new Date());
        information.setTypeId(1);
        System.out.println(information);
        //上传的附件大小
        long fileSize = file.getSize();
        //上传的附件名称
        String fileName = file.getOriginalFilename();
        information.setFileName(fileName);
        //上传的附件后缀名
        String typeName = file.getName();
        //重新给上传的文件命名
        Date date = new Date();
        fileName = date.getTime() + "_" + fileName;
        //上传的附件路径
        String filePath = "D:/123/" + fileName;
        information.setTypeName(typeName);
        information.setFilePath(filePath);
        information.setFileSize((double) fileSize);
        information.setUploadTime(new Date());
        file.transferTo(new File(filePath));
        Integer integer = service.addInformation(information);
        BaseResult baseResult = new BaseResult();
        if (integer != 0) {
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("咨询添加成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("咨询添加失败");
        }
        return baseResult;
    }

    @RequestMapping("/findAllInformation.do")
    public BaseResult findAllInformation(BaseResult baseResult) throws Exception {
        PageInfo all = service.findAll(baseResult);
        if (all.getList().size() > 0) {
            baseResult.setTotalPage(all.getPages());
            baseResult.setCount((int) all.getTotal());
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("咨询查询成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("暂无咨询信息");
        }
        baseResult.setData(all.getList());
        return baseResult;
    }

    @RequestMapping("/findAllInformation2.do")
    public BaseResult findAllInformation2(BaseResult baseResult) throws Exception {
        PageInfo all = service.findAll2(baseResult);
        if (all.getList().size() > 0) {
            baseResult.setTotalPage(all.getPages());
            baseResult.setCount((int) all.getTotal());
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("咨询查询成功!");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("暂无咨询信息");
        }
        baseResult.setData(all.getList());
        return baseResult;
    }

    @RequestMapping("/removeInformation.do")
    public BaseResult removeInformation(Information information) throws Exception {
        Integer integer = service.removeInformation(information);
        BaseResult baseResult = new BaseResult();
        if (integer > 0) {
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("删除成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("删除失败");
        }
        return baseResult;
    }

    @RequestMapping("/modifyInformation.do")
    public BaseResult modifyInformation(Integer[] ids) throws Exception {
        Integer integer = 0;
        for (int i = 0; i < ids.length; i++) {
            Information informationById = service.findInformationById(ids[i]);
            System.out.println(informationById);

            informationById.setState(0);
            integer = service.modifyInformation(informationById);
        }
        BaseResult baseResult = new BaseResult();
        if (integer > 0) {
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("修改成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("修改失败");
        }
        return baseResult;
    }

    @RequestMapping("/findInformationById.do")
    public BaseResult findInformationById(Integer id) throws Exception {
        Information informationById = service.findInformationById(id);
        BaseResult baseResult = new BaseResult();
        if (informationById != null) {
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("单个咨询查询成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("单个咨询查询失败");
        }
        baseResult.setData(informationById);
        return baseResult;
    }

    @RequestMapping("/findInformationByTitle.do")
    public BaseResult findInformationByTitle(Information information, BaseResult baseResult) throws Exception {
        PageInfo byTitle = service.findByTitle(information, baseResult);
        if (byTitle.getList().size() > 0) {
            baseResult.setTotalPage(byTitle.getPages());
            baseResult.setCount((int) byTitle.getTotal());
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("咨询查询成功!");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("暂无咨询信息!");
        }
        baseResult.setData(byTitle.getList());
        return baseResult;
    }

    @RequestMapping("/findInformationByTitle2.do")
    public BaseResult findInformationByTitle2(Information information, BaseResult baseResult) throws Exception {
        PageInfo byTitle = service.findByTitle2(information, baseResult);
        if (byTitle.getList().size() > 0) {
            baseResult.setTotalPage(byTitle.getPages());
            baseResult.setCount((int) byTitle.getTotal());
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("咨询查询成功");
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("暂无咨询信息!");
        }
        baseResult.setData(byTitle.getList());
        return baseResult;
    }

    @RequestMapping("/download.do")
    public ResponseEntity<byte[]> download(String filePath) throws Exception{
        int i = filePath.lastIndexOf("_");
        String reallyFileName = filePath.substring(i + 1);
        FileInputStream inputStream = new FileInputStream(filePath);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=" + reallyFileName);

        HttpStatus status = HttpStatus.OK;

        ResponseEntity<byte[]> entity = new ResponseEntity<>(bytes, httpHeaders, status);
        return entity;
    }

}
