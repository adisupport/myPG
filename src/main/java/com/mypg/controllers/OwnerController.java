package com.mypg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    @GetMapping()
    @ResponseBody
    public String getPage(){
        return "OWNER PAGE";
    }
}
