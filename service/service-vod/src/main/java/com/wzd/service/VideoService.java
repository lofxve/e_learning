package com.wzd.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName VideoService
 * @Author lofxve
 * @Date 2021/2/26 22:09
 * @Version 1.0
 */
public interface VideoService {
    String uploadVideo(MultipartFile file);
}
