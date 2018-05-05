package com.xiaow.mh.interfaces.DTO;

import lombok.Data;

import java.util.List;

/**
 * Created by zhangnengwen on 17/11/16.
 */
@Data
public class ReceiverDTO {

    private Long rolerId;

    private Mhroler roler;

    //武器和防具,耳环,胸针,戒指,腰带
    private List<CompleteEquipment> equipmentList;

    //手镯1
    private Bangle bangle1;

    //手镯2
    private Bangle bangle2;

    //工艺品前缀附魔
    private Long artwareMagicId1;

    //工艺品后缀附魔
    private Long artwareMagicId2;

    //时装类别id(S,A,B,C)
    private Long fashionId;

    //艾因拉赫头衔(黄金之神,白银之神(不喝药))
    private Long honourId;

    //敢死队战役
    private Long battleId;

    //远征等级
    private Long expeditionId;

    //时装徽章追加属性
    private Badge badge;
}
