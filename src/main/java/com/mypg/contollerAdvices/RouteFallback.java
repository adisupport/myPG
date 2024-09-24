package com.mypg.contollerAdvices;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class RouteFallback {
    @ExceptionHandler(NoResourceFoundException.class)
    public String fallback(){
        return "redirect:/";
    }
}
