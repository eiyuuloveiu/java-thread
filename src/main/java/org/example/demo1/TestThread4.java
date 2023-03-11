package org.example.demo1;

/**
 * 多个线程操作同一个Runnable对象
 * 并且对象中存在共享的资源，会存在并发问题***************
 * 存在两个人取到同一个结果的情况，甚至出现了num小于0的情况
 */
public class TestThread4 implements Runnable {
    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }

            // 这里出现睡眠，出现结果更加明显
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "--> 拿到了第" + ticketNums-- + "票");
        }
    }


    public static void main(String[] args) {
        TestThread4 runnable = new TestThread4();

        // 多个线程操作同一个Runnable对象
        // 并且对象中存在共享的资源，会存在并发问题***************
        new Thread(runnable, "A").start();
        new Thread(runnable, "B").start();
        new Thread(runnable, "C").start();
    }
}
