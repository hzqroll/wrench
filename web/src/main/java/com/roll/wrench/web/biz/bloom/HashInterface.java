package com.roll.wrench.web.biz.bloom;

import java.io.Serializable;

/**
 * @author roll
 * created on 2020/3/6 5:23 下午
 */
public interface HashInterface extends Serializable {
    /**
     * 计算hash值
     *
     * @param value 申明需要被hash的数组
     * @param m     过滤器容量大小
     * @param k     需要计算的hash数量
     * @return hash结果数组
     */
    public int[] hash(byte[] value, int m, int k);
}
