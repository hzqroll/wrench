package com.roll.wrench.inner.aop;

import com.roll.wrench.inner.dubbo.cache.ReferenceConfigLocalCache;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zongqiang.hao
 * created on 2019-03-30 19:05.
 */
@Service
@Aspect
public class CacheAop {

    @Autowired
    private ReferenceConfigLocalCache referenceConfigLocalCache;

    @Pointcut(value = "execution(* com.roll.wrench.inner..*.invoke(com.roll.wrench.inner.pojo.DubboGenericConfig)) && args(genericConfig)")
    public void genericPointcut(com.roll.wrench.inner.pojo.DubboGenericConfig genericConfig) {
    }

    @Before(value = "genericPointcut(genericConfig)", argNames = "genericConfig")
    public void refreshGenericConfigCache(com.roll.wrench.inner.pojo.DubboGenericConfig genericConfig) {
        ReferenceConfig referenceConfig = new ReferenceConfig();
        ApplicationConfig applicationConfig = new ApplicationConfig();
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(genericConfig.getAddress());
        registryConfig.setProtocol(genericConfig.getProtocol());
        registryConfig.setGroup(genericConfig.getGroup());
        applicationConfig.setName(genericConfig.getName());
        applicationConfig.setRegistry(registryConfig);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setGeneric(true);
        referenceConfig.setInterface(genericConfig.getServiceName());
        referenceConfig.setVersion(genericConfig.getVersion());

        referenceConfigLocalCache.put(genericConfig, referenceConfig);
    }
}
