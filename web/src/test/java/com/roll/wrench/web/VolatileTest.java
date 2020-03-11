package com.roll.wrench.web;

/**
 * @author zongqiang.hao
 * created on 2020-03-10 21:06.
 */
public class VolatileTest {
    static  int a = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                // 每一个线程拿到的 a 都是最新的，但是 a 在线程中累加的时候，外面的线程已经把 a 的值更改了，但是，累加的时候
                // 确还是首次拿到的数值，导致结果错误
                //for (int j = 0; j < 100; j++) {
                // 拿到 a 的值
                // 对 数据进行+1
                // 给 a 赋值
                    a = a + 1;
                //}
            }).start();
        }
        System.out.println(a);
    }
}
