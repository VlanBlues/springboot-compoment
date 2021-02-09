package com.example.component.controller;

import com.example.component.service.FileUploadService;
import com.example.component.util.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Author: Vlan
 * Date: 2021/2/2 10:18
 */
@RestController
public class FileUploadController {
    
    @Resource
    private FileUploadService fileUploadService;
    
    @RequestMapping("/upload")
    public Result singleUpload(@RequestParam("file") MultipartFile file){
        return fileUploadService.singleUpload(file);
    }
    
    //这种方式上传的时间短
    @RequestMapping("/upload2")
    public Result singleUpload2(@RequestParam("file") MultipartFile file){
        return fileUploadService.singleUpload2(file);
    }

    //多文件上传
    @RequestMapping("/multipleUpload")
    public Result multipleUpload(@RequestParam("files") MultipartFile[] files){
        return fileUploadService.multipleUpload(files);
    }
}
