package org.example.lamdaexpression;

/**
 * 测试lamda表达式，展示从普通实现类到lamda表达式的演变过程
 * 要点：函数式接口只能有一个接口
 */
public class TestLamda1 {
    /**
     * 3、静态内部类
     */
    static class Love1 implements ILove {
        @Override
        public void love() {
            System.out.println("I love you1");
        }
    }


    public static void main(String[] args) {
        ILove love = new Love();
        love.love();

        love = new Love1();
        love.love();


        /**
         * 4、局部内部类
         */
        class Love2 implements ILove {
            @Override
            public void love() {
                System.out.println("I love you2");
            }
        }
        // 只能在局部内部类下面创建
        love = new Love2();
        love.love();

        /**
         * 5、匿名内部类
         */
        love = new ILove() {
            @Override
            public void love() {
                System.out.println("I love you3");
            }
        };

        love.love();

        /**
         * 6、lamda表达式
         */
        love = () -> System.out.println("I love you4");
        love.love();
    }
}

/**
 * 1、定义一个函数式接口，接口中只有一个抽象方法
 */
interface ILove {
    void love();
}

/**
 * 2、创建一个普通实现类
 */
class Love implements ILove {
    @Override
    public void love() {
        System.out.println("I love you");
    }
}
