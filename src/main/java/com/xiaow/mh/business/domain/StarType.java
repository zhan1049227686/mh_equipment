package com.xiaow.mh.business.domain;

/**
 * Created by zhangnengwen on 17/11/16.
 */
public enum StarType {

    二星(2000),
    三星(3000),
    四星(4000),
    五星(5000);

    private int value;

    StarType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name();
    }
}
