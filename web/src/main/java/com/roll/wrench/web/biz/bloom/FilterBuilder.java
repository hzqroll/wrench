package com.roll.wrench.web.biz.bloom;

/**
 * @author roll
 * created on 2020/3/7 9:23 下午
 */
public class FilterBuilder {

    private HashProvider.HashMethod hashMethod = HashProvider.HashMethod.MD2;

    private HashProvider.HashFunction hashFunction = HashProvider.HashMethod.MD2.getHashFunction();

    public FilterBuilder() {
    }
}
