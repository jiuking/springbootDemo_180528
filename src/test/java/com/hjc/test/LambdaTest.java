package com.hjc.test;

/**
 * @author : Hjc
 * @date : 2018/5/31 0031 15:10
 * @description : java8-lambda语句
 */
public class LambdaTest {
    public static void main(String[] args) {
        System.out.println("hello lambda");
        new Thread(() -> System.out.println(Thread.currentThread().getName()+" Hello Lambda")).start();
    }
}
