package org.example.lamdaexpression;

/**
 * lamda表达式的简化过程
 */
public class TestLamda2 {
    public static void main(String[] args) {
        ILike like = (int a) -> {
            System.out.println("I like " + a);
        };
        like.like(1);

        // 简化1 去掉参数类型
        like = (a) -> {
            System.out.println("I like " + a);
        };
        like.like(2);

        // 简化2 去掉括号
        like = a -> {
            System.out.println("I like " + a);
        };
        like.like(3);

        // 简化3 去掉花括号
        like = a -> System.out.println("I like " + a);
        like.like(4);
    }
}

interface ILike {
    void like(int a);
}
