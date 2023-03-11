package org.example.demo3;

/**
 * 线程状态
 * 要点：
 * 1、所有线程的状态都定义在Thread.State中
 */
public class TestState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                }
                System.out.println("end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread.State state = thread.getState();
        System.out.println(state);

        thread.start();
        state = thread.getState();
        System.out.println(state);

        while (state != Thread.State.TERMINATED) {
            // 这里的休眠一定要加，不然结果很奇怪
            Thread.sleep(100);
            state = thread.getState();
            System.out.println(state);
        }

    }
}
