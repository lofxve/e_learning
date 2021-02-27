package com.wzd.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName VideoService
 * @Author lofxve
 * @Date 2021/2/26 22:09
 * @Version 1.0
 */
public interface VideoService {
    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);

    void deleteBatch(List videoIdList);
}
