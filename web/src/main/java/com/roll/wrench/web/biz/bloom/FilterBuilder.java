package com.roll.wrench.web.biz.bloom;

/**
 * @author roll
 * created on 2020/3/7 9:23 下午
 */
public class FilterBuilder {

    /**
     * 数组长度
     */
    public int size;
    /**
     * hash个数
     */
    public int hashes;

    private HashProvider.HashMethod hashMethod = HashProvider.HashMethod.MD2;

    private HashProvider.HashFunction hashFunction = HashProvider.HashMethod.MD2.getHashFunction();

    public FilterBuilder() {
    }

    public HashProvider.HashFunction hashFunction() {
        return hashFunction;
    }

    public HashProvider.HashMethod hashMethod() {
        return hashMethod;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHashes() {
        return hashes;
    }

    public void setHashes(int hashes) {
        this.hashes = hashes;
    }

    public HashProvider.HashMethod getHashMethod() {
        return hashMethod;
    }

    public void setHashMethod(HashProvider.HashMethod hashMethod) {
        this.hashMethod = hashMethod;
    }

    public HashProvider.HashFunction getHashFunction() {
        return hashFunction;
    }

    public void setHashFunction(HashProvider.HashFunction hashFunction) {
        this.hashFunction = hashFunction;
    }
}


