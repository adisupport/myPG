package com.mypg.controllers.owner;

import com.mypg.dtos.GuestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    private static final Logger log = LoggerFactory.getLogger(OwnerController.class);

    @GetMapping()
    public String getPage(Model model){
        model.addAttribute("pageName","home");
        return "owner/index";
    }
    @GetMapping("/booking")
    public String getBookingForm(Model model){
        model.addAttribute("pageName","booking");
        model.addAttribute("bookingForm",new GuestDTO());
        return "owner/pages/bookingForm";
    }
    @PostMapping("/booking")
    public String handleBooking(GuestDTO dto){
        return "redirect:/owner";
    }
}
