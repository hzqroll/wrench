package com.roll.wrench.inner.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zongqiang.hao
 * created on 2019-04-03 14:21.
 */
public class MapTest implements Runnable {
    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
//        MapTest mapTest = new MapTest();
//        mapTest.test(false);
//        //executor.submit(mapTest);
//
//        MapTest mapTest1 = new MapTest();
//        mapTest1.test(true);
//        //executor.submit(mapTest1);

        Map<String, List<String>> a = new ConcurrentHashMap<>();
        List<String> bList = new ArrayList<>();
        bList.add("123");
        a.put("23", bList);


        Thread thread1 = new Thread(() -> {
            System.out.println("get start");
            List<String> b = a.get("23");
            try {
                Thread.sleep(11000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get 0 start");
            System.out.println("get :" + b.get(0));
        });
        thread1.start();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("remove start");
            a.remove("23");
        });
        thread.start();

        Thread thread2 = new Thread(() -> {
            System.out.println("get start");
            List<String> b = a.get("23");
            try {
                Thread.sleep(11000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get 0 start");
            System.out.println("get :" + b.get(0));
        });
        thread2.start();
    }

    public void test(boolean flag) {
        Map<String, List<String>> a = new ConcurrentHashMap<>();
        List<String> bList = new ArrayList<>();
        bList.add("123");
        a.put("23", bList);
        MapTest threadTest2 = new MapTest(flag, a);
        threadTest2.run();
    }

    public MapTest() {
    }

    private boolean flag;

    private Map<String, List<String>> listMap;

    public MapTest(boolean flag, Map<String, List<String>> listMap) {
        this.flag = flag;
        this.listMap = listMap;
    }

    @Override
    public void run() {
        List<String> list = null;

        if (flag) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("remove start");
            list = listMap.remove("23");

        } else {
            System.out.println("get start");
            list = listMap.get("23");
            try {
                Thread.sleep(11000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get 0 start");
            System.out.println("get :" + list.get(0));
        }
    }
}

