package com.xiaow.mh.interfaces.DTO;

import lombok.Data;

/**
 * Created by zhangnengwen on 17/11/16.
 */
@Data
public class CompleteEquipment {

    private Long id;

    private Long upLevelId;

    private Integer fairyType;

    private Integer starType;

    private Long firstMagicId;

    private Long secondMagicId;
}
