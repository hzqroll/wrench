package com.roll.wrench.web.biz.bloom;

/**
 * @author roll
 * created on 2020/3/6 5:35 下午
 */
public class HashRandom implements HashInterface {
    @Override
    public int findPosition(String value, int poolSize) {
        return 0;
    }

    public static void main(String[] args) {
        String key = "1231";
        int h;
        int hashCode = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        System.out.println(hashCode);
    }
}
