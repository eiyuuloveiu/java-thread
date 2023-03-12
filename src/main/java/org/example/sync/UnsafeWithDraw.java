package org.example.sync;

/**
 * 线程不安全 -- 取钱
 * 解决方案：使用synchronized代码块锁住account，也就是需要进行增删改查的的对象
 * 注意：在withdraw方法上加上synchronized关键字不会生效，因为锁的对象不正确
 */
public class UnsafeWithDraw {
    public static void main(String[] args) {
        // 一千元的账户
        Account account = new Account(100);
        // 两人同时取
        new Thread(new WithDrawMoney(account, 50), "A").start();
        new Thread(new WithDrawMoney(account, 60), "B").start();
    }
}

/**
 * 取钱，专注于取钱的动作
 */
class WithDrawMoney implements Runnable {
    private Account account;
    private int withDrawAmount;
    private int nowAmount;


    public WithDrawMoney(Account account, int withDrawAmount) {
        this.account = account;
        this.withDrawAmount = withDrawAmount;
    }

    @Override
    public void run() {
        // 非常重要
        synchronized (account) {
            withdraw();
        }
    }

    private void withdraw() {
        if (account.getTotalAmount() < withDrawAmount) {
            System.out.println(Thread.currentThread().getName() + " --> 银行账户不够此次取款");
            return;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int remainAmount = account.getTotalAmount() - withDrawAmount;
        account.setTotalAmount(remainAmount);
        nowAmount += withDrawAmount;
        System.out.println("银行账户余额为" + this.account.getTotalAmount());
        System.out.println(Thread.currentThread().getName() + " --> 手头有钱" + this.nowAmount + "元");
    }

}

class Account {
    private int totalAmount;

    public Account(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
