package com.xiaow.mh.business.domain;

import com.xiaow.mh.framework.common.Utils;
import com.xiaow.mh.framework.domain.AppDO;
import com.xiaow.mh.interfaces.DTO.Badge;
import com.xiaow.mh.interfaces.DTO.Bangle;
import com.xiaow.mh.interfaces.DTO.Mhroler;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhangnengwen on 17/11/14.
 * 包含附魔,武器,防具,首饰(除手镯工艺品),套装属性
 */
@Entity
@Data
@Table(name = "d_equipment")
public class Equipment implements AppDO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "USEDLEVEL")
    private Integer usedlever;
    //力量
    private Integer strength;
    //敏捷
    private Integer agility;
    //智力
    private Integer intelligence;
    //意志
    private Integer volition;
    //最大生命值
    private Integer hp;
    //最大体力值
    private Integer vit;
    //攻击
    private Integer attack;
    //魔法攻击力
    private Integer magicAttack;
    //防御
    private Integer defense;
    //攻速
    private Integer attackSpd;
    //暴击
    private Integer crit;
    //平衡
    private Integer balance;
    //暴击抵抗
    private Integer critDef;

    private Integer propertyType;

    @Transient
    private int addAttack;

    @Transient
    private int liftBan;

    public Equipment addStar(double value1, double value2) {
        strength = (int)(Math.ceil(strength * value1));
        agility = (int)(Math.ceil(agility * value1));
        intelligence = (int)(Math.ceil(intelligence * value1));
        volition = (int)(Math.ceil(volition * value1));
        attack = (int)(Math.ceil(attack * value2));
        defense = (int)(Math.ceil(defense * value2));
        return this;
    }

    public Equipment addUpLevel(UpLevel upLevel) {
        if (upLevel != null) {
            attack += upLevel.getAttack();
            magicAttack += upLevel.getAttack();
            attackSpd += upLevel.getAttackSpd();
            defense += upLevel.getDefense();
            hp += upLevel.getHp();
            addAttack = upLevel.getAddAttack();
        }
        return this;
    }

    public Equipment addMagic(Equipment magic) {
        if (magic != null) {
            this.strength += magic.getStrength();
            this.agility += magic.getAgility();
            this.intelligence += magic.getIntelligence();
            this.volition += magic.getVolition();
            this.hp += magic.getHp();
            this.vit += magic.getVit();
            this.attack += magic.getAttack();
            this.magicAttack += magic.getMagicAttack();
            this.defense += magic.getDefense();
            this.attackSpd += magic.getAttackSpd();
            this.crit += magic.getCrit();
            this.balance += magic.getBalance();
            this.critDef += magic.getCritDef();
            this.addAttack += magic.getAddAttack();
        }
        return this;
    }

    public Equipment addBangle(Bangle bangle) {
        if (bangle != null) {
            this.attack += bangle.getAttack();
            this.magicAttack += bangle.getMagicAttack();
            this.defense += bangle.getDefense();
            this.hp += bangle.getHp();
        }
        return this;
    }

    public Equipment addSuit(Suit suit) {
        if (suit != null) {
            this.strength += suit.getStrength();
            this.intelligence += suit.getIntelligence();
            this.hp += suit.getHp();
            this.vit += suit.getVit();
            this.defense += suit.getDefense();
        }
        return this;
    }

    public Equipment addBadge(Badge badge) {
        if (badge!=null) {
            this.attack += badge.getAttack();
            this.magicAttack += badge.getMagicAttack();
            this.crit += badge.getCrit();
            this.liftBan += badge.getLiftBan();
            this.critDef += badge.getCritDef();
        }
        return this;
    }

    public Equipment propertyConversion() {
        this.attack += this.strength * 8 / 3;
        this.magicAttack += this.intelligence * 2;
        this.defense += this.agility / 2;
        this.hp += this.volition * 3 / 5;
        this.crit += this.volition < 2000 ? this.volition * 3 / 400 : 15;
        this.attack = Math.max(this.attack,this.magicAttack);
        return this;
    }

    public Equipment addRoler(Mhroler mhroler) {
        if (mhroler != null) {
            Utils.addProperty(this,mhroler);
        }
        return this;
    }
}
