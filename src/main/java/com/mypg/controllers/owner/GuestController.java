package com.mypg.controllers.owner;

import com.mypg.dtos.GuestDTO;
import com.mypg.dtos.GuestResponseDTO;
import com.mypg.exceptions.InvoiceException;
import com.mypg.models.Guest;
import com.mypg.models.Invoice;
import com.mypg.services.GuestService;
import jakarta.servlet.http.HttpSession;
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
    GuestService guestService;
    PrintStream out;
    GuestController(GuestService guestService) {
        this.guestService = guestService;
        out = System.out;
    }
    @GetMapping()
    public String getPage(Model model, HttpSession session){

        String message = (String) session.getAttribute("message");
        session.setAttribute("message",null);
        model.addAttribute("pageName", "guest");
        model.addAttribute("guestForm",new GuestDTO());
        out.println("Collection Guest Data");
        model.addAttribute("guests",guestService.getGuests());
        model.addAttribute("error",message);

        out.println("-----Guest Page Requested------");
        return "owner/index";

    }
    @PostMapping()
    public String guestFormSubmit(GuestDTO guestDTO, Model model,HttpSession session){
        if(guestService.addGuest(guestDTO)){
            session.setAttribute("message","Guest added successfully");
        }else{
            session.setAttribute("message","Guest already exists or Something went wrong");
        }
        return "redirect:/owner/guest";
    }

    @GetMapping("/{mobile}")
    public String getGuestDetailPage(@PathVariable("mobile") Long mobile, Model model){
        model.addAttribute("pageName","Guest Detail Page");
        GuestResponseDTO guest = guestService.findGuestByMobile(mobile);
        model.addAttribute("guest",guest);
        Invoice invoice = new Invoice();
        invoice.setGuest(guest);
        model.addAttribute("invoiceForm",invoice);
        return "/owner/pages/guest_detail";
    }
    @PostMapping("/{mobile}/invoice")
//    @ResponseBody
    public String handleCreateInvoice(Invoice invoice,@PathVariable("mobile") Long mobile, Model model)
    throws InvoiceException
    {
        invoice.setGuest(guestService.getGuestByMobile(mobile));

        guestService.addInvoice(invoice);
        return "redirect:/owner/guest/"+mobile;
    }
    @GetMapping("/{mobile}/checkout")
    public String checkout(@PathVariable("mobile") Long mobile,HttpSession session){
        System.out.println("checkout guest " + mobile);
        guestService.checkout(mobile);
        session.setAttribute("message","Guest checked out successfully");
        return "redirect:/owner/guest";
    }
    @ExceptionHandler(InvoiceException.class)
    public String handleException(InvoiceException e, HttpSession session){
        session.setAttribute("message",e.getMessage());
        return "redirect:/owner";
    }
}
