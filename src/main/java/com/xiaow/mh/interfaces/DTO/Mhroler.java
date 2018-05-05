package com.xiaow.mh.interfaces.DTO;

import com.xiaow.mh.framework.domain.AppDO;
import lombok.Data;

/**
 * Created by zhangnengwen on 18/4/17.
 */
@Data
public class Mhroler implements AppDTO{

    //力量
    private int strength;
    //敏捷
    private int agility;
    //智力
    private int intelligence;
    //意志
    private int volition;
    //攻击
    private int attack;
    //防御
    private int defense;

    private int crit;

    private int hp;

    private int vit;

    public boolean isNull() {
        if (strength > 0) {
            return false;
        }
        if (agility > 0) {
            return false;
        }
        if (intelligence > 0) {
            return false;
        }
        if (volition > 0) {
            return false;
        }
        if (attack > 0) {
            return false;
        }
        if (defense > 0) {
            return false;
        }
        if (crit > 0) {
            return false;
        }
        if (hp > 0) {
            return false;
        }
        if (vit > 0) {
            return false;
        }
        return true;
    }
}
