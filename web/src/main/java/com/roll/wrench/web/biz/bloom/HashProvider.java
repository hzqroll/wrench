package com.roll.wrench.web.biz.bloom;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.Random;

/**
 * @author roll
 * created on 2020/3/7 9:38 下午
 */
public class HashProvider {

    private static final int seed32 = 89478583;

    /**
     * @param value  需要被hash的数据
     * @param m      过滤器容量大小
     * @param k      需要计算的hash数量
     * @param method hash算法名称
     * @return value在数组中的位置集合，位置从[0,size]
     */
    public static int[] hashCrypt(byte[] value, int m, int k, String method) {
        //MessageDigest is not thread-safe --> use new instance
        MessageDigest cryptHash = null;
        try {
            cryptHash = MessageDigest.getInstance(method);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        int[] positions = new int[k];

        int computedHashes = 0;
        // Add salt to the hash deterministically in order to generate different
        // hashes for each round
        // Alternative: use pseudorandom sequence
        Random r = new Random(seed32);
        byte[] digest = new byte[0];
        while (computedHashes < k) {
            // byte[] saltBytes =
            // ByteBuffer.allocate(4).putInt(r.nextInt()).array();
            cryptHash.update(digest);
            digest = cryptHash.digest(value);
            BitSet hashed = BitSet.valueOf(digest);

            // Convert the hash to numbers in the range [0,size)
            // Size of the BloomFilter rounded to the next power of two
            int filterSize = 32 - Integer.numberOfLeadingZeros(m);
            // Computed hash bits
            int hashBits = digest.length * 8;
            // Split the hash value according to the size of the Bloomfilter --> higher performance than just doing modulo
            for (int split = 0; split < (hashBits / filterSize)
                    && computedHashes < k; split++) {
                int from = split * filterSize;
                int to = (split + 1) * filterSize;
                BitSet hashSlice = hashed.get(from, to);
                // Bitset to Int
                long[] longHash = hashSlice.toLongArray();
                int intHash = longHash.length > 0 ? (int) longHash[0] : 0;
                // Only use the position if it's in [0,size); Called rejection sampling
                if (intHash < m) {
                    positions[computedHashes] = intHash;
                    computedHashes++;
                }
            }
        }

        return positions;
    }

    public static interface HashFunction extends Serializable {
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

    /**
     * Hash方法枚举类
     */
    public static enum HashMethod {
        // MD2类型Hash算法
        MD2((bytes, m, k) -> HashProvider.hashCrypt(bytes, m, k, "MD2"));

        private HashFunction hashFunction;

        HashMethod(HashFunction hashFunction) {
            this.hashFunction = hashFunction;
        }

        public HashFunction getHashFunction() {
            return hashFunction;
        }
    }

}
