package com.xiaow.mh.test;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhangnengwen on 18/7/14.
 */
public class test2 {

    public static void main(String[] args){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(CalculatorOwn.class);
        enhancer.setCallback(new MethodInterceptor() {
            //类似invokerhanddler的invoke方法
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("begin");
                Object invoke = methodProxy.invoke(new CalculatorOwn(), objects);
                System.out.println("end");
                return invoke;
            }
        });
        CalculatorOwn proxy =(CalculatorOwn) enhancer.create();
        System.out.println(proxy.add(2,3));
    }

}
