package com.example.component.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: Vlan
 * Date: 2021/2/8 9:01
 */
public interface FileDownloadService {
    void download(HttpServletResponse res);
    
    void downloadMultipleFileToZip(HttpServletResponse res);
}
