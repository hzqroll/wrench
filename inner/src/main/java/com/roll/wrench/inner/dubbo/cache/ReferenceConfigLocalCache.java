package com.roll.wrench.inner.dubbo.cache;

import com.roll.wrench.inner.pojo.DubboGenericConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link ReferenceConfig} 缓存内容
 *
 * @author zongqiang.hao
 * created on 2019-03-30 20:21.
 */
@Service("referenceConfigLocalCache")
public class ReferenceConfigLocalCache {

    /**
     * ReferenceConfig缓存数据
     */
    private static Map<String, ReferenceConfig> referenceConfigMap = new ConcurrentHashMap<>();

    public ReferenceConfig get(DubboGenericConfig genericConfig) {
        return referenceConfigMap.get(getKey(genericConfig));
    }


    public ReferenceConfig put(DubboGenericConfig genericConfig, ReferenceConfig referenceConfig) {
        return referenceConfigMap.put(getKey(genericConfig), referenceConfig);
    }

    private String getKey(DubboGenericConfig genericConfig) {
        return genericConfig.getServiceName() + "_" + genericConfig.getVersion();
    }
}
