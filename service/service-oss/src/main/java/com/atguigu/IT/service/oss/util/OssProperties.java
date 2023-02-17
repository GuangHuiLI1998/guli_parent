package com.atguigu.IT.service.oss.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>TODO</p>
 *
 * @author IT小李
 * @version V1.0.0
 * @date 2023/2/11 10:07
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {

    private String endpoint;
    private String bucketname;
    private String accesskeyid;
    private String accesskeysecret;
}
