package org.example.demo1;

/**
 * 创建线程方式二：实现Runnable
 * 将实现了Runnable的对象传入Thread类即可
 * 可以避免OOP单继承局限性，灵活，方便同一个对象被多个线程使用(见下方链接)
 * @see TestThread4
 */
public class TestThread3 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println(Thread.currentThread().getName() + "--> code review..." + i);
        }
    }

    public static void main(String[] args) {
        // 创建一个线程对象
        TestThread3 runnable = new TestThread3();
        // 即使是传入同一个Runnable对象，也是会开启两个线程
        // 但是如果Runnable中存在共享的资源的话，就会存在并发的问题（见org.example.demo1.TestThread4）****************
        // Runnable对象可以被重复使用
        // 几个start就开启几个线程
        new Thread(runnable, "小明").start();
        new Thread(runnable, "小王").start();

        for (int i = 0; i < 2000; i++) {
            System.out.println("reading..." + i);
        }
    }
}
