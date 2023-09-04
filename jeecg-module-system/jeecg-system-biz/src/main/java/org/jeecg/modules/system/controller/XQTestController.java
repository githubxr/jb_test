package org.jeecg.modules.system.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xqtest")
public class XQTestController {
    @ApiOperation(value="XQ测试接口", notes="XQ测试接口")
    @GetMapping(value = "/test")
    public String test(){
        return "成功！";
    }
}
