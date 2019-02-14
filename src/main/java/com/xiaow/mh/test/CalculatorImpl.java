package com.xiaow.mh.test;

/**
 * Created by zhangnengwen on 18/7/14.
 */
//接口实现类,执行真正的a+b操作
public class CalculatorImpl implements Calculator{
    @Override
    public int add(int a, int b) {
        System.out.println("doing ");
        return a+b;
    }
}
