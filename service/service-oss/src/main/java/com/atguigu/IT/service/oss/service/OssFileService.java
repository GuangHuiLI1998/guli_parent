package com.atguigu.IT.service.oss.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * <p>
 * OSS 服务类
 * </p>
 *
 * @author Kim
 * @version 1.0
 * @since 2022/11/27
 */
@Service
@FeignClient("service-oss")
public interface OssFileService {

    /**
     * 阿里云OSS文件上传
     * @param inputStream 输入流
     * @param module 文件夹名称
     * @param originalFilename 原始文件名
     * @return 文件在OSS服务器上的url地址
     * @author Kim
     */
    String upload(InputStream inputStream, String module, String originalFilename);

    /**
     * 阿里云OSS文件删除
     * @param url 文件在OSS服务器上的url地址
     * @since 2022/11/28 7:01
     * @author Kim
     */
    void removeOssFile(String url);



}