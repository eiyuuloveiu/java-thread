package org.example.demo3;

/**
 * 线程合并
 * 要点：
 * 1、线程优先执行，其他线程阻塞
 * 2、可以想象为vip插队
 */
public class TestJoin implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("vip-->" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 开启子线程
        TestJoin runnable = new TestJoin();
        Thread thread = new Thread(runnable);
        thread.start();

        // 主线程做的事
        for (int i = 0; i < 500; i++) {
            if (i == 200) {
                thread.join();
            }
            System.out.println("main-->" + i);
        }
    }
}
