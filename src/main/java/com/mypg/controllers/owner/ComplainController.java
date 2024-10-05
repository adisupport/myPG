package com.mypg.controllers.owner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner/complain")
public class ComplainController {
    @GetMapping()
    public String getComplain(Model model) {
        model.addAttribute("pageName","complain");
        return "owner/index";
    }
}
