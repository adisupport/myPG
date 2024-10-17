package com.mypg.controllers.owner;

import com.mypg.dtos.BookingDTO;
import com.mypg.dtos.GuestDTO;
import com.mypg.exceptions.NoSuchRoom;
import com.mypg.services.BookingService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    BookingService bookingService;
    OwnerController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    private static final Logger log = LoggerFactory.getLogger(OwnerController.class);

    @GetMapping()
    public String getPage(Model model,HttpSession session){
        String error = (String) session.getAttribute("error");
        model.addAttribute("pageName","home");
        model.addAttribute("error",error);
        return "owner/index";
    }
    @GetMapping("/booking")
    public String getBookingForm(@RequestParam(value = "roomId",defaultValue = "0")Integer roomId,Model model){
        model.addAttribute("pageName","booking");
        model.addAttribute("bookingForm",new BookingDTO());
        model.addAttribute("roomId",roomId);
        return "owner/pages/bookingForm";
    }
    @PostMapping("/booking")
    public String handleBooking(BookingDTO dto) throws NoSuchRoom {
        bookingService.createBooking(dto);
        return "redirect:/owner";
    }
    @ExceptionHandler(NoSuchElementException.class)
    public String handleFailedBooking(NoSuchElementException e, HttpSession httpSession){
        httpSession.setAttribute("error","Booking Failed Due to: "+e.getMessage());
        return "redirect:/owner";
    }
}
