package com.IT.service.edu.feign;

import com.IT.common.base.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "service-oss")
public interface OssFileService {
    @RequestMapping(value = "/admin/oss/file/remove", method = RequestMethod.DELETE)
    R removeFile(@RequestBody String url);

    @GetMapping("/admin/oss/file/test")
    R test();
}
