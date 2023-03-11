package org.example.demo3;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程休眠 ---- 模拟倒计时
 */
public class TestSleep2 {

    public static void main(String[] args) throws InterruptedException {
        tikTok();
        timeEscape();
    }

    // 倒计时
    public static void tikTok() throws InterruptedException {
        for (int i = 10; i >= 0; i--) {
            System.out.println(i);
            Thread.sleep(1000);
        }
    }

    // 时间流逝
    public static void timeEscape() throws InterruptedException {
        Date date = new Date(System.currentTimeMillis());
        while (true) {
            String format = new SimpleDateFormat("HH:mm:ss").format(date);
            System.out.println(format);
            Thread.sleep(1000);
            date = new Date(System.currentTimeMillis());
        }
    }
}
