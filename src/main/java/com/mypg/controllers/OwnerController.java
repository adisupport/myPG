package com.mypg.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    private static final Logger log = LoggerFactory.getLogger(OwnerController.class);

    @GetMapping()
    public String getPage(@RequestParam(value = "page", defaultValue = "") String pageRequest, Model model){
        log.debug(pageRequest);
        if(Objects.equals(pageRequest, "")){
            model.addAttribute("pageName", "home");
            return "owner/index";
        }
        String page = "home";

        if(pageRequest.equals("rooms")){
            page = "rooms";
        }else if(pageRequest.equals("guests")){
            page = "guests";
        }else if(pageRequest.equals("invoices")){
            page = "invoices";
        }else if(pageRequest.equals("complains")){
            page = "complains";
        }
        model.addAttribute("pageName",page);
        return "owner/index";
    }
}
