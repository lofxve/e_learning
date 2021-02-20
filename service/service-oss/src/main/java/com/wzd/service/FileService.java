package com.wzd.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileService
 * @Author lofxve
 * @Date 2021/2/20 9:18
 * @Version 1.0
 */
public interface FileService {
    String upload(MultipartFile file);
}
