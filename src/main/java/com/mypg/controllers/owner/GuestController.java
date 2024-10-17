package com.mypg.controllers.owner;

import com.mypg.dtos.GuestDTO;
import com.mypg.services.GuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.PrintStream;

@Controller
@RequestMapping("/owner/guest")
public class GuestController {
    private static final Logger log = LoggerFactory.getLogger(GuestController.class);
    String message;
    GuestService guestService;
    PrintStream out;
    GuestController(GuestService guestService) {
        this.guestService = guestService;
        out = System.out;
    }
    @GetMapping()

    public String getPage(Model model){
        model.addAttribute("pageName", "guest");
        model.addAttribute("guestForm",new GuestDTO());
        out.println("Collection Guest Data");
        model.addAttribute("guests",guestService.getGuests());
        model.addAttribute("error",message);

        out.println("-----Guest Page Requested");
        return "owner/index";

    }
    @PostMapping()
    public String guestFormSubmit(GuestDTO guestDTO, Model model){
        if(guestService.addGuest(guestDTO)){
            message = "Guest added successfully";
        }else{
            message = "Guest already exists or Something went wrong";
        }
        return "redirect:/owner/guest";
    }

    @GetMapping("/{mobile}")
    public String getGuestDetailPage(@PathVariable("mobile") Long mobile, Model model){
        model.addAttribute("pageName","Guest Detail Page");
        return "/owner/pages/guest_detail";
    }
}
