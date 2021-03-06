package com.roll.wrench.inner.controller;

import com.roll.wrench.inner.dubbo.GenericServiceImpl;
import com.roll.wrench.inner.pojo.DubboGenericConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zongqiang.hao
 * created on 2019-03-29 15:36.
 */
@RestController
public class ApiController {

    @Autowired
    private GenericServiceImpl genericService;

    @RequestMapping("/invoke/dubbo")
    public String invokeDubbo() {
        DubboGenericConfig genericConfig = new DubboGenericConfig();
        genericConfig.setServiceName("cn.f.c.b.api.intf.BodyGuard");
        genericConfig.setMethodName("queryEncryptConfig");
        genericConfig.setVersion("1.0.0_118_hzq");
        genericConfig.setInputParamsArray(new String[]{"java.lang.String", "java.lang.String"});
        genericConfig.setInputObjectArray(new String[]{"zhaoyao", "balala"});
        genericService.invoke(genericConfig);
        return "成功!";
    }
}
