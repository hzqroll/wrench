package com.roll.wrench.web.controller;

import com.roll.wrench.web.dal.dubbo.DubboGenericConfigDO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zongqiang.hao
 * created on 2019-04-01 20:48.
 */
@Controller
public class DubboConfigController {
    @RequestMapping("dubbo/config")
    public String getConfig(Model model) {
        DubboGenericConfigDO genericConfig = new DubboGenericConfigDO();
        genericConfig.setServiceName("cn.fraudmetrix.creditcloud.bodyguard.api.intf.BodyGuard");
        genericConfig.setMethodName("queryEncryptConfig");
        genericConfig.setVersion("1.0.0_118_hzq");
        genericConfig.setInput("input");
        model.addAttribute("genericConfig", genericConfig);
        return "dubbo/config";
    }

    @RequestMapping("dubbo/update")
    @ResponseBody
    public String getConfig(String serviceName) {
        System.out.println(serviceName);
        return "success";
    }
}
