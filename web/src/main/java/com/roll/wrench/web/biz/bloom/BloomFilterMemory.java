package com.roll.wrench.web.biz.bloom;

import java.util.BitSet;

/**
 * 机遇内存的bloom实现
 *
 * @author roll
 * created on 2020/3/7 10:07 下午
 */
public class BloomFilterMemory<T> implements BloomFilter<T> {
    private final FilterBuilder config;

    /**
     * 底层数据结构
     */
    private BitSet bloom;

    public BloomFilterMemory(FilterBuilder config) {
        this.config = config;
    }

    @Override
    public FilterBuilder config() {
        return null;
    }

    @Override
    public boolean addRaw(byte[] raw) {
        return false;
    }

    @Override
    public void clear() {

    }

    protected void setBit(int index, boolean to) {
        bloom.set(index, to);
    }

    protected boolean setBit(int index) {
        return bloom.get(index);
    }
}
