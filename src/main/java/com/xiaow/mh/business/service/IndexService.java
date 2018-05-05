package com.xiaow.mh.business.service;

import com.xiaow.mh.business.domain.*;
import com.xiaow.mh.framework.common.exceptions.PersistenceException;
import com.xiaow.mh.framework.service.TransactionalService;
import com.xiaow.mh.interfaces.DTO.CompleteEquipment;
import com.xiaow.mh.interfaces.DTO.ReceiverDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangnengwen on 17/11/14.
 */
@Service
public class IndexService extends TransactionalService<EquipmentRepo,Equipment>{

    @Autowired
    private UpLevelRepo upLevelRepo;

    @Autowired
    private SuitRepo suitRepo;

    /**
     *
     * @param receiverDTO
     * @return
     */
    public Equipment getRoler(ReceiverDTO receiverDTO) throws PersistenceException{
        //角色基础属性
        if (receiverDTO.getRolerId() == null || receiverDTO.getRolerId() == -1) {
            receiverDTO.setRolerId(179L);
        }
        Equipment finalEquipment = findOne(receiverDTO.getRolerId());
        Equipment equipment = null;
        //添加装备属性
        Map<String,Integer> map = new HashMap();
        map.put("雷吉纳",0);
        map.put("布拉哈",0);
        map.put("境界的守护者",0);
        map.put("鲁拉巴达",0);
        map.put("哈维登特",0);
        map.put("图拉汗",0);
        List<String> ringList = new ArrayList<>();
        for (CompleteEquipment completeEquipment: receiverDTO.getEquipmentList()) {
            equipment = getOne(completeEquipment);
            finalEquipment.addMagic(equipment);

            //套装判断
           for (String name:map.keySet()) {
               if (equipment.getName().contains(name))
                   map.put(name,map.get(name) + 1);
           }
           if (equipment.getName().contains("酷寒")) {
               ringList.add(equipment.getName());
           }
        }
        //工艺品首次附魔属性
        if (receiverDTO.getArtwareMagicId1() != null) {
            equipment = findOne(receiverDTO.getArtwareMagicId1());
            finalEquipment.addMagic(equipment);
        }

        //工艺品进阶附魔属性
        if (receiverDTO.getArtwareMagicId2() != null) {
            equipment = findOne(receiverDTO.getArtwareMagicId2());
            finalEquipment.addMagic(equipment);
        }

        //加手镯属性
        finalEquipment.addBangle(receiverDTO.getBangle1());
        finalEquipment.addBangle(receiverDTO.getBangle2());
        //加徽章属性
        finalEquipment.addBadge(receiverDTO.getBadge());
        //加时装属性
        if (receiverDTO.getFashionId() != null) {
            equipment = findOne(receiverDTO.getFashionId());
            finalEquipment.addMagic(equipment);
        }
        //加头衔属性
        if (receiverDTO.getHonourId() != null) {
            equipment = findOne(receiverDTO.getHonourId());
            finalEquipment.addMagic(equipment);
        }
        //加敢死队属性
        if (receiverDTO.getBattleId() != null) {
            equipment = findOne(receiverDTO.getBattleId());
            if (equipment.getCrit() >= 3) {
                finalEquipment.setLiftBan(finalEquipment.getLiftBan() + 300);
            }
            finalEquipment.addMagic(equipment);
        }
        //加远征属性
        if (receiverDTO.getExpeditionId() != null) {
            equipment = findOne(receiverDTO.getExpeditionId());
            finalEquipment.addMagic(equipment);
        }
        //套装属性添加
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            if (entry.getValue() > 2) {
               List<Suit> suitList = suitRepo.findByNameAndCount(entry.getKey(),entry.getValue());
                if (suitList != null && suitList.size() > 0) {
                    finalEquipment.addSuit(suitList.get(0));
                }
            }
        }
        //戒指套装判断
        if (ringList.size() == 2) {
            if ((ringList.get(0).equals("酷寒的刺") && ringList.get(1).equals("酷寒的匕首"))
                    ||(ringList.get(0).equals("酷寒的匕首") && ringList.get(1).equals("酷寒的刺"))
                    ||(ringList.get(0).equals("酷寒的意志") && ringList.get(1).equals("酷寒的怨念"))
                    ||(ringList.get(0).equals("酷寒的怨念") && ringList.get(1).equals("酷寒的意志"))) {
                finalEquipment.setCrit(finalEquipment.getCrit() + 1);
            }
        }
        return finalEquipment.propertyConversion();
    }

    /**
     * 得到单件装备的完整属性
     * @param completeEquipment
     * @return
     * @throws PersistenceException
     */
    public Equipment getOne(CompleteEquipment completeEquipment) throws PersistenceException{
        Equipment equipment = findOne(completeEquipment.getId());
        if (equipment != null) {
            addStar(equipment,completeEquipment.getStarType());
            addUpLevel(equipment,completeEquipment.getUpLevelId());
            addMagic(equipment,completeEquipment.getFirstMagicId());
            addMagic(equipment,completeEquipment.getSecondMagicId());
            addFairy(equipment,completeEquipment.getFairyType());
        }
        return equipment;
    }

    /**
     * 加星提升装备属性计算
     * @param equipment
     * @param starType
     * @return
     */
    public Equipment addStar(Equipment equipment,Integer starType) {
        if (starType == null) {
            return equipment;
        }
        if (StarType.三星.getValue() == starType.intValue()) {
            return equipment.addStar(1.15,1.02);
        }
        if (StarType.四星.getValue() == starType.intValue()) {
            return equipment.addStar(1.2,1.04);
        }
        if (StarType.五星.getValue() == starType.intValue()) {
            return equipment.addStar(1.25,1.06);
        }
        return equipment;
    }

    /**
     * 加精灵石
     * @param equipment
     * @param fairyType
     * @return
     */
    public Equipment addFairy(Equipment equipment, Integer fairyType) {
        if (fairyType == null) {
            return equipment;
        }
        switch (fairyType.intValue()) {
            case 1000:
                equipment.setAttackSpd(equipment.getAttackSpd() + 1);
                break;
            case 2000:
                equipment.setBalance(equipment.getBalance() + 2);
                break;
            case 3000:
                equipment.setCrit(equipment.getCrit() + 2);
                break;
            case 4000:
                equipment.setDefense(equipment.getDefense() + 103);
                break;
            case 5000:
                equipment.setCritDef(equipment.getCritDef() + 1);
                break;
            default:
                break;
        }
        return equipment;
    }

    /**
     * 加强化
     * @param equipment
     * @param upLevelId
     * @return
     */
    public Equipment addUpLevel(Equipment equipment, Long upLevelId) {
        if (upLevelId == null) {
            return equipment;
        }
        UpLevel upLevel = upLevelRepo.findOne(upLevelId);
        return equipment.addUpLevel(upLevel);

    }

    /**
     * 加附魔
     * @param equipment
     * @param magicId
     * @return
     * @throws PersistenceException
     */
    public Equipment addMagic(Equipment equipment, Long magicId) throws PersistenceException{
        if (magicId == null) {
            return equipment;
        }
        Equipment magic = findOne(magicId);
        return equipment.addMagic(magic);
    }
}
