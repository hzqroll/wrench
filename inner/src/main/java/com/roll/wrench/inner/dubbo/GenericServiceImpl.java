package com.roll.wrench.inner.dubbo;

import com.alibaba.fastjson.JSON;
import com.roll.wrench.inner.dubbo.cache.ReferenceConfigLocalCache;
import com.roll.wrench.inner.pojo.DubboGenericConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zongqiang.hao
 * created on 2019-03-28 09:02.
 */
@Service
public class GenericServiceImpl {

    private ReferenceConfigCache referenceConfigCache = ReferenceConfigCache.getCache();

    @Autowired
    private ReferenceConfigLocalCache referenceConfigLocalCache;

    public void invoke(DubboGenericConfig genericConfig) {
        ReferenceConfig referenceConfig = referenceConfigLocalCache.get(genericConfig);
        if (referenceConfig != null) {
            GenericService genericService = (GenericService) referenceConfigCache.get(referenceConfig);
            Object result = genericService.$invoke(genericConfig.getMethodName(), genericConfig.getInputParamsArray(), genericConfig.getInputObjectArray());
            System.out.println(JSON.toJSONString(result));
        } else {
            System.out.println("get referenceConfig failed!");
        }
    }
}
