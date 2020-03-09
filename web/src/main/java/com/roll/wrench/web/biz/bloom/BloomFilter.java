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

    boolean addRaw(byte[] raw);

    /**
     * 是否包含当前元素
     *
     * @param bytes 元素值
     * @return 包含返回true，不包含返回false
     */
    boolean contains(byte[] bytes);

    /**
     * 清楚数据
     */
    void clear();

    /**
     * 获取hash后的数组，元素位置数组
     *
     * @param bytes 需要被hash的数据
     * @return hash后的数据
     */
    public default int[] hash(byte[] bytes) {
        return config().hashFunction().hash(bytes, config().getSize(), config().getHashes());
    }

    /**
     * 增加一个元素
     *
     * @param element 元素
     * @return 是否增加成功
     */
    public default boolean add(T element) {
        return addRaw(toBytes(element));
    }

    /**
     * 判断过滤器中是否包含制定元素
     *
     * @param element 元素值
     * @return 包含返回true
     */
    public default boolean contains(T element) {
        return contains(toBytes(element));
    }

    /**
     * 给定的数据转换为byte数组
     *
     * @param value 需要被转换的值
     * @return byte数组
     */
    public default byte[] toBytes(T value) {
        return value.toString().getBytes(Charset.forName("utf-8"));
    }
}
