package com.roll.wrench.web.bloom;

import com.roll.wrench.web.biz.bloom.BloomFilter;
import com.roll.wrench.web.biz.bloom.BloomFilterMemory;
import com.roll.wrench.web.biz.bloom.FilterBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author roll
 * created on 2020/3/9 5:25 下午
 */
@RunWith(JUnit4.class)
public class BloomFilterTest {

    public BloomFilter getBloomFilter(int size, int hashes) {
        FilterBuilder config = new FilterBuilder();
        config.setSize(size);
        config.setHashes(hashes);
        return new BloomFilterMemory<>(config);
    }

    @Test
    public void testMemoryFilter() {
        BloomFilter<String> bloomFilter = getBloomFilter(100000, 3);
        for (int i = 1; i < 10; i++) {
            bloomFilter.add(String.valueOf(i));
        }

        assertTrue(bloomFilter.contains("1"));
        assertTrue(bloomFilter.contains("1"));
        assertFalse(bloomFilter.contains("68676"));
        assertFalse(bloomFilter.contains("43534536"));
        assertFalse(bloomFilter.contains("435"));
        assertFalse(bloomFilter.contains("不存在3"));
        assertFalse(bloomFilter.contains("sdfs"));
        assertFalse(bloomFilter.contains("不存sfsdf在"));
    }

    @Test
    public void testMultiThreadMemoryFilter() {
        BloomFilter<String> bloomFilter = getBloomFilter(100000, 3);
        for (int i = 0; i < 10; i++) {
            final int base = i;
            new Thread(() -> {
                for (int j = 1; j < 10; j++) {
                    bloomFilter.add(String.valueOf(base).concat(String.valueOf(j)));
                }
            }).start();
        }

        assertTrue(bloomFilter.contains("11"));
        assertTrue(bloomFilter.contains("12"));
        assertFalse(bloomFilter.contains("85"));
        assertFalse(bloomFilter.contains("43534536"));
        assertFalse(bloomFilter.contains("435"));
        assertFalse(bloomFilter.contains("不存在3"));
        assertFalse(bloomFilter.contains("sdfs"));
        assertFalse(bloomFilter.contains("不存sfsdf在"));
    }
}
