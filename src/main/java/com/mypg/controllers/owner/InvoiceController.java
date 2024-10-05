package com.mypg.controllers.owner;

import com.mypg.models.Invoice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner/invoice")
public class InvoiceController {
    @GetMapping()
    public String invoices(Model model){
        model.addAttribute("pageName", "invoice");
        return "owner/index";
    }
}
