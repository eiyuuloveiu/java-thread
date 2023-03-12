package org.example.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * 不安全的List集合
 *
 * 在多线程的情况下，操作同一个集合，会出现并发的情况
 * 解决方式：使用synchronized代码块锁住list对象
 */
public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
//                synchronized (list) {
                    list.add(Thread.currentThread().getName());
//                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }

        System.out.println(list.size());


    }
}
