package com.example.component.service.impl;


import com.example.component.service.FileDownloadService;
import com.example.component.util.ZipUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Vlan
 * Date: 2021/2/8 9:02
 */
@Service
@Slf4j
public class FileDownloadImpl implements FileDownloadService {
    @Override
    public void download(HttpServletResponse res) {
        BufferedInputStream bis = null;
        OutputStream os = null;
        try{
            String fileName = "8.jpg";
            String filePath = "F:\\img\\user_img\\";
            File file = new File(filePath,fileName);
            if(file.exists()){
                res.reset();
                res.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
                res.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
                res.setHeader("Content-Length", String.valueOf(file.length()));
                res.setContentType("application/octet-stream;charset=UTF-8");
                byte[] buff = new byte[1024];
                os = res.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
            }
            log.info("文件下载！");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(os);
        }
    }

    //多个文件打包下载
    @Override
    public void downloadMultipleFileToZip(HttpServletResponse res) {
        FileOutputStream fos = null;
        try {
            String zipName = "test01.zip";
            res.reset();
            res.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            res.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(zipName,"UTF-8"));
            //res.setHeader("Content-Length", String.valueOf(file.length()));
            res.setContentType("application/octet-stream;charset=UTF-8");
            List<File> fileList = new ArrayList<>();
            fileList.add(new File("F:/img/user_img/8.jpg"));
            fileList.add(new File("F:/img/user_img/9.jpg"));
            //String zipPath = "F:/img/temporary/";
            //String zipName = "test01.zip";
            //File temDir = new File(zipPath,zipName);
            //if (!temDir.exists()) {
            //    temDir.mkdirs();
            //}
            //fos = new FileOutputStream(temDir);
            ZipUtils.toZip(fileList, res.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        
    }
}
