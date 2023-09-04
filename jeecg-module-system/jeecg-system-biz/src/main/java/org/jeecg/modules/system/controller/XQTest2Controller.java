package org.jeecg.modules.system.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/xqtest2")
public class XQTest2Controller {
    @ApiOperation(value="XQ测试接口", notes="XQ测试接口")
    @GetMapping(value = "/test2")
    public String test(){
        return "成功！";
    }
}
