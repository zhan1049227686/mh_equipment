package com.xiaow.mh.test;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhangnengwen on 19/1/10.
 */
public class test3 {
    public static void main(String[] args){
        Long a = 200l;

        Double b = 2.34564;

        Float c = 1.234f;

        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(c.toString());
    }
}
