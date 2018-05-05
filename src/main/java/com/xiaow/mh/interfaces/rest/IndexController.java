package com.xiaow.mh.interfaces.rest;

import com.xiaow.mh.business.domain.Equipment;
import com.xiaow.mh.business.service.IndexService;
import com.xiaow.mh.interfaces.DTO.ReceiverDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangnengwen on 17/11/14.
 */
@Controller
@RequestMapping("")
public class IndexController {

    @Autowired
    IndexService indexService;

    @RequestMapping("")
    public String admin(Model model){
        ReceiverDTO receiverDTO = new ReceiverDTO();
        model.addAttribute("receiverDTO",receiverDTO);
        return "index";
    }

    @RequestMapping("/show")
    public String funnel(Model model, @ModelAttribute ReceiverDTO receiverDTO) throws Exception{

        Equipment equipment = indexService.getRoler(receiverDTO);
        model.addAttribute("role",equipment);
        model.addAttribute("receiverDTO",receiverDTO);
        return "index";
    }
}
