package org.example.demo3;


/**
 * 线程休眠 --- 模拟网络延时: 放大问题的发生性
 * 要点：
 * 1、sleep会抛出中断异常
 * 2、sleep时间达到后线程进入就绪状态
 * 3、可以模拟网络延时、倒计时
 * 4、每一个对象都有一个锁，sleep不会释放锁
 */
public class TestSleep1 implements Runnable {
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
        TestSleep1 runnable = new TestSleep1();

        // 多个线程操作同一个Runnable对象
        // 并且对象中存在共享的资源，会存在并发问题***************
        new Thread(runnable, "A").start();
        new Thread(runnable, "B").start();
        new Thread(runnable, "C").start();
    }
}
