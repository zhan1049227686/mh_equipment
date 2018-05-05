package com.xiaow.mh.business.domain;

import com.xiaow.mh.framework.domain.AppDO;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhangnengwen on 17/11/19.
 */
@Entity
@Table(name = "d_suit")
@Data
public class Suit implements AppDO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //力量
    private Integer strength;
    //智力
    private Integer intelligence;
    //最大生命值
    private Integer hp;
    //最大体力值
    private Integer vit;
    //防御
    private Integer defense;

    private Integer count;
}
