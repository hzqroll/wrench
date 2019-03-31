package com.roll.wrench.inner.dubbo;

import com.roll.wrench.inner.pojo.DubboGenericConfig;
import org.junit.Test;

/**
 * @author zongqiang.hao
 * created on 2019-03-29 15:37.
 */
public class GenericServiceImplTest {
    @Test
    public void invokeTest() {
        DubboGenericConfig  genericConfig = new DubboGenericConfig();
        genericConfig.setServiceName("cn.fraudmetrix.creditcloud.bodyguard.api.intf.BodyGuard");
        genericConfig.setMethodName("queryEncryptConfig");
        genericConfig.setInputParamsArray(new String[]{"java.lang.String", "java.lang.String"});
        genericConfig.setInputObjectArray(new String[]{"zhaoyao", "balala"});
    }
}
