package com.xiaow.mh.business.domain;

/**
 * Created by zhangnengwen on 17/11/16.
 */
public enum PropertyType {

    人物角色(1000),
    武器(2000),
    副武器(2001),
    防具(3000),
    首饰(4000),
    首次附魔(5000),
    进阶附魔(5001),
    时装(6000),
    属性头衔(7000),
    其他(10000);

    private int value;

    PropertyType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name();
    }
}
