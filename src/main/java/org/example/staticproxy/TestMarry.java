package org.example.staticproxy;

/**
 * 静态代理模式
 * Thread采用的就是静态代理模式，Thread类是代理类，而真实类和Thread类都实现了Runnable接口
 * 要点：代理类与真实类需要实现同一个接口。代理对象要代理真实对象
 * 好处：代理对象可以做很多真实对象做不了的事；真实对象可以专注自己的事情
 */
public class TestMarry {

    public static void main(String[] args) {
        // 其实Thread与这个例子中的WeddingCompany相同，采用的是静态代理模式
        // Thread thread = new Thread(() -> System.out.println("Test"));
        // thread.start();

        // 可以进行相应简化
        // 1
        WeddingCompany company = new WeddingCompany(new RealPerson());
        company.marry();
        // 2
        new WeddingCompany(new RealPerson()).marry();
        // 3
        new WeddingCompany(() -> System.out.println("中国解放了，我要结婚了")).marry();
    }

}

// 结婚接口
interface Marry {
    void marry();
}

// 真实对象
class RealPerson implements Marry {
    @Override
    public void marry() {
        System.out.println("我要结婚了。。。");
    }
}

// 婚姻中介公司，会为需要结婚的人提供婚前婚后服务
class WeddingCompany implements Marry {
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void marry() {
        before();
        this.target.marry();
        after();
    }

    private void after() {
        System.out.println("婚后准备。。。。");
    }

    private void before() {
        System.out.println("婚前准备。。。。");
    }
}
