package com.xiaow.mh.business.domain;

/**
 * Created by zhangnengwen on 17/11/16.
 */
public enum FairyType {

    攻速1(1000),
    平衡2(2000),
    暴击2(3000),
    防御103(4000),
    暴击抵抗1(5000);

    private int value;

    FairyType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name();
    }
}
