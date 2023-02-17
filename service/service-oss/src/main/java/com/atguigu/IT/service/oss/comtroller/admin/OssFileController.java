package com.atguigu.IT.service.oss.comtroller.admin;

import com.IT.common.base.result.R;
import com.IT.common.base.result.ResultCodeEnum;
import com.IT.service.base.exception.ITException;
import com.atguigu.IT.service.oss.service.OssFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * <p>TODO</p>
 *
 * @author IT小李
 * @version V1.0.0
 * @date 2023/2/11 10:04
 */
@Api(tags = "阿里云文件管理")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/admin/oss/file")
public class OssFileController {


    @Resource
    private OssFileService ossFileService;

    /**
     * 阿里云OSS文件上传
     * @param file 上传的文件
     * @param module 模块名
     * @return: 上传OSS文件url
     * @since 2022/11/28 7:23
     * @author Kim
     */
    @ApiOperation(value = "文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public R upload(@ApiParam(value = "文件", required = true)@RequestParam("file") MultipartFile file, @ApiParam(value = "模块", required = true)@RequestParam("module") String module) throws ITException {

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String uploadUrl = ossFileService.upload(inputStream, module, originalFilename);

            return R.ok().message("文件上传成功").data("url", uploadUrl);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new ITException(ResultCodeEnum.FILE_UPLOAD_ERROR); //ITException使用的是service_base中的异常文件
        }
    }

    /**
     * 文件删除
     * @param url 上传OSS文件url
     * @return: 文件删除成功
     * @since 2022/11/28 7:30
     * @author Kim
     */
    @ApiOperation(value = "文件删除")
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public R removeFile(@ApiParam(value = "模块", required = true)@RequestBody String url){

        ossFileService.removeOssFile(url);
        return R.ok().message("文件删除成功！");
    }





    @ApiOperation(value = "测试")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public R test(){
        log.info("oss test被调用");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok();
    }
}
