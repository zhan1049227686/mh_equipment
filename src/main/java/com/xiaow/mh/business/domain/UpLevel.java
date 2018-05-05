package com.xiaow.mh.business.domain;

import com.xiaow.mh.framework.domain.AppDO;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhangnengwen on 17/11/19.
 */
@Table(name = "d_uplevel")
@Entity
@Data
public class UpLevel implements AppDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //等级名
    private String uplevelName;
    //攻击
    private Integer attack;
    private Integer attackSpd;
    //防御
    private Integer defense;

    //最大生命值
    private Integer hp;

    //追伤
    private Integer addAttack;

    //属性类型
    private Integer propertyType;
}
