package com.mypg.controllers.owner;

import com.mypg.dtos.GuestDTO;
import com.mypg.models.Guest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/owner/guest")
public class GuestController {
    @GetMapping()
    public String getPage(Model model){

        model.addAttribute("pageName", "guest");
        model.addAttribute("form",new GuestDTO());

        return "owner/index";
    }
}
