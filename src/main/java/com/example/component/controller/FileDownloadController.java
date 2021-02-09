package com.example.component.controller;

import com.example.component.service.FileDownloadService;
import com.example.component.util.Result;
import com.sun.deploy.net.HttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: Vlan
 * Date: 2021/2/8 9:00
 */
@RestController
public class FileDownloadController {
    @Resource
    private FileDownloadService fileDownloadService;
    
    //单个文件下载
    @RequestMapping("/download")
    public void download(HttpServletResponse response){
        fileDownloadService.download(response);
    }
    
    //多个文件打包下载
    @RequestMapping("/downloadZip")
    public void downloadMultipleFileToZip(HttpServletResponse response){
        fileDownloadService.downloadMultipleFileToZip(response);
    }
}
