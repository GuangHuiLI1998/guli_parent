package com.IT.service.edu.feign.impl;

import com.IT.common.base.result.R;
import com.IT.service.edu.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>TODO</p>
 *
 * @author IT小李
 * @version V1.0.0
 * @date 2023/2/12 9:53
 */

@Service
@Slf4j
public class OssFileServiceFallBackImpl implements OssFileService {

 @Override
 public R removeFile(String url) {


   log.info("熔断保护");
   return R.error().message("调用超时");
 }




    @Override
    public R test() {log.info("熔断保护");
        return R.error().message("调用超时");
    }
}
