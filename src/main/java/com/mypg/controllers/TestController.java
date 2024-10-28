package com.mypg.controllers;

import com.mypg.dtos.TestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping()
    public String getPage(Model model){
        TestDTO dto = new TestDTO();
        dto.setId("1");
        dto.setName("Test DTO");
        dto.setAge("50");
        model.addAttribute("dto",dto);
        int age = 24;
        model.addAttribute("age", age);
        return "test";
    }

    @PostMapping()
    @ResponseBody
    public TestDTO getPage2(TestDTO dto){
        return dto;
    }
}
