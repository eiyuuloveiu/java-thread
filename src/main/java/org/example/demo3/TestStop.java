package org.example.demo3;

/**
 * 测试线程停止
 * 要点：
 * 1、不推荐使用jdk的stop和destroy方法
 * 2、推荐让线程自己停下 --> 利用次数，不建议死循环
 * 3、推荐使用标志位的方式停下线程
 */
public class TestStop implements Runnable {
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            System.out.println("run ... Thread");
        }
    }

    // 设置方法来设置标志位
    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop runnable = new TestStop();

        new Thread(runnable).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("Main " + i);
            if (i == 900) {
                runnable.stop();
                System.out.println("线程停止");
            }
        }
    }
}
