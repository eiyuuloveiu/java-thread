package org.example.demo1;

/**
 * 模拟龟兔赛跑
 */
public class Race implements Runnable {
    /**
     * 必须要有个变量来判断比赛是否已经结束了
     */
    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            // 这样就不行，没有变量判断是否结束
//            if (i >= 100) {
//                System.out.println("The winner is " + Thread.currentThread().getName());
//                break;
//            }

            // 兔子休息
            if (Thread.currentThread().getName().equals("rabbit") && i % 10 == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (gameOver(i))
                break;

            System.out.println(Thread.currentThread().getName() + "--> 走了" + i + "步");
        }
    }

    private boolean gameOver(int step) {
        if (winner != null) {
            return true;
        }
        if (step >= 100) {
            winner = Thread.currentThread().getName();
            System.out.println("The winner is " + Thread.currentThread().getName());
            return true;
        }
        return false;
    }



    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "rabbit").start();
        new Thread(race, "turtle").start();
    }
}
