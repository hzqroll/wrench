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

    private int a = 10;
    /**
     * 底层数据结构
     */
    private BitSet bloom;

    public BloomFilterMemory(FilterBuilder config) {
        this.config = config;
        bloom = new BitSet(config.getSize());
    }

    @Override
    public FilterBuilder config() {
        return config;
    }

    @Override
    public boolean addRaw(byte[] raw) {
        boolean added = false;
        for (int position : hash(raw)) {
            if (!getBits(position)) {
                added = true;
                setBit(position, true);
            }
        }
        return added;
    }

    @Override
    public boolean contains(byte[] bytes) {
        for (int position : hash(bytes)) {
            if (!getBits(position)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        bloom.clear();
    }

    protected void setBit(int index, boolean to) {
        bloom.set(index, to);
    }

    protected boolean setBit(int index) {
        return bloom.get(index);
    }

    protected boolean getBits(int index) {
        return bloom.get(index);
    }
}
