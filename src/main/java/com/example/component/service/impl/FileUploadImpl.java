package com.example.component.service.impl;

import com.example.component.service.FileUploadService;
import com.example.component.util.Result;
import com.example.component.util.UUIDUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * Author: Vlan
 * Date: 2021/2/2 10:55
 */
@Service
public class FileUploadImpl implements FileUploadService {

    //文件上传位置
    @Value("${imgLocal.test}")
    private String path;

    //这种方式耗时长
    @Override
    public Result singleUpload(MultipartFile file) {
        //判断文件是否为空
        if (file.isEmpty()) {
            return Result.fail("文件为空！");
        }
        //创建输入输出流
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            //获取文件的输入流
            inputStream = file.getInputStream();
            //获取上传时的文件名
            String fileName = file.getOriginalFilename();
            //获取文件类型
            //String contentType = file.getContentType();
            fileName = UUIDUtil.getUUID() + "_" + fileName;
            //注意是路径+文件名
            File targetFile = new File(path + fileName);
            //判断文件父目录是否存在
            if (!targetFile.getParentFile().exists()) {
                //不存在就创建一个
                targetFile.getParentFile().mkdir();
            }
            //获取文件的输出流
            outputStream = new FileOutputStream(targetFile);
            //最后使用资源访问器FileCopyUtils的copy方法拷贝文件
            FileCopyUtils.copy(inputStream, outputStream);
            return Result.success("上传成功！");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("上传失败！");
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }

    //两种方式保存文件 经测试 这种方式更省时间
    @Override
    public Result singleUpload2(MultipartFile file) {
        //判断文件是否为空
        if (file.isEmpty()) {
            return Result.fail("文件为空！");
        }
        // 文件名
        String originalFilename = file.getOriginalFilename();
        //获取文件类型
        //String contentType = file.getContentType();
        // 存储路径
        String filePath = path + System.currentTimeMillis() + originalFilename;
        if(saveFile(filePath, file)){
            return Result.success("上传成功！");
        }else {
            return Result.fail("文件保存失败！");
        }
    }

    @Override
    public Result multipleUpload(MultipartFile[] files) {
        //判断文件是否为空
        if (files.length == 0) {
            return Result.fail("文件为空！");
        }
        String originalFilename = "";
        String filePath = "";
        for (MultipartFile file : files) {
            // 文件名
            originalFilename = file.getOriginalFilename();
            // 存储路径
            filePath = path + System.currentTimeMillis() + originalFilename;
            if(!saveFile(filePath, file)){
                Result.fail("文件保存失败！");
            }
        }
        return Result.success("上传成功！");
    }

    private boolean saveFile(String filePath, MultipartFile file) {
        try {
            File targetFile = new File(filePath);
            //判断文件父目录是否存在
            if (!targetFile.getParentFile().exists()) {
                //不存在就创建一个
                targetFile.getParentFile().mkdir();
            }
            // 保存文件
            file.transferTo(targetFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
