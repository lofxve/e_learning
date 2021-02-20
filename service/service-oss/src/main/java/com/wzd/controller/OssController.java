package com.wzd.controller;


import com.wzd.commonutils.R;
import com.wzd.service.FileService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssController
 * @Author lofxve
 * @Date 2021/2/20 9:23
 * @Version 1.0
 */
@CrossOrigin
@RestController
@Api(tags ="阿里云文件管理")
@RequestMapping("/admin/eduoss/file")
public class OssController {
    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public R uploadOssFile(MultipartFile file){
        String url = fileService.upload(file);
        return R.ok().data("url",url);
    }

}
