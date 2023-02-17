package com.IT.service.edu.controller.admin;

import com.IT.common.base.result.R;
import org.springframework.web.bind.annotation.*;

/**
 * <p>TODO</p>
 *
 * @author IT小李
 * @version V1.0.0
 * @date 2023/2/5 17:18
 */
@CrossOrigin
@RestController
@RequestMapping("/user")

public class LoginController {

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        return R.ok()
                .data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
