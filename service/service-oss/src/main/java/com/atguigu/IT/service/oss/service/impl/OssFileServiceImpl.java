package com.atguigu.IT.service.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.atguigu.IT.service.oss.service.OssFileService;
import com.atguigu.IT.service.oss.util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.UUID;

/**
 * <p>
 * OSS 服务实现类
 * </p>
 *
 * @author Kim
 * @version 1.0
 * @since 2022/11/27
 */
@Service
public class OssFileServiceImpl implements OssFileService {

    @Resource
    private OssProperties ossProperties;

    /**
     * 阿里云OSS文件上传
     *
     * @param inputStream      输入流
     * @param module           文件夹名称
     * @param originalFilename 原始文件名
     * @return 文件在OSS服务器上的url地址
     * @author Kim
     */
    @Override
    public String upload(InputStream inputStream, String module, String originalFilename) {
        // 读取配置信息
        String endpoint = ossProperties.getEndpoint();
        String bucketName = ossProperties.getBucketname();
        String accessKeyId = ossProperties.getAccesskeyid();
        String accessKeySecret = ossProperties.getAccesskeysecret();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //判断OSS实例是否存在：如果不存在则创建，如果存在则获取
        if (!ossClient.doesBucketExist(bucketName)){
            // 没有则创建Bucket
            ossClient.createBucket(bucketName);
            // 设置存储空间的权限为公共读，默认为私有。
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }

        //构建objectName: 文件路径 avatar/2019/02/26/文件名
        String folder = new DateTime().toString("yyyy/MM/dd");
        //文件名：uuid.扩展名
        String fileName = UUID.randomUUID().toString();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String key = module + "/" + folder + "/" + fileName + fileExtension;

        // 上传文件流
        ossClient.putObject(bucketName, key, inputStream);

        // 关闭ossClient
        ossClient.shutdown();

        //返回url地址
        return "https://" + bucketName + "." + endpoint + "/" + key;
    }

    /**
     * 阿里云OSS文件删除
     *
     * @param url 文件在OSS服务器上的url地址
     * @author Kim
     * @since 2022/11/28 7:01
     */
    @Override
    public void removeOssFile(String url) {
        // 读取配置信息
        String endpoint = ossProperties.getEndpoint();
        String bucketName = ossProperties.getBucketname();
        String accessKeyId = ossProperties.getAccesskeyid();
        String accessKeySecret = ossProperties.getAccesskeysecret();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件 文件完整路径。文件完整路径中不能包含Bucket名称。
        String host = "https://" + bucketName+ "." + endpoint + "/";
        String objectName = url.substring(host.length());

        ossClient.deleteObject(bucketName, objectName);

        // 关闭ossClient
        ossClient.shutdown();

    }

}