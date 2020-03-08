package com.roll.wrench.web.biz.bloom;

import java.nio.charset.Charset;

/**
 * 简单布隆过滤器
 *
 * @author roll
 * created on 2020/3/6 5:06 下午
 */
public interface BloomFilter<T> {

    /**
     * 配置信息
     *
     * @return 配置信息
     */
    FilterBuilder config();

    public boolean addRaw(byte[] raw);

    void clear();

    public default boolean add(T element) {
        return addRaw(toBytes(element));
    }

    public default byte[] toBytes(T value) {
        return value.toString().getBytes(Charset.forName("utf-8"));
    }
}
