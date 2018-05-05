package com.xiaow.mh.interfaces.rest;

import com.xiaow.mh.business.domain.Equipment;
import com.xiaow.mh.business.service.IndexService;
import com.xiaow.mh.framework.common.exceptions.PersistenceException;
import com.xiaow.mh.interfaces.DTO.ReceiverDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * Created by zhangnengwen on 17/11/14.
 */
@RestController
public class IndexAPI {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "api/getRoler", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Equipment getRole(@RequestBody ReceiverDTO receiverDTO) throws PersistenceException {
        receiverDTO.setEquipmentList(receiverDTO.getEquipmentList().stream().filter(i -> i.getId() != null).collect(Collectors.toList()));
        if (receiverDTO.getRoler().isNull() == false) {
            receiverDTO.setHonourId(null);
            receiverDTO.setBattleId(null);
            receiverDTO.setExpeditionId(null);
        }
        Equipment equipment = indexService.getRoler(receiverDTO);
        equipment.addRoler(receiverDTO.getRoler());
        return equipment;
    }
}
