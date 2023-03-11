package org.example.demo1;

/**
 *  创建线程方式一：继承Thread类，重写run()方法，调用start开启线程
 *  总结：注意，线程开启不一定立即执行，由CPU调度执行
 *  存在OOP单继承局限性
 */
public class TestThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("code review..." + i);
        }
    }


    public static void main(String[] args) {
        // 创建一个线程对象
        TestThread1 thread1 = new TestThread1();

        // 按照顺序执行
        //thread1.run();

        // 调用start方法开启多线程
        thread1.start();

        for (int i = 0; i < 2000; i++) {
            System.out.println("reading..." + i);
        }
    }
}
